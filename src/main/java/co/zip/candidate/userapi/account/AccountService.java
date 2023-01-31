package co.zip.candidate.userapi.account;

import co.zip.candidate.userapi.exceptions.AccountPrerequisiteException;
import co.zip.candidate.userapi.exceptions.ResourceNotFoundException;
import co.zip.candidate.userapi.user.User;
import co.zip.candidate.userapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account createAccount(Account account) {
        userRepository.findById(account.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "userId", account.getUser().getId()));
        if (!canCreateAccount(account.getUser())) {
            throw new AccountPrerequisiteException("User has insufficient credit to create an account: (monthly_salary - monthly_expenses) >= 1000");
        }
        return accountRepository.save(account);
    }

    private boolean canCreateAccount(User user) {
        BigDecimal credit = user.getMonthlySalary().subtract(user.getMonthlyExpenses());
        BigDecimal minimumCredit = new BigDecimal("1000");
        return credit.compareTo(minimumCredit) >= 0;
    }




}
