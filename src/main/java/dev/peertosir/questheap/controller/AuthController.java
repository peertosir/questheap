package dev.peertosir.questheap.controller;

import dev.peertosir.questheap.config.auth.jwt.JwtProvider;
import dev.peertosir.questheap.domain.auth.User;
import dev.peertosir.questheap.dto.auth.AuthToken;
import dev.peertosir.questheap.dto.auth.UserDto;
import dev.peertosir.questheap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid UserDto userDto) {
        // #TODO use mapper when UserController will be done
        User user = new User();
        user.setLogin(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userService.saveUser(user);
    }

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public AuthToken getToken(@RequestBody @Valid UserDto userDto) {
        User user = userService.findByLoginAndPassword(userDto.getUsername(), userDto.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthToken(token);
    }

}
