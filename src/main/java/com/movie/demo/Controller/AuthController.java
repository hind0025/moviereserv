package com.movie.demo.Controller;

import com.movie.demo.Service.Usersignin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final Usersignin usersignin;

    @PostMapping("/signup")
    public String signup(@RequestParam String email, @RequestParam String password) {
        return usersignin.checkUserExists(email, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return usersignin.userLogin(email, password);
    }
}
