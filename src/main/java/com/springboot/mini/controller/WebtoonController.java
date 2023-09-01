package com.springboot.mini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.mini.Service.WebtoonService;
import com.springboot.mini.data.dto.WebtoonDto;
import com.springboot.mini.data.dto.WebtoonRankDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebtoonController {
    private final WebtoonService webtoonService;

    @Autowired
    public WebtoonController(WebtoonService webtoonService){
        this.webtoonService = webtoonService;
    }

    // 전체 조회(메인) 페이지 연결
    @GetMapping("/webtoons")
    public String index(Model model){
        log.info("나 여기 있어!!");

        // 1. 모든 데이터 가져오기
        List<WebtoonDto> webtoonDtos = webtoonService.getWebtoonAll();

        // 2. 모델에 데이터 등록
        model.addAttribute("webtoonList", webtoonDtos);

        // 3. 뷰에 전송
        return "webtoons/main";
    }

    // 한 웹툰 선택 시 상세 내용 조회 페이지 연결
    @GetMapping("/webtoons/{webtoonId}")
    public String detail(@PathVariable String webtoonId, Model model){
        log.info("id 값은? : "+ webtoonId);
        
        List<WebtoonDto> webtoonDtos = webtoonService.searchWebtoonsByEvery(webtoonId);
        log.info("Dto : "+webtoonDtos);

        model.addAttribute("webtoons", webtoonDtos);

        return "webtoons/detail";
    }

    // 검색 기능 페이지 연결
    @GetMapping("/webtoons/search/{every}")
    public String show(@PathVariable String every, Model model) {
        List<WebtoonDto> webtoonDtos = webtoonService.searchWebtoonsByEvery(every);

        model.addAttribute("webtoonList", webtoonDtos);
        
        return "webtoons/show";
    }

    @GetMapping("/webtoons/{genre}/rank")
    public String genreRank(@PathVariable String genre, Model model){
        List<WebtoonRankDto> webtoonRankDtos = webtoonService.showRankByGenre(genre);
        model.addAttribute("webtoonRankList",webtoonRankDtos);
        return "webtoons/rank";
    }

}
