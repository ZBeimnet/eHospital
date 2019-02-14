package com.atech.yekatit_care.Services;
import com.atech.yekatit_care.Domains.Role;
import com.atech.yekatit_care.Domains.User;
import com.atech.yekatit_care.Repositories.RoleRepository;
import com.atech.yekatit_care.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("roleRepository") RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> getAllUsers(){ return userRepository.findAllByOrderByName(); }

    public void saveUser(User user, String role) {
        user.setPassword((bCryptPasswordEncoder.encode(user.getPassword())));
        user.setActive(1);
        Role userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet(Arrays.asList(userRole)));
        userRepository.save(user);

    }
}
