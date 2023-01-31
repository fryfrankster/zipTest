package co.zip.candidate.userapi.account;

import co.zip.candidate.userapi.exceptions.AccountPrerequisiteException;
import co.zip.candidate.userapi.exceptions.DuplicateException;
import co.zip.candidate.userapi.exceptions.ResourceNotFoundException;
import co.zip.candidate.userapi.user.User;
import co.zip.candidate.userapi.user.UserRepository;
import co.zip.candidate.userapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(long userId) {
        User user = userService.getUserById(userId);
        Account existingAccount = user.getAccount();
        if(existingAccount != null){
            throw new DuplicateException("accountId", "", "User with ID " + userId + " already has an account");
        }
        if (!hasEnoughCredit(user)) {
            throw new AccountPrerequisiteException("User has insufficient credit to create an account: (monthly_salary - monthly_expenses) >= 1000");
        }
        Account account = new Account();
        account.setUser(user);
        return accountRepository.save(account);
    }

    private boolean hasEnoughCredit(User user) {
        BigDecimal credit = user.getMonthlySalary().subtract(user.getMonthlyExpenses());
        BigDecimal minimumCredit = new BigDecimal("1000");
        return credit.compareTo(minimumCredit) >= 0;
    }
}
