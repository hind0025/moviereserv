package com.movie.demo.Service;

import com.movie.demo.Entity.Role;
import com.movie.demo.Entity.Userinfo;
import com.movie.demo.Repository.UserinfoRepository;
import com.movie.demo.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Usersignin {

    private final UserinfoRepository userinforepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Signup → ALWAYS USER
    public String signup(String email, String pass) {

        if (userinforepository.existsByEmail(email)) {
            return "user already exists";
        }

        Userinfo newUser = new Userinfo();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(pass));
        newUser.setRole(Role.USER);

        userinforepository.save(newUser);
        return "user signed up successfully";
    }


    public String userLogin(String email, String pass) {

        Userinfo user = userinforepository.findByEmail(email);
        if (user == null) {
            return "no such user exists";
        }

        if (!passwordEncoder.matches(pass, user.getPassword())) {
            return "wrong password";
        }


        return jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );
    }
}
