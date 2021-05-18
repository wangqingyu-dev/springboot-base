package com.rt.base.business.dto.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangq
 */
@Data
@ApiModel(value="DemoMqDto",description="MQ消息Bean")
public class DemoMqDto {

    @ApiModelProperty(value="消息ID",name="messageId")
    private String messageId;

    @ApiModelProperty(value="信息内容",name="message")
    private String messageContent;

    @ApiModelProperty(value="备注",name="remark")
    private String remark;

    @ApiModelProperty(value="创建者",name="createUserId")
    private String createUserId;

    @ApiModelProperty(value="创建时间",name="createTime")
    private String createTime;

}
