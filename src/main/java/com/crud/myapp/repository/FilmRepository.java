package com.crud.myapp.repository;

import com.crud.myapp.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, String> {
    List<Film> findByGenre(String genre);
    List<Film> findByUser_LoginUser(String loginUser);
    void deleteByUser_LoginUser(String loginUser);
}