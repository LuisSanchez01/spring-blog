package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Movie;
import com.codeup.springblog.models.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleIsLikeIgnoreCase(String title);
    List<Movie> findByTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String title, String description);

    List<Movie> findByIdEquals(Long id);
}
