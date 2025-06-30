package com.crud.myapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "series")
public class Series {
    @Id
    @Column(name = "name_series", length = 100)
    private String nameSeries;

    @ManyToOne
    @JoinColumn(name = "name_user", nullable = false)
    @JsonBackReference
    private Users user;

    @Column(name = "genre", length = 50, nullable = false)
    private String genre;

    @Column(name = "release_year")
    @Max(value = 2025, message = "Год выпуска не раньше 2026")
    private int releaseYear;

    @Column(name = "seasons")
    @Positive(message = "Сезонов должно быть больше 0")
    private int seasons;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "grade")
    @Min(value = 1, message = "Оценка от 1 до 10")
    @Max(value = 10, message = "Оценка от 1 до 10")
    private int grade;
}
