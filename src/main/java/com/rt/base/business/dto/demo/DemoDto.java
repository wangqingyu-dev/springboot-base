package com.rt.base.business.dto.demo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wangq
 */
@Data
public class DemoDto {

    private String name;

    private String code;

    @ApiModelProperty(value="Redis缓存Bean",name="redisDto")
    private DemoRedisDto redisDto;

    @ApiModelProperty(value="提示信息Bean",name="messageDto")
    private DemoMessageDto messageDto;

    @ApiModelProperty(value="处理成功的消息记录",name="mqList")
    private List<DemoMqDto> mqList;
}
