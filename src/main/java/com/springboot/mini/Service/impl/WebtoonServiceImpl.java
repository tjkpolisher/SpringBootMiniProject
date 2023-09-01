package com.springboot.mini.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mini.Service.WebtoonService;
import com.springboot.mini.data.dto.WebtoonDto;
import com.springboot.mini.data.dto.WebtoonRankDto;
import com.springboot.mini.data.entity.Webtoon;
import com.springboot.mini.data.entity.WebtoonRank;
import com.springboot.mini.data.repository.WebtoonRankRepository;
import com.springboot.mini.data.repository.WebtoonRepository;
import com.springboot.mini.data.repository.WebtoonSearchRepository;

@Service
public class WebtoonServiceImpl implements WebtoonService{
    private WebtoonRepository webtoonRepository;
    private WebtoonSearchRepository webtoonSearchRepository;
    private WebtoonRankRepository webtoonRankRepository;

    @Autowired
    public WebtoonServiceImpl(WebtoonRepository webtoonRepository, WebtoonSearchRepository webtoonSearchRepository, WebtoonRankRepository webtoonRankRepository){
        this.webtoonRepository = webtoonRepository;
        this.webtoonSearchRepository = webtoonSearchRepository;
        this.webtoonRankRepository = webtoonRankRepository;
    }

    // 전체 조회
    @Override
    public List<WebtoonDto> getWebtoonAll(){
        List<Webtoon> webtoons = webtoonRepository.findAll();
        return webtoons.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // 웹툰 상세 페이지
    @Override
    public List<WebtoonDto> getWebtoonByWebtoonId(Integer webtoonId){
        List<Webtoon> webtoons = webtoonRepository.queryByWebtoonId(webtoonId);
        return webtoons.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // 검색
    @Override
    public List<WebtoonDto> searchWebtoonsByEvery(String every) {
        List<Webtoon> webtoons = webtoonSearchRepository.queryByEvery(every);
        return webtoons.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public  List<WebtoonRankDto> showRankByGenre(String genre){
        List<WebtoonRank> webtoonRanks = webtoonRankRepository.queryByGenre(genre);
        return webtoonRanks.stream().map(this::convertToDTO2).collect(Collectors.toList());
    }

    private WebtoonDto convertToDTO(Webtoon webtoon){
        WebtoonDto dto = new WebtoonDto();
        dto.setWebtoonId(webtoon.getWebtoonId());
        dto.setTitle(webtoon.getTitle());
        dto.setWriter(webtoon.getWriter());
        dto.setPainter(webtoon.getPainter());
        dto.setAge(webtoon.getAge());
        dto.setFavorite(webtoon.getFavorite());
        dto.setStarScore(webtoon.getStarScore());
        dto.setThumbnailUrl(webtoon.getThumbnailUrl());
        dto.setPublishDay(webtoon.getPublishDay());
        dto.setHashTag(webtoon.getHashTag());
        return dto;
    }

    private WebtoonRankDto convertToDTO2(WebtoonRank webtoonRank){
        WebtoonRankDto dto = new WebtoonRankDto();
        dto.setWebtoonId(webtoonRank.getWebtoonId());
        dto.setTitle(webtoonRank.getTitle());
        dto.setThumbnailUrl(webtoonRank.getThumbnailUrl());
        dto.setWebtoonRank(webtoonRank.getWebtoonRank());
        return dto;
    }
}
