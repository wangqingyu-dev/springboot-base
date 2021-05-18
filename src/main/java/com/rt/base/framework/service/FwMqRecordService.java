package com.rt.base.framework.service;

import com.rt.base.framework.entity.MqErrorRecord;
import com.rt.base.framework.entity.MqSuccessRecord;


/**
 * @author wangq
 */
public interface FwMqRecordService {

    /**
     * 写入MQ消费失败记录
     * @param msgId 消息ID
     * @param body 消息体
     * @return 列表数据
     */
    Integer createErrorRecord(String msgId,String body);

    /**
     * 写入MQ消费成功记录
     * @param msgId 消息ID
     * @param body 消息体
     * @param remark 备注
     * @return 列表数据
     */
    Integer createSuccessRecord(String msgId,String body,String remark);
}
