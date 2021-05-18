package com.rt.base.business.form.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangq
 */
@Data
@ApiModel(value="DemoRecordForm",description="SQLite入参Bean")
public class DemoRecordForm {

    @ApiModelProperty(value="消息编号",name="messageId")
    private String messageId;

    @ApiModelProperty(value="消息内容",name="messageContent",example = "json格式")
    private String messageContent;

    @ApiModelProperty(value="失败原因",name="errorReason")
    private String errorReason;
}
