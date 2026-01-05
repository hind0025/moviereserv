package com.movie.demo.Service;

import com.movie.demo.Entity.Userinfo;
import com.movie.demo.Repository.UserinfoRepository;
import com.movie.demo.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Configuration
public class Usersignin
{
    private final  UserinfoRepository userinforepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public String checkUserExists(String email,String pass)
    {
        boolean exists=userinforepository.existsByEmail(email);
        if(exists)
        {
            return "user already exists";
        }
        String hashed = passwordEncoder.encode(pass);

            Userinfo newuser=new Userinfo();
            newuser.setEmail(email);
            newuser.setPassword(hashed);
            newuser.setRole("user");
            userinforepository.save(newuser);
            return "user signed in ";

    }
    public String userLogin(String email,String pass)
    {
        boolean exists=userinforepository.existsByEmail(email);
        if(!exists)
        {
            return "no such user exists";
        }
        Userinfo user = userinforepository.findByEmail(email);
        if (user == null) {
            return "no such user exists";
        }

        if (!passwordEncoder.matches(pass, user.getPassword())) {
            return "wrong password";
        }

        // Generate JWT
        return jwtUtil.generateToken(email,user.getRole());

    }
}
