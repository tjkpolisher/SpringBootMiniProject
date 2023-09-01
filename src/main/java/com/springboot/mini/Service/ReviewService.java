package com.springboot.mini.Service;

import java.util.List;

import com.springboot.mini.data.entity.Review;
import com.springboot.mini.data.dto.ReviewForm;

public interface ReviewService {
    List<ReviewForm> index();
    Review show(Long id);
    Review create(ReviewForm dto);
    Review update(Long id, ReviewForm dto);
    Review delete(Long id);
}
