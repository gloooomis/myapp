package com.crud.myapp.controller;

import com.crud.myapp.model.Film;
import com.crud.myapp.model.Users;
import com.crud.myapp.service.FilmService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    @GetMapping
    public String findAll(Model model, HttpSession session){
        List<Film> films = filmService.getAllFilms();
        Users currentUser = (Users) session.getAttribute("user");

        model.addAttribute("films", films);
        model.addAttribute("currentUser", currentUser);
        return "film-list";
    }



    @GetMapping("/film-create")
    public String showCreateFilmForm(Model model) {
        model.addAttribute("film", new Film());
        return "film-create"; // имя шаблона (HTML)
    }

    @PostMapping("/film-create")
    public String createFilm(@ModelAttribute Film film, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        film.setUser(currentUser);
        filmService.createFilm(film);
        return "redirect:/films";
    }






    @GetMapping("/film-delete/{name}")
    public String deleteUser(@PathVariable("name") String nameFilm, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        Film film = filmService.getFilmByName(nameFilm);

        if (film != null && film.getUser().getLoginUser().equals(currentUser.getLoginUser())) {
            filmService.deleteFilm(nameFilm);
        }

        return "redirect:/films";
    }


    @GetMapping("/film-update/{name}")
    public String updateFilmForm(@PathVariable("name") String nameFilm, Model model){
        Film film = filmService.getFilmByName(nameFilm);
        model.addAttribute("film", film);
        return "/film-update";
    }
    @PostMapping("/film-update")
    public String updateFilm(Film film, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        Film existing = filmService.getFilmByName(film.getNameFilm());

        if (existing != null && existing.getUser().getLoginUser().equals(currentUser.getLoginUser())) {
            film.setUser(existing.getUser());
            filmService.createFilm(film);
        }

        return "redirect:/films";
    }

}
