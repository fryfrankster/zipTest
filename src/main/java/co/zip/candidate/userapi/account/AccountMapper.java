package co.zip.candidate.userapi.account;

import co.zip.candidate.userapi.user.User;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account createFromDTO(User user) {
        Account account = new Account();
        account.setUser(user);
        return account;
    }

    public AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUserId(account.getUser().getId());
        return accountDTO;
    }
}
