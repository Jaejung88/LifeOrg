package com.project.life.controllers.movie;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.life.models.movie.Movie;
import com.project.life.services.movie.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {

	private final MovieService MovieService;

	public MovieController(MovieService MovieService) {
		this.MovieService = MovieService;
	}

	@RequestMapping("")
	public String movie(@ModelAttribute("movie") Movie movie) {
		return "my_movies.jsp";
	}

	@RequestMapping(value = "/new_movie", method = RequestMethod.POST)
	public String addMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result) {
		if (result.hasErrors()) {
			return "my_movies.jsp";
		} else {
			MovieService.saveMovie(movie);
			return "redirect:/movies";
		}
	}
}