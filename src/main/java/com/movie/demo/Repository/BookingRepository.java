package com.movie.demo.Repository;

import com.movie.demo.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  BookingRepository extends JpaRepository<Booking,Long>
{

}
