package com.rt.base.mytest.model;

import lombok.Data;

/**
 * @ClassName: PageCreateModel
 * @Author: liwang
 * @Date: 2020-03-05 11:06
 **/
@Data
public class PageCreateModel {

    public PageCreateModel(String bizName) {
        this.bizName = bizName;
    }

    public PageCreateModel(String bizName, boolean bffApi, boolean bffLogic, boolean aggApiApi, boolean aggApiFallback, boolean aggServiceApi, boolean aggServiceLogic) {
        this.bizName = bizName;
        this.bffApi = bffApi;
        this.bffLogic = bffLogic;
        this.aggApiApi = aggApiApi;
        this.aggApiFallback = aggApiFallback;
        this.aggServiceApi = aggServiceApi;
        this.aggServiceLogic = aggServiceLogic;
    }

    private String bizName;
    private String modelType = "service";
    private String bizType = "api";
    private String rootBffPath = System.getProperty("user.dir")
            + "\\bff-after-sales\\src\\main\\java\\com\\gtmc\\glaf\\agg\\aftersales\\%s\\%s";
    private String rootAggPath = System.getProperty("user.dir")
            + "\\agg-after-sales-%s\\agg-after-sales-%s-%s\\src\\main\\java\\com\\gtmc\\glaf\\agg\\aftersales\\%s\\%s";

    private String bffApiPath = String.format(rootBffPath, bizName, "api");
    private String bffLogicPath = String.format(rootBffPath, bizName, "logic");
    private String apiApiPath = String.format(rootAggPath, bizName, bizName, "api", bizName, "api");
    private String apiFallbackPath = String.format(rootAggPath, bizName, bizName, "api", bizName, "fallback");
    private String serviceApiPath = String.format(rootAggPath, bizName, bizName, "service", bizName, "api");
    private String serviceLogicPath = String.format(rootAggPath, bizName, bizName, "service", bizName, "logic");

    private boolean bffApi = true;
    private boolean bffLogic = true;
    private boolean aggApiApi = true;
    private boolean aggApiFallback = true;
    private boolean aggServiceApi = true;
    private boolean aggServiceLogic = true;

}
