package co.zip.candidate.userapi.account;

import co.zip.candidate.userapi.user.User;
import co.zip.candidate.userapi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService, UserService userService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.userService = userService;
        this.accountMapper = accountMapper;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        User user = userService.getUserById(accountDTO.getUserId());
        Account account = accountService.createAccount(accountMapper.createFromDTO(user));
        return ResponseEntity.ok(accountMapper.toDTO(account));
    }
}
