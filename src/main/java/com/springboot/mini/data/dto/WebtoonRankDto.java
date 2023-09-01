package com.springboot.mini.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // 롬복 어노테이션의 모두를 포괄하는 어노테이션
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WebtoonRankDto {
    private Long numb;
    private Integer webtoonId;
    private String title;
    private String thumbnailUrl;
    private String webtoonRank;
}
