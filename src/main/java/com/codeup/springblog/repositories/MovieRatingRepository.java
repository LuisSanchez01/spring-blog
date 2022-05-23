package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Movie;
import com.codeup.springblog.models.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {
    List<MovieRating> findByMovie_IdEquals(Long id);



}
