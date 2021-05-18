package com.rt.base.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rt.base.framework.entity.MqErrorRecord;
import com.rt.base.framework.entity.MqSuccessRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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
public interface MqSuccessRecordMapper extends BaseMapper<MqSuccessRecord> {

    /**
     * MQ成功记录
     * @param successRecord MQ成功记录
     * @return 写入条数
     */
    int insertMqSuccessRecord(MqSuccessRecord successRecord);

    List<MqSuccessRecord> selectSuccessList();
}
