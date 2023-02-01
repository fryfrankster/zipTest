package co.zip.candidate.userapi;

import co.zip.candidate.userapi.exceptions.DuplicateException;
import co.zip.candidate.userapi.exceptions.ResourceNotFoundException;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user;

    @BeforeEach
    public void initialise() {
        user = new User();
        user.setId(1);
        user.setName("Frank");
        user.setEmail("frank@email.com");
        user.setMonthlySalary(new BigDecimal("10000"));
        user.setMonthlyExpenses(new BigDecimal("50"));
    }

    @Test
    public void get_user_by_id() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        User userResult = userService.getUserById(user.getId());
        assertThat(userResult.getId()).isSameAs(user.getId());
    }

    @Test
    public void get_user_by_id_not_found() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> userService.getUserById(user.getId())).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("User not found with id : 1");
    }

    @Test
    public void get_all_users() {
        List<User> users = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(users);

        List<User> usersResult = userService.getAllUsers();
        assertThat(usersResult).isSameAs(users);
    }

    @Test
    public void get_all_users_empty() {
        List<User> users = Collections.emptyList();
        when(userRepository.findAll()).thenReturn(users);

        List<User> usersResult = userService.getAllUsers();
        assertThat(usersResult).isSameAs(users);
    }

    @Test
    public void create_user() {
        when(userRepository.save(user)).thenReturn(user);

        User userResult = userService.createUser(user);
        assertThat(user).isSameAs(userResult);
    }

    @Test
    public void create_user_email_already_exists() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        User duplicateUser = new User();
        duplicateUser.setEmail(user.getEmail());

        assertThatThrownBy(() -> userService.createUser(duplicateUser)).isInstanceOf(DuplicateException.class)
                .hasMessageContaining("email "+ user.getEmail() + " already exists: Could not create user");
    }
}
