package com.rt.base.business.form.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangq
 */
@Data
@ApiModel(value="DemoMqForm",description="MQ入参Bean")
public class DemoMqForm {

    @ApiModelProperty(value="业务ID",name="businessId")
    private String businessId;

    @ApiModelProperty(value="消息内容",name="body",example = "json格式")
    private Object body;
}
