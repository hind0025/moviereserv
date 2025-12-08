package com.movie.demo.Repository;

import com.movie.demo.Entity.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieShowRepository extends JpaRepository<MovieShow,Long>
{

}