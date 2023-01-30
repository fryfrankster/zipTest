package co.zip.candidate.userapi.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User createFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setMonthlyExpenses(userDTO.getMonthlyExpenses());
        user.setMonthlySalary(userDTO.getMonthlySalary());
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setMonthlySalary(user.getMonthlySalary());
        userDTO.setMonthlyExpenses(user.getMonthlyExpenses());
        return userDTO;
    }
}
