package com.springboot.mini.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Review {
    // 리뷰 게시글 번호 (자동 생성)
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer webtoonId;

    // 게시글 제목
    @Column
    private String title;
    
    // 게시글 내용
    @Column
    private String content;

    public void patch(Review review) {
        if (review.title != null) {
            this.title = review.title;
        }
        if (review.content != null) {
            this.content = review.content;
        }
    }

    public Review toEntity(){
        return new Review(id, webtoonId, title, content);
    }
}
