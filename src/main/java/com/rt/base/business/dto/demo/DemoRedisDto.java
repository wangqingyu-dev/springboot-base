package com.rt.base.business.dto.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangq
 */
@Data
@ApiModel(value="DemoRedisDto",description="Redis数据")
public class DemoRedisDto {

    @ApiModelProperty(value="键",name="key")
    private String key;

    @ApiModelProperty(value="值",name="value")
    private String value;

}
