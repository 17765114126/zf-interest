package com.example.springboot.templates;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PagingResult<T> extends BaseResult<List<T>> implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final int DEFAULT_PAGE_NUM = 1;
    @ApiModelProperty(value = "当前页", example = "1")
    private int pageNum;
    @ApiModelProperty(value = "每页条数", example = "10")
    private int pageSize = 10;
    @ApiModelProperty(value = "总数")
    private long total;
    @ApiModelProperty(value = "开始")
    private int start;


    public Page<T> toPage() {
        return new Page(pageNum, pageSize);
    }

    public PagingResult(List<T> body, int pageIndex, int pageSize, long total) {
        this.setData(body);
        this.pageNum = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.setSuccess(true);
        this.setRespCode("20000");
        this.setRespMsg("成功");
    }


}
