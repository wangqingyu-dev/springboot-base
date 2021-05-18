package com.rt.base.business.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rt.base.business.dto.demo.DemoDto;
import com.rt.base.business.dto.demo.DemoMqDto;
import com.rt.base.business.entity.TbDemo;
import com.rt.base.business.mapper.TbDemoMapper;
import com.rt.base.business.service.DemoService;
import com.rt.base.business.form.demo.DemoForm;
import com.rt.base.business.common.utils.CommonUtil;
import com.rt.base.business.common.utils.DateUtils;
import com.rt.base.framework.entity.MqErrorRecord;
import com.rt.base.framework.entity.MqSuccessRecord;
import com.rt.base.framework.mapper.MqErrorRecordMapper;
import com.rt.base.framework.mapper.MqSuccessRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangq
 */
@Service
public class DemoServiceImpl extends ServiceImpl<TbDemoMapper, TbDemo> implements DemoService {

    @Autowired
    private MqErrorRecordMapper errorRecordMapper;
    @Autowired
    private MqSuccessRecordMapper successRecordMapper;

    /**
     * 查询列表
     * --数据源：主数据源-MYSQL
     * @param form 入参
     * @return 数据集合
     */
    @Override
    public List<DemoDto> findList(DemoForm form) {
        List<DemoDto> resultList = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper<TbDemo>().eq("id", "1");
        List<TbDemo> demoList = list(queryWrapper);
        if (demoList != null) {
            for (TbDemo tbDemo:demoList) {
                DemoDto demoDto = new DemoDto();
                demoDto.setName(tbDemo.getName());
                demoDto.setCode(tbDemo.getCode());
                resultList.add(demoDto);
            }
        }
        return resultList;
    }

    /**
     * 写入MQ消费失败记录
     * --数据源：SQLite
     * @param errorRecord 失败记录
     * @return
     */
    @Override
    @DS("sqlite")
    public Integer createErrorRecord(MqErrorRecord errorRecord){

        MqErrorRecord record = new MqErrorRecord();
        record.setMessageId(CommonUtil.getUUID());
        record.setMessageContent("");
        record.setErrorReason("消息体解析失败！");
        record.setCreateTime(DateUtils.parseLocalDateTime2String(LocalDateTime.now()));
        return errorRecordMapper.insertMqErrorRecord(record);
    }

    /**
     * 查询MQ消费成功记录
     * --数据源：SQLite
     * @return 成功记录
     */
    @Override
    @DS("sqlite")
    public List<DemoMqDto> selectSuccessRecord(){
        List<DemoMqDto> result = new ArrayList<>();
        List<MqSuccessRecord> recordList = successRecordMapper.selectSuccessList();
        if (recordList != null) {
            for (MqSuccessRecord record:recordList) {
                DemoMqDto demoMqDto = new DemoMqDto();
                demoMqDto.setMessageId(record.getMessageId());
                demoMqDto.setMessageContent(record.getMessageContent());
                demoMqDto.setCreateTime(record.getCreateTime());
                demoMqDto.setCreateUserId(record.getCreateUserId());
                demoMqDto.setRemark(record.getRemark());
                result.add(demoMqDto);
            }
        }
        return result;
    }
}
