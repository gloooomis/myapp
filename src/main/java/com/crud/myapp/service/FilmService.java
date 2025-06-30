package com.crud.myapp.service;

import com.crud.myapp.model.Film;
import com.crud.myapp.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    // Создать новый фильм
    public void createFilm(Film film) {
        filmRepository.save(film);
    }

    // Получить все фильмы
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    // Найти фильм по названию
    public Film getFilmByName(String name) {
        return filmRepository.findById(name).orElse(null);
    }

    // Найти фильмы по жанру
    public List<Film> getFilmsByGenre(String genre) {
        return filmRepository.findByGenre(genre);
    }

    // Найти фильмы пользователя
    public List<Film> getFilmsByUser(String loginUser) {
        return filmRepository.findByUser_LoginUser(loginUser);
    }

    // Обновить фильм
    @Transactional
    public Film updateFilm(String name, Film updatedFilm) {
        Film existingFilm = filmRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        existingFilm.setGenre(updatedFilm.getGenre());
        existingFilm.setReleaseYear(updatedFilm.getReleaseYear());
        existingFilm.setDuration(updatedFilm.getDuration());
        existingFilm.setCountry(updatedFilm.getCountry());
        existingFilm.setGrade(updatedFilm.getGrade());

        return filmRepository.save(existingFilm);
    }

    // Удалить фильм по названию
    public void deleteFilm(String name) {
        filmRepository.deleteById(name);
    }

    // Удалить все фильмы пользователя
    public void deleteAllFilmsByUser(String loginUser) {
        filmRepository.deleteByUser_LoginUser(loginUser);
    }
}
