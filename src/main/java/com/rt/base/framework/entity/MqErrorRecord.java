package com.rt.base.framework.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wq
 * @since 2020-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MqErrorRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String messageId;

    private String messageContent;

    private String errorReason;

    private String createTime;

    private String createUserId;

}
