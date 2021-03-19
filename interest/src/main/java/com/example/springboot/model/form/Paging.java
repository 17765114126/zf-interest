package com.example.springboot.model.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author chen
 * @create 2020-12-01
 **/
@Data
public class Paging<T extends Serializable> {
    private Long current = 1L;
    private Long size = 10L;
    private List<String> asc;
    private List<String> desc;

    public Page<T> toPage() {
        return new Page(current, size);
    }
}