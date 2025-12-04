package com.movie.demo.Repository;

import com.movie.demo.Entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserinfoRepository extends JpaRepository<Userinfo,UUID>
{
    boolean findByEmailandPass(String email,String pass);

    boolean existsByEmail(String email);

    Userinfo findByEmail(String email);

}
