package co.zip.candidate.userapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUser() {
        return "User Frankie here";
    }

    public List<AppUser> getAll(){
        return userRepository.findAll();
    }

    public AppUser createUser(){
        AppUser myUser = new AppUser();
        myUser.setName("Frank");
        return userRepository.save(myUser);
    }
}
