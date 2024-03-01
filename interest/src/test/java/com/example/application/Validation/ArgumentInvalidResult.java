package com.example.application.Validation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author WuZhi_S
 * @Classname: ArgumentInvalidResult
 * @Description:
 * @Date: 2020/8/4 13:59
 */
@ApiModel(value = "参数绑定异常时返回体")
@Data
public class ArgumentInvalidResult implements Serializable {

    private static final long serialVersionUID = -1471698884305695019L;

    @ApiModelProperty(value = "异常字段名")
    private String field;

    @ApiModelProperty(value = "拒绝值")
    private Object rejectedValue;

    @ApiModelProperty(value = "默认信息")
    private String defaultMessage;

}
