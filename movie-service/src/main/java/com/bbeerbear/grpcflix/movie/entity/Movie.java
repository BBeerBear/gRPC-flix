package com.bbeerbear.grpcflix.movie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    private int id;
    @Column(name = "release_movie")
    private String title;
    private int year;
    private double rating;
    private String genre;
}
