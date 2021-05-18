package com.rt.base.business.dto.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangq
 */
@Data
@ApiModel(value="DemoMessageDto",description="提示信息Bean")
public class DemoMessageDto {

    @ApiModelProperty(value="地区",name="local")
    private String local;

    @ApiModelProperty(value="信息内容",name="message")
    private String message;

    @ApiModelProperty(value="信息编码",name="code")
    private String code;

}
