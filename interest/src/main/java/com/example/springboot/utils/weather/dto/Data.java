package com.example.springboot.utils.weather.dto;

import java.util.List;

/**
 * Auto-generated: 2021-04-17 16:23:24
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@lombok.Data
public class Data {

    private Yesterday yesterday;
    private String city;
    private List<Forecast> forecast;
    private String ganmao;
    private String wendu;

}