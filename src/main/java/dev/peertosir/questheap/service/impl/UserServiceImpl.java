package dev.peertosir.questheap.service.impl;

import dev.peertosir.questheap.domain.auth.User;
import dev.peertosir.questheap.domain.auth.UserRole;
import dev.peertosir.questheap.exception.base.client.BadRequestException;
import dev.peertosir.questheap.repository.auth.UserRepository;
import dev.peertosir.questheap.repository.auth.UserRoleRepository;
import dev.peertosir.questheap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        UserRole role = userRoleRepository.findByName("ROLE_USER");
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new BadRequestException("User with this username already exists");
        }
        user.setUserRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
