package com.rt.base.business.form.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangq
 */
@Data
@ApiModel(value="DemoMessageForm",description="国际化入参Bean")
public class DemoMessageForm {

    @ApiModelProperty(value="消息编号",name="code")
    private String code;

    @ApiModelProperty(value="语言编码",name="language",example = "zh")
    private String language;

    @ApiModelProperty(value="国家地区编码",name="country",example = "CN/TW")
    private String country;
}
