package com.crud.myapp.repository;

import com.crud.myapp.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, String> {
    List<Series> findByGenre(String genre);
    List<Series> findByUser_LoginUser(String loginUser);
    void deleteByUser_LoginUser(String loginUser);
}
