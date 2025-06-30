package com.crud.myapp.controller;


import com.crud.myapp.model.Series;

import com.crud.myapp.model.Users;
import com.crud.myapp.service.SeriesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/series")
public class SeriesController {
    private final SeriesService seriesService;
    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }
    @GetMapping
    public String findAll(Model model, HttpSession session){
        List<Series> series = seriesService.getAllSeries();
        Users currentUser = (Users) session.getAttribute("user");

        model.addAttribute("series", series);
        model.addAttribute("currentUser", currentUser);
        return "series-list";
    }

    @GetMapping("/series-create")
    public String showCreateSeriesForm(Model model) {
        model.addAttribute("series", new Series());
        return "series-create"; // имя шаблона
    }

    @PostMapping("/series-create")
    public String createSeries(@ModelAttribute Series series, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        series.setUser(currentUser);
        seriesService.createSeries(series);
        return "redirect:/series";
    }




    @GetMapping("/series-delete/{name}")
    public String deleteUser(@PathVariable("name") String nameSeries, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        Series series = seriesService.getSeriesByName(nameSeries);

        if (series != null && series.getUser().getLoginUser().equals(currentUser.getLoginUser())) {
            seriesService.deleteSeries(nameSeries);
        }

        return "redirect:/series";
    }

    @GetMapping("/series-update/{name}")
    public String updateSeriesForm(@PathVariable("name") String nameSeries, Model model){
        Series series = seriesService.getSeriesByName(nameSeries);
        model.addAttribute("series1", series);
        return "/series-update";
    }
    @PostMapping("/series-update")
    public String updateSeries(Series series, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        Series existing = seriesService.getSeriesByName(series.getNameSeries());

        if (existing != null && existing.getUser().getLoginUser().equals(currentUser.getLoginUser())) {
            series.setUser(existing.getUser());
            seriesService.createSeries(series);
        }

        return "redirect:/series";
    }

}
