package com.springboot.mini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.mini.Service.impl.ReviewServiceImpl;
import com.springboot.mini.data.dto.ReviewForm;
import com.springboot.mini.data.entity.Review;
import com.springboot.mini.data.repository.ReviewRepository;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReviewController {
    // @Autowired
    private ReviewServiceImpl reviewService;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService){
        this.reviewService = reviewService;
    }

    // 리뷰 게시판 메인 페이지 (전체 리뷰 리스트 출력)
    @GetMapping("/reviews")
    public String index (Model model){
        log.info("나 여깄어!!!");

        List<ReviewForm> reviewForms = reviewService.index();
        
        model.addAttribute("reviewList", reviewForms);

        return "reviews/index";
    }

    // 새 리뷰 작성 창
    @GetMapping(value = "/reviews/new")
    public String newReviewForm() {
        return "reviews/new";
    }

    // 리뷰 작성 후 게시
    @PostMapping("/reviews/create")
    public String createReview(ReviewForm form) {
        log.info("내가 폼이야! : "+form.toString());
        Review review = form.toEntity();
        Review saved = reviewRepository.save(review);
        log.info(saved.toString());
        return "redirect:/reviews/" + saved.getId();
    }
    
    // 개별 리뷰 열람
    @GetMapping("/reviews/{id}")
    public String show(@PathVariable Long id, Model model) {
        //  터미널 상에 로그로 리뷰 id 번호 출력
        log.info("id = " + id);

        // id를 이용해 데이터 호출
        Review reviewEntity = reviewRepository.findById(id).orElse(null);
        System.out.println(reviewEntity);
        // id가 없을 경우 null을 리턴하고, 그 외의 경우 data를 id 값을 이용해 reviewEntity 변수에 리턴함.

        // 모델에 데이터 추가
        model.addAttribute("reviews", reviewEntity);
        return "reviews/show";
    }

    // @GetMapping("/reviews")
    // public String index(Model model) {
    //     // 1. Bring all of the data
    //     List<Review> reviewEntityList = (List<Review>) reviewRepository.findAll();
    //     // 2. Add data to the model
    //     model.addAttribute("reviewList", reviewEntityList);
    //     // 3. Transmit a view
    //     return "articles/index";
    // }

    @GetMapping("/reviews/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Review reviewEntity = reviewRepository.findById(id).orElse(null);
        model.addAttribute("reviews", reviewEntity);
        return "reviews/edit";
    }

    @PostMapping("/reviews/update")
    public String update(ReviewForm form) {
        Review review = form.toEntity();
        log.info(review.toString());
        Review target = reviewRepository.findById(review.getId()).orElse(null);
        if(target != null) {
            reviewRepository.save(review);
        }
        return "redirect:/reviews/" + review.getId();
    }

    @GetMapping("/reviews/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Review target = reviewRepository.findById(id).orElse(null);
        if (target != null) {
            reviewRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제완료");
        }

        return "redirect:/reviews";
    }
}
