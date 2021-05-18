package com.rt.base.mytest.model;

import lombok.Data;

/**
 * 字段配置
 */
@Data
public class FieldModel extends BaseModel {

    //序号
    public String no;
    //隐藏
    public boolean hidden;
    //字段名
    public String name;
    //字段名
    public String newName;
    //字段名
    public String DBName;
    //默认值
    public String defaultValue;
    //字段汉语名称
    public String comment;
    //字段汉语名称（导入用）
    public String comment2;
    //字段长度
    public String length;
    //字段精度
    public String scale;
    //日期格式化
    public String formatStr;
    //字段类型
    public String type;
    //字段类型
    public String DBtype;
    //是否只读
    public boolean readOnly;
    //是否必填
    public boolean must;
    //是否统计
    public boolean statistics;
    //数据库中必填字段
    public boolean DBMust;
    //数据库中主键
    public boolean pk;
    //注解
    public String annotation;
    //其他备注
    public String note;
    //其他备注
    public String tableName;
    //其他备注
    public String tableComment;

}