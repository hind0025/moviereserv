package com.movie.demo.Controller;

import com.movie.demo.DTO.LoginRequest;
import com.movie.demo.Service.Usersignin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final Usersignin usersignin;

//    @PostMapping("/signup")
//    public String signup(@RequestParam String email, @RequestParam String password) {
//        return usersignin.checkUserExists(email, password);
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password) {
//        return usersignin.userLogin(email, password);
//    }

    @PostMapping("/signup")
    public String signup(@RequestBody LoginRequest request) {
        return usersignin.checkUserExists(request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return usersignin.userLogin(request.getEmail(), request.getPassword());
    }

}
