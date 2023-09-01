package com.springboot.mini.data.dto;

import com.springboot.mini.data.entity.Review;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor
public class ReviewForm {
    private Long id;
    private Integer webtoonId;
    private String title; // 제목
    private String content; // 내용

    public Review toEntity() {
        return new Review(id, webtoonId, title, content);
    }
}
