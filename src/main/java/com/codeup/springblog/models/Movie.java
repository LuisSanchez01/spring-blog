package com.codeup.springblog.models;
import com.codeup.springblog.repositories.MovieRepository;
import org.hibernate.annotations.OnDelete;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    private String movieCategories;
    private String description;
    @Column(nullable = false)
    private String director;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private String coverImage;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "movie")
    private List<MovieRating> ratings;


    public Movie(String title, String movieCategories, String description, String director, String coverImage) {
        this.title = title;
        this.movieCategories = movieCategories;
        this.description = description;
        this.director = director;
        this.coverImage = coverImage;
    }

    public Movie(){};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieCategories() {
        return movieCategories;
    }

    public void setMovieCategories(String movieCategories) {
        this.movieCategories = movieCategories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<MovieRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<MovieRating> ratings) {
        this.ratings = ratings;
    }

    public Movie setId(Long id) {
        this.id = id;
        return this;
    }

    public void updateAll(Movie movie) {

        this.title = movie.title;
        this.movieCategories = movie.movieCategories;
        this.description = movie.description;
        this.director = movie.director;
        this.coverImage = movie.coverImage;

        for (MovieRating rating : this.ratings) {

            System.out.println("rating = " + rating);

            MovieRating ratingMatch =  movie.ratings.stream()
                    .filter(Objects::nonNull)
                    .filter(o -> o.getId() == rating.getId())
                    .findFirst()
                    .orElse(null);


            if(ratingMatch != null) rating.setUserRating(ratingMatch.getUserRating());

        }


        this.ratings = movie.ratings;

    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", movieCategories='" + movieCategories + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }
}

