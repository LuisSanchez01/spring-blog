package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "movie_ratings")
public class MovieRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int userRating;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public MovieRating() {

    }

    public MovieRating( int userRating, Movie movie) {
        this.userRating = userRating;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MovieRating{" +
                "id=" + id +
                ", userRating=" + userRating +
                ", movie=" + movie +
                '}';
    }

}
