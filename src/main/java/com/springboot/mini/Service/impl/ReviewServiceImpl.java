package com.springboot.mini.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.springboot.mini.Service.ReviewService;
import com.springboot.mini.data.dto.ReviewForm;
import com.springboot.mini.data.entity.Review;
import com.springboot.mini.data.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewForm> index() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Review show(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public Review create(ReviewForm dto) {
        Review review = dto.toEntity();

        if (review.getId() != null) {
            return null;
        }

        return reviewRepository.save(review);
    }

    @Override
    public Review update(Long id, ReviewForm dto) {
        Review review = dto.toEntity();

        Review target = reviewRepository.findById(id).orElse(null);

        if (target == null || id != review.getId()) {
            return null;
        }

        review.patch(review);
        Review updated = reviewRepository.save(target);
        return updated;
    }

    @Override
    public Review delete(Long id) {
        Review target = reviewRepository.findById(id).orElse(null);
        if (target == null) {
            return null;
        }
        
        reviewRepository.delete(target);
        return target;
    }

    // @Transactional // 이 어노테이션이 지정되어 있을 경우, 메서드 작업을 마칠 때 자동으로 flush() 메서드를 실행하고, 데이터베이스의 레코드를 업데이트하는 쿼리가 실행됩니다.
    // public List<Review> createArticles(List<ReviewForm> dtos) {
    //     List<Review> reviewList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());

    //     // Save into DB
    //     reviewList.stream()
    //         .forEach(review -> reviewRepository.save(review));

    //     reviewRepository.findById(-1L)
    //         .orElseThrow(() -> new IllegalArgumentException("실패"));
        
    //     return reviewList;
    // }

    private ReviewForm convertToDTO(Review review){
    ReviewForm dto = new ReviewForm();
    dto.setId(review.getId());
    dto.setTitle(review.getTitle());
    dto.setContent(review.getContent());
    return dto;
    }
}
