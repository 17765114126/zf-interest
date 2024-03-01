package com.example.application.reptile.Douban;

import lombok.Data;

@Data
public class Movie {

    private String id; //电影的id
    private String  directors;//导演
    private String title;//标题
    private String cover;//封面
    private String rate;//评分
    private String casts;//演员

}
