package ru.martha.lab2_1createmcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.martha.lab2_1createmcs.domain.Film;
import ru.martha.lab2_1createmcs.sevice.FilmsService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FilmsController {

    private final FilmsService service;

    @Autowired
    public FilmsController(FilmsService service) {
        this.service = service;
    }

    @RequestMapping(path = "/films", method = RequestMethod.GET)
    public List<Film> getAll() {
        return service.gelAllFilms();
    }

    @RequestMapping(path = "/films/{id}", method = RequestMethod.GET)
    public Film getFilmById(@PathVariable("id") Long id) {
        return service.getFilmById(id);
    }

    @RequestMapping(path = "/films/create", method = RequestMethod.POST)
    @ResponseBody
    public Film createFilm(@RequestBody Film film) {
        return service.createFilm(film);
    }

    @RequestMapping(path = "/films/{id}/remove", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeFilm(@PathVariable("id") Long id) {
        service.removeFilm(id);
    }

    @RequestMapping(path = "/films/{id}/edit", method = RequestMethod.PATCH)
    public Film editFilm(@PathVariable("id") Long id, @RequestBody Film film) {
        return service.editFilm(id, film);
    }
}
