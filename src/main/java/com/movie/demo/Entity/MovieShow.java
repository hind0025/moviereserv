package com.movie.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class MovieShow {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Movie movie;

    private String theatre;

    private Date showTime;

    private int totalSeats;     // total available seats
    private int bookedSeats;    // seats already booked
}
