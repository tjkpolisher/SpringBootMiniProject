package com.springboot.mini.Service;

import java.util.List;

import com.springboot.mini.data.dto.WebtoonDto;
import com.springboot.mini.data.dto.WebtoonRankDto;

public interface WebtoonService {
    // 전체 조회
    List<WebtoonDto> getWebtoonAll();

    // 상세 조회
    List<WebtoonDto> getWebtoonByWebtoonId(Integer webtoonId);

    // 검색
    List<WebtoonDto> searchWebtoonsByEvery(String every);

    // 장르별 랭크
    List<WebtoonRankDto> showRankByGenre(String genre);
}
