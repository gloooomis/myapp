package com.crud.myapp.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "name_film", length = 100)
    private String nameFilm;

    @ManyToOne
    @JoinColumn(name = "name_user", nullable = false)
    @JsonBackReference
    private Users user;

    @Column(name = "genre", length = 50, nullable = false)
    private String genre;

    @Column(name = "release_year")
    @Max(value = 2025, message = "Год выпуска не раньше 2026")
    private int releaseYear;

    @Column(name = "duration")
    @Positive(message = "Длительность больше 0")
    private int duration;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "grade")
    @Min(value = 1, message = "Оценка от 1 до 10")
    @Max(value = 10, message = "Оценка от 1 до 10")
    private int grade;
}