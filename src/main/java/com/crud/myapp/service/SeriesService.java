package com.crud.myapp.service;

import com.crud.myapp.model.Series;
import com.crud.myapp.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeriesService {

    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    // Создать новый сериал
    public Series createSeries(Series series) {
        return seriesRepository.save(series);
    }

    // Получить все сериалы
    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    // Найти сериал по названию
    public Series getSeriesByName(String name) {
        return seriesRepository.findById(name).orElse(null);
    }

    // Найти сериалы по жанру
    public List<Series> getSeriesByGenre(String genre) {
        return seriesRepository.findByGenre(genre);
    }

    // Найти сериалы пользователя
    public List<Series> getSeriesByUser(String loginUser) {
        return seriesRepository.findByUser_LoginUser(loginUser);
    }

    // Обновить сериал
    @Transactional
    public Series updateSeries(String name, Series updatedSeries) {
        Series existingSeries = seriesRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("Series not found"));

        existingSeries.setGenre(updatedSeries.getGenre());
        existingSeries.setReleaseYear(updatedSeries.getReleaseYear());
        existingSeries.setSeasons(updatedSeries.getSeasons());
        existingSeries.setCountry(updatedSeries.getCountry());
        existingSeries.setGrade(updatedSeries.getGrade());

        return seriesRepository.save(existingSeries);
    }

    // Удалить сериал по названию
    public void deleteSeries(String name) {
        seriesRepository.deleteById(name);
    }

    // Удалить все сериалы пользователя
    public void deleteAllSeriesByUser(String loginUser) {
        seriesRepository.deleteByUser_LoginUser(loginUser);
    }
}
