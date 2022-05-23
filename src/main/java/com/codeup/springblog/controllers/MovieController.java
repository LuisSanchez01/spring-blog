package com.codeup.springblog.controllers;
import com.codeup.springblog.models.Movie;
import com.codeup.springblog.repositories.MovieRepository;
import com.codeup.springblog.services.MovieRatingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private MovieRepository movieDao;

    private MovieRatingService movieRatingService;

    public MovieController(MovieRepository movieDao,
                           MovieRatingService movieRatingService
    ) {
        this.movieDao = movieDao;
        this.movieRatingService = movieRatingService;
    }

    // Read
    @GetMapping
    public String showMovies(Model model) {

        model.addAttribute("ratingsService",movieRatingService);
        model.addAttribute("movies",movieDao.findAll());
        return "movies/index";
    }


    @GetMapping("/{id}")
    public String showMovie(
            @PathVariable Long id,
            Model model
    ) {
        Movie movie = movieDao.getById(id);

        model.addAttribute("movie", movie);

        return "movies/show";
    }

    // Create
    @GetMapping("/create")
    public String createMovie(Model model) {
        // TODO: Complete this
        model.addAttribute("movie", new Movie());
        return "movies/create";
    }


    @PostMapping("/create")
    public String doCreateMovie(
            @ModelAttribute Movie movie
    ) {
        // TODO: Complete this

        movieDao.save(movie);
        return "movies/index";
    }


    // Update
    @GetMapping("/update/{id}")
    public String updateMovie(
            @PathVariable long id,
            Model model) {

        model.addAttribute("movie", movieDao.getById(id));

        // TODO: Populate a form like the Create form, such that
        // it will allow you to update an existing movie.
        return "movies/create";
    }

    @PostMapping("/update")
    public String doUpdateMovie(
            @ModelAttribute Movie movie
    ) {

        System.out.println("movie.toString() = " + movie.toString());
        Movie dbMovie = movieDao.getById(movie.getId());

        dbMovie.updateAll(movie);

        movieDao.save(dbMovie);

        return "redirect:/movies/" + movie.getId();
    }


    // Delete
    @PostMapping("/delete")
    public String deleteMovie(
            @RequestParam(name="id") long id
    ) {
        // TODO: Add button to the movies index page that will delete the items
        // TODO: button should be in a form, value will be the id of the item.
        // TODO: Send the user back to to the main page.

        movieDao.deleteById(id);

        return "redirect:/movies";
    }




}

