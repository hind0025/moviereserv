package com.movie.demo.Repository;

import com.movie.demo.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long>
{
}
