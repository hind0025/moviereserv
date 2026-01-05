package com.movie.demo.Repository;

import com.movie.demo.Entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserinfoRepository extends JpaRepository<Userinfo,UUID>
{
   // boolean findByEmailandPass(String email,String pass);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Userinfo u WHERE u.email = :email AND u.password = :password")
    boolean findByEmailandPass(@Param("email") String email, @Param("password") String password);


    boolean existsByEmail(String email);

    Userinfo findByEmail(String email);

}
