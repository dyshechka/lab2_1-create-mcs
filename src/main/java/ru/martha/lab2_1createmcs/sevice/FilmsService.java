package ru.martha.lab2_1createmcs.sevice;

import org.springframework.stereotype.Service;
import ru.martha.lab2_1createmcs.domain.Film;
import ru.martha.lab2_1createmcs.exception.FilmValidationException;
import ru.martha.lab2_1createmcs.exception.FilmNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.util.StringUtils.*;

@Service
public class FilmsService {

    private final List<Film> films;
    private Long idCounter = 0L;

    public FilmsService() {
        films = new ArrayList<>();
        films.add(new Film(1L, "Тебя здесь никогда не было", "18+", 95, "Великобритания", 7.5));
        films.add(new Film(2L, "Три билборда на границе Эббинга, Миссури", "18+", 115, "США", 8.2));
        films.add(new Film(3L, "Матрица", "16+", 136, "США", 8.7));
        films.add(new Film(4L, "Властелин колец: Братство кольца", "12+", 178, "Новая Зеландия", 8.8));
        films.add(new Film(5L, "Бегущий по лезвию 2049", "18+", 164, "Канада", 8.2));
        for (Film film : films) {
            if (film.getId() > idCounter) {
                idCounter = film.getId() + 1;
            }
        }
    }

    public List<Film> gelAllFilms() {
        return films;
    }

    public Film getFilmById(Long id) {
        for (Film film : films) {
            if (id.equals(film.getId())) {
                return film;
            }
        }
        throw new FilmNotFoundException();
    }

    public Film createFilm(Film film) {
        films.add(film);
        film.setId(idCounter);
        idCounter++;
        return film;
    }

    public void removeFilm(Long id) {
        Iterator<Film> iterator = films.iterator();
        //noinspection Java8CollectionRemoveIf
        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (id.equals(film.getId())) {
                iterator.remove();
                return;
            }
        }
        throw new FilmNotFoundException();
    }

    public Film editFilm(Long id, Film newFilm) {
        Film film = getFilmById(id);
        if (film == null) {
            throw new FilmNotFoundException();
        }
        if (!isEmpty(newFilm.getName())) {
            film.setName(newFilm.getName());
        }
        if (!isEmpty(newFilm.getAgeRestriction())) {
            film.setAgeRestriction(newFilm.getAgeRestriction());
        }
        if (newFilm.getDuration() != null) {
            if (newFilm.getDuration() <= 0) {
                throw new FilmValidationException("Invalid film duration");
            }
            film.setDuration(newFilm.getDuration());
        }
        if (!isEmpty(newFilm.getCountry())) {
            film.setCountry(newFilm.getCountry());
        }
        if (newFilm.getImdb() != null) {
            if (newFilm.getImdb() <= 0 || newFilm.getImdb() > 10) {
                throw new FilmValidationException("Invalid film imdb");
            }
            film.setImdb(newFilm.getImdb());
        }
        return film;
    }
}
