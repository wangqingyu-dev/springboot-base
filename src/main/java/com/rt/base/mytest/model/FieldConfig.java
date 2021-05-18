package com.rt.base.mytest.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * excel工具类
 */
public class FieldConfig {

    // 导入配置字段
    @Getter
    private List<FieldModel> fieldList;

    // sheet页名称
    @Getter
    private String sheetName;

    // 导出的数据
    @Getter
    private List<Map> data;

    // 是否字段检测
    @Getter
    private boolean check;

    /**
     * 构造函数--创建配置的List
     */
    public FieldConfig() {
        fieldList = new ArrayList<>();
    }

    /**
     * 构造函数--创建配置的List
     *
     * @param length list的初始长度
     */
    public FieldConfig(int length) {
        fieldList = new ArrayList<>(length);
    }

    /**
     * 合并配置
     *
     * @param fieldConfig 需要合并的配置
     */
    public boolean addAll(FieldConfig fieldConfig) {
        return this.fieldList.addAll(fieldConfig.getFieldList());
    }



    /**
     * 导入--设置是否 进行数据校验
     *
     * @return
     */
    public FieldConfig check() {
        this.check = true;
        return this;
    }







    /**
     * 导出--赋值sheet页名称
     *
     * @param sheetName
     * @return
     */
    public FieldConfig sheetName(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }

    /**
     * 导出--配置导出的数据
     *
     * @param data
     * @return
     */
    public FieldConfig data(List<Map> data) {
        this.data = data;
        return this;
    }


    /**
     * 实体自动转换用--配置数据信息
     *
     * @param fieldName    字段名
     * @param fieldNewName 新对象中的名称
     * @param fieldLength  字段长度
     * @param fieldScale   字段精度
     * @param formatStr    日期格式化
     * @return
     */
    public FieldConfig beanConvert(String fieldName, String fieldNewName, int fieldLength, int fieldScale, String formatStr) {
        FieldModel importField = new FieldModel();
        importField.setName(fieldName);
        importField.setNewName(fieldNewName);
        importField.setLength(String.valueOf(fieldLength));
        importField.setScale(String.valueOf(fieldScale));
        importField.setFormatStr(formatStr);
        this.fieldList.add(importField);
        return this;
    }

//    /**
//     * 提交校验--初始化
//     *
//     * @param fieldName    字段名
//     * @param formulaOrder 运算公式--顺序
//     * @param formula      运算公式
//     * @param fieldScale   字段精度
//     * @param assignment   是否把运算的结果赋值给字段 true: 赋值  false : 校验
//     * @param postNotNull  提交的数据，是否不能为空， true : 不能为空， false : 可为空（默认）
//     * @return
//     */
//    public FieldConfig field(String fieldName, String fieldComment, int formulaOrder, String formula, int fieldLength,
//                             int fieldScale, boolean assignment, boolean postNotNull, boolean postNotEmpty) {
//        FieldModel importField = new FieldModel(fieldName, fieldComment, formulaOrder, formula, fieldLength,
//                fieldScale, assignment, postNotNull, postNotEmpty);
//        this.fieldList.add(importField);
//        return this;
//    }

}
