package com.rt.base.mytest.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 页面配置
 */
@Data
public class TableModel extends BaseModel {

    // 字段配置
    public List<FieldModel> fieldModelList;
    // 索引
//    public List<IndModel> indList;
    public Map<String, List<IndModel> > indMap;
    // 文件名称（中文）
    public String fileNameCh;
    // 文件名称（英文）
    public String fileNameEn;
    // 文件中文名称
    public String tableTitle;
    // 文件名称（数据库表名）
    public String tableName;
    // 表名relation
    public String tableRelation;
    // 文件路径
    public String filePath;
    // 文件序列化ID
    public String serialVersionUID;

    public TableModel() {
    }
    public TableModel(String fileNameCh) {
        this.fileNameEn = fileNameCh;
    }
    // 生成脚本用
    public TableModel(String tableName, List<FieldModel> fieldModelList, String tableTitle) {
        this.fieldModelList = fieldModelList;
        this.tableTitle = tableTitle;
        this.tableName = tableName;
    }

}
