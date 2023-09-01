package com.springboot.mini.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class WebtoonRank {
    @Id
    private Long numb;

    @Column
    private String genre;

    @Column
    private Integer webtoonId;

    @Column
    private String title;

    @Column
    private String thumbnailUrl;

    @Column
    private String webtoonRank;

    public WebtoonRank toEntity(){
        return new WebtoonRank(numb,genre,webtoonId,title,thumbnailUrl,webtoonRank);
    }
}
