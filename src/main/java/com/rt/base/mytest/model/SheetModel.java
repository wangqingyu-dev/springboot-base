package com.rt.base.mytest.model;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class SheetModel extends BaseModel {
    public String sqlId;
    public String ms;
    public List<List<DataModel>> datas = new ArrayList<>();
    public void setDatas(List<DataModel> dataModels) {
        datas.add(dataModels);
    }

}
