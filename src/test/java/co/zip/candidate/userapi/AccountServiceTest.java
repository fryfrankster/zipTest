package co.zip.candidate.userapi;

import co.zip.candidate.userapi.account.Account;
import co.zip.candidate.userapi.account.AccountRepository;
import co.zip.candidate.userapi.account.AccountService;
import co.zip.candidate.userapi.exceptions.AccountPrerequisiteException;
import co.zip.candidate.userapi.exceptions.DuplicateException;
import co.zip.candidate.userapi.user.User;
import co.zip.candidate.userapi.user.UserRepository;
import co.zip.candidate.userapi.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    @Mock
    UserService userService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AccountService accountService;

    User user;

    @BeforeEach
    public void initialise() {
        user = new User();
        user.setId(1);
    }

    @Test
    public void credit_check_fails_with_insufficient_credit(){
        user.setMonthlyExpenses(new BigDecimal("10000"));
        user.setMonthlySalary(new BigDecimal("0"));

        doReturn(user).when(userService).getUserById(user.getId());
        assertThatThrownBy(() -> accountService.createAccount(user.getId())).isInstanceOf(AccountPrerequisiteException.class)
                .hasMessageContaining("User has insufficient credit to " +
                        "create an account: (monthly_salary - monthly_expenses) >= 1000");
    }

    @Test
    public void credit_check_passes_with_sufficient_credit(){
        user.setMonthlyExpenses(new BigDecimal("100"));
        user.setMonthlySalary(new BigDecimal("2000"));

        doReturn(user).when(userService).getUserById(user.getId());
        accountService.createAccount(user.getId());
    }

    @Test
    public void credit_check_passes_with_sufficient_boundary(){
        user.setMonthlyExpenses(new BigDecimal("0"));
        user.setMonthlySalary(new BigDecimal("1000"));

        doReturn(user).when(userService).getUserById(user.getId());
        accountService.createAccount(user.getId());
    }

    @Test
    public void user_can_create_account(){
        user.setMonthlyExpenses(new BigDecimal("0"));
        user.setMonthlySalary(new BigDecimal("10000"));

        doReturn(user).when(userService).getUserById(user.getId());
        accountService.createAccount(user.getId());
    }

    @Test
    public void user_cannot_create_multiple_accounts(){
        user.setAccount(new Account());
        doReturn(user).when(userService).getUserById(user.getId());

        assertThatThrownBy(() -> accountService.createAccount(user.getId())).isInstanceOf(DuplicateException.class)
                .hasMessageContaining("accountId already exists: User with ID 1 already has an account");
    }

}
