package com.bbeerbear.grpcflix.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendedMovie {
    private String title;
    private int year;
    private double rating;
}
