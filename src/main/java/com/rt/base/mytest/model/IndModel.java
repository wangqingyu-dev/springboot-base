package com.rt.base.mytest.model;

import lombok.Data;

/**
 * 索引
 *
 * @author liW
 * @version 1.0
 * @date 2019-7-22 9:39
 */
@Data
public class IndModel extends BaseModel {

//    select S.INDEX_NAME, S.TABLE_NAME, S.COLUMN_NAME, S.COLUMN_POSITION, S.DESCEND

    // 索引名称
    public String indexName;
    // 表名
    public String tableName;
    // 字段名
    public String cloumnName;
    // 顺序
    public String columnPosition;
    // 排序方式
    public String descend;

}
