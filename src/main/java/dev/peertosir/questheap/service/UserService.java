package dev.peertosir.questheap.service;

import dev.peertosir.questheap.domain.auth.User;

public interface UserService {
    User saveUser(User user);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);
}
