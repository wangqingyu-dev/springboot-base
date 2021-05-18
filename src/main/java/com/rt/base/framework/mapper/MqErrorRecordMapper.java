package com.rt.base.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rt.base.framework.entity.MqErrorRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-29
 */
@Component
@Mapper
public interface MqErrorRecordMapper extends BaseMapper<MqErrorRecord> {

    /**
     * MQ失败记录
     * @param mqErrorRecord MQ失败记录
     * @return 写入条数
     */
    int insertMqErrorRecord(MqErrorRecord mqErrorRecord);

}
