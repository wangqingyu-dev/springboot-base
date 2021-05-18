package com.rt.base.mytest.model;

import lombok.Data;

/**
 * @author liW
 * @version 1.0
 * @date 2019-7-26 15:19
 */
@Data
public class SqlCreateModel extends BaseModel {

    // 新建表
    private boolean createTable;

    // 新建字段
    private boolean createColumn;

    // 新建索引
    private boolean createInd;

    // 长度，类型
    private boolean updateType;

    // 注释
    private boolean updateComment;

    // 默认值
    private boolean updateDefault;

    // 删除表
    private boolean deleteTable;

    // 删除字段
    private boolean deleteColumn;

    // 删除索引
    private boolean deleteInd;

    public SqlCreateModel() {
    }

    public SqlCreateModel(boolean createTable, boolean createColumn, boolean createInd, boolean updateType, boolean updateComment, boolean updateDefault, boolean deleteTable, boolean deleteColumn, boolean deleteInd) {
        this.createTable = createTable;
        this.createColumn = createColumn;
        this.createInd = createInd;
        this.updateType = updateType;
        this.updateComment = updateComment;
        this.updateDefault = updateDefault;
        this.deleteTable = deleteTable;
        this.deleteColumn = deleteColumn;
        this.deleteInd = deleteInd;
    }
}
