package com.movie.demo.Service;

import com.movie.demo.Entity.Booking;
import com.movie.demo.Entity.MovieShow;
import com.movie.demo.Repository.BookingRepository;
import com.movie.demo.Repository.MovieShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService
{
    @Autowired
    private final MovieShowRepository movieShowRepository;
    @Autowired
    private final BookingRepository bookingRepository;

    public String bookSeats(Long showId,int seats) throws RuntimeException
    {
        MovieShow show=movieShowRepository.findById(showId).orElseThrow(()->new RuntimeException("Show not found"));

        int available=show.getTotalSeats()-show.getBookedSeats();
        if(seats>available) {
            return "only" + available + "seats are available";
        }
        show.setBookedSeats(show.getBookedSeats()+seats);
        movieShowRepository.save(show);

        Booking booking = new Booking();
        booking.setShow(show);
        booking.setSeatsBooked(seats);
        bookingRepository.save(booking);

        return "booking successful for "+available +" seats";
    }

}
