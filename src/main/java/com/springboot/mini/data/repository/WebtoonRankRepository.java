package com.springboot.mini.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.mini.data.entity.WebtoonRank;


public interface WebtoonRankRepository extends JpaRepository<WebtoonRank,String>{
    @Query("SELECT wr FROM WebtoonRank wr WHERE wr.genre = :genre")
    List<WebtoonRank> queryByGenre(String genre);
}
