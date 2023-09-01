package com.springboot.mini.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.mini.data.entity.Webtoon;

public interface WebtoonSearchRepository extends JpaRepository<Webtoon, String>{
    // 검색 기능
    @Query("SELECT t FROM Webtoon t WHERE t.webtoonId like %:every% or t.title like %:every% or t.hashTag like %:every%")
    List<Webtoon> queryByEvery(String every);

}
