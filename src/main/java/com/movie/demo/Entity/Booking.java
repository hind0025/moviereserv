package com.movie.demo.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MovieShow show;   // linked to show

    private int seatsBooked;

    private String status;
}
