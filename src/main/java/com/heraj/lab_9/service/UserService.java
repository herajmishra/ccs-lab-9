package com.heraj.lab_9.service;

import com.heraj.lab_9.exception.UserNotFoundException;
import com.heraj.lab_9.model.CustomUserDetails;
import com.heraj.lab_9.model.SaltResponse;
import com.heraj.lab_9.model.User;
import com.heraj.lab_9.repository.UserRepository;
import com.heraj.lab_9.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordUtil passwordUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveUser(User user) {
        passwordEncoder.encode(user.getPassword());
        String salt2 = passwordUtil.getSalt();
        String encodedPassword = BCrypt.hashpw(user.getPassword(), salt2);;
        user.setPassword(encodedPassword);
        user.setSalt2(salt2);
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public CustomUserDetails login(String username, String password) {
        password = BCrypt.hashpw(password, userRepository.findByUsername(username).get().getSalt2());
        logger.info(password);
        List<User> users = userRepository.findByUsernameAndPassword(username, password);
        logger.info(users.size() == 1 ? "login successful" : "login failed.");
        if (users.size() != 1) {
            throw new UserNotFoundException("Not found");
        }
        return new CustomUserDetails(users.get(0));
    }

    public SaltResponse getSalt(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        logger.info(user.getUsername() + user.getSalt());
        if (user != null) {
            return new SaltResponse(user.getSalt());
        } else {
            return new SaltResponse("");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}