package com.codeup.springblog.controllers;

import com.codeup.springblog.models.MovieRating;
import com.codeup.springblog.repositories.MovieRatingRepository;
import com.codeup.springblog.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies/ratings")
public class MovieRatingController {


    private MovieRepository movieDao;
    private MovieRatingRepository movieRatingDao;

    public MovieRatingController(MovieRepository movieDao, MovieRatingRepository movieRatingDao) {
        this.movieDao = movieDao;
        this.movieRatingDao = movieRatingDao;
    }

    @PostMapping("/{id}")
    public String addRating(
            @PathVariable Long id,
            @RequestParam(name="rating") Long rating
    ) {

        movieRatingDao.save(new MovieRating(5,movieDao.getById(id)));

        return "redirect:/movies";
    }


}
