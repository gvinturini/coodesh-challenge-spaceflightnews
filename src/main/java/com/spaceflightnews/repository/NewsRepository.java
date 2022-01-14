package com.spaceflightnews.repository;

import com.spaceflightnews.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {

    List<News> findByOrderByIdDesc();

}
