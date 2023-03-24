package com.bbeerbear.grpcflix.aggregator.service;

import com.bbeerbear.grpcflix.aggregator.dto.RecommendedMovie;
import com.bbeerbear.grpcflix.aggregator.dto.UserGenre;
import com.bbeerbear.grpcflix.common.Genre;
import com.bbeerbear.grpcflix.movie.MovieSearchRequest;
import com.bbeerbear.grpcflix.movie.MovieSearchResponse;
import com.bbeerbear.grpcflix.movie.MovieServiceGrpc;
import com.bbeerbear.grpcflix.user.UserGenreUpdateRequest;
import com.bbeerbear.grpcflix.user.UserResponse;
import com.bbeerbear.grpcflix.user.UserSearchRequest;
import com.bbeerbear.grpcflix.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMovieService {
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieStub;

    public List<RecommendedMovie> getUserMovieSuggestions(String loginId){
        UserSearchRequest userSearchRequest = UserSearchRequest.newBuilder().setLoginId(loginId).build();
        UserResponse userResponse = this.userStub.getUserGenre(userSearchRequest);
        MovieSearchRequest movieSearchRequest = MovieSearchRequest.newBuilder().setGenre(userResponse.getGenre()).build();
        MovieSearchResponse moviesSearchResponse = this.movieStub.getMovies(movieSearchRequest);
        // Convert to movie dto
        return moviesSearchResponse.getMovieList()
                .stream().map(movieDto -> new RecommendedMovie(movieDto.getTitle(), movieDto.getYear(),movieDto.getRating()))
                .collect(Collectors.toList());
    }

    public void setUserGenre(UserGenre userGenre){
        UserGenreUpdateRequest userGenreUpdateRequest = UserGenreUpdateRequest.newBuilder()
                .setLoginId(userGenre.getLoginId())
                .setGenre(Genre.valueOf(userGenre.getGenre().toUpperCase()))
                .build();
        UserResponse userResponse = this.userStub.updateUserGenre(userGenreUpdateRequest);
    }
}
