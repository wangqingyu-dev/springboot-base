package com.rt.base.business.service;

import com.rt.base.business.dto.demo.DemoDto;
import com.rt.base.business.dto.demo.DemoMqDto;
import com.rt.base.business.form.demo.DemoForm;
import com.rt.base.framework.entity.MqErrorRecord;
import com.rt.base.framework.entity.MqSuccessRecord;

import java.util.List;

/**
 * @author wangq
 */
public interface DemoService {

    /**
     * 查询列表示例
     * @param form 入参
     * @return 列表数据
     */
    List<DemoDto> findList(DemoForm form);

    /**
     * 写入MQ消费失败记录
     * @param errorRecord
     * @return 列表数据
     */
    Integer createErrorRecord(MqErrorRecord errorRecord);

    /**
     * 查询MQ消费成功记录
     * @return 列表数据
     */
    List<DemoMqDto> selectSuccessRecord();

}
