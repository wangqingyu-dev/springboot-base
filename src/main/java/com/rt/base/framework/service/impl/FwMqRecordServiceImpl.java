package com.rt.base.framework.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.rt.base.framework.common.utils.CommonUtil;
import com.rt.base.framework.common.utils.DateUtils;
import com.rt.base.framework.entity.MqErrorRecord;
import com.rt.base.framework.entity.MqSuccessRecord;
import com.rt.base.framework.mapper.MqErrorRecordMapper;
import com.rt.base.framework.mapper.MqSuccessRecordMapper;
import com.rt.base.framework.service.FwMqRecordService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author wangq
 */
@Service
public class FwMqRecordServiceImpl implements FwMqRecordService {

    @Autowired
    private MqErrorRecordMapper errorRecordMapper;
    @Autowired
    private MqSuccessRecordMapper successRecordMapper;

    /**
     * 写入MQ消费失败记录
     * @param msgId 消息ID
     * @param body 消息体
     * @return 执行条数
     */
    @Override
    @DS("sqlite")
    public Integer createErrorRecord(String msgId,String body){
        MqErrorRecord errorRecord = new MqErrorRecord();
        errorRecord.setMessageId(msgId);
        errorRecord.setMessageContent(body);
        errorRecord.setCreateTime(DateUtils.parseLocalDateTime2String(LocalDateTime.now()));
        errorRecord.setCreateUserId("sys");
        return errorRecordMapper.insertMqErrorRecord(errorRecord);
    }

    /**
     * 写入MQ消费成功记录
     * @param msgId 消息ID
     * @param body 消息体
     * @param remark 备注
     * @return 执行条数
     */
    @Override
    @DS("sqlite")
    public Integer createSuccessRecord(String msgId,String body,String remark){
        MqSuccessRecord successRecord = new MqSuccessRecord();
        successRecord.setMessageId(msgId);
        successRecord.setMessageContent(body);
        successRecord.setRemark(remark);
        successRecord.setCreateTime(DateUtils.parseLocalDateTime2String(LocalDateTime.now()));
        successRecord.setCreateUserId("sys");
        return successRecordMapper.insertMqSuccessRecord(successRecord);
    }
}
