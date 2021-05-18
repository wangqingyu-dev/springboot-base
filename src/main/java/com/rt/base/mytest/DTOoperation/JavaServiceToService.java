package com.rt.base.mytest.DTOoperation;

import com.rt.base.mytest.stringtext.StringText;
import com.rt.base.mytest.util.DataUtil;
import com.rt.base.mytest.util.DevFileUtil;

/**
 * @author :static
 * 一键从controller到数据库增删改查
 */
public class JavaServiceToService {
    private static String servicePath = "D:\\tianyan\\src\\main\\java\\com\\eccoal\\tianyan\\business\\service\\";
    private static String serviceImplPath = "D:\\tianyan\\src\\main\\java\\com\\eccoal\\tianyan\\business\\service\\impl\\";
    private static String dtoExtImpPath = "D:\\tianyan\\src\\main\\java\\com\\eccoal\\tianyan\\business\\dto\\";

    public static void main(String[] args) {
        //TODO 如果不需要创建新文件就不用改动packagePath
        String packagePath = "com.eccoal.tianyan.business";
        String note = "企业招投标信息";
        String url = "/open/m/bids/2.0";
        //方法名及注释
        String[] strings = {
                "selectFromTy 调用天眼：查询 "+note+"",
                "selectFromDB DB数据：查询 "+note+""
        };
        //实体类的类名
        String entryName = "Bids";

//      createFlag     是否生成文件 1 生成  0在控制台显示
        String createFlag = "1";

//      ipageFlag     是否查询list 1 是  0单一
        String ipageFlag = "1";
//      nested     是否返回dto有嵌套的dto 1 有  0没有
        String nested = "0";
        writeDtoExt(dtoExtImpPath,packagePath,entryName,nested);
        packagePath += ".service;";
        writeService(servicePath, packagePath, entryName, strings, createFlag);
        packagePath = packagePath.replace(".service;",".service.impl;");
        writeServiceImpl(serviceImplPath, packagePath, entryName, strings, createFlag, ipageFlag);

        String commonAnnotation = "";
        String entryFirstName = entryName.replace(entryName.substring(0, 1), entryName.substring(0, 1).toLowerCase());
        commonAnnotation += "   /**\n" +
                "     * "+note+" 接口URL\n" +
                "     */\n"+
                "     @Value(\"${tianyan.api."+entryFirstName+"_url}\")\n";
        commonAnnotation +=  "   private String "+entryFirstName+"Url;\n";
        commonAnnotation += "# "+note+"\n";
        commonAnnotation += entryFirstName+"_url: " +url+"?pageSize={0}&keyword={1}&pageNum={2}";
        System.out.println(commonAnnotation);
    }

    /**
     * Dto Ext写到指定目录
     *
     * @param dtoExtImpPath dto ext 地址
     * @param packagePath   包的地址
     * @param entryName     实体类name
     * @param nested     是否返回dto有嵌套的dto 1 有  0没有
     */
    private static void writeDtoExt(String dtoExtImpPath, String packagePath, String entryName,String nested) {
        //代码
        packagePath = packagePath + ".dto;";
        String commonAnnotation = "";
        commonAnnotation += "package " + packagePath + "\n";
        commonAnnotation += "import lombok.Data;" + "\n";
        commonAnnotation += "import lombok.EqualsAndHashCode;" + "\n";
        commonAnnotation += "import lombok.experimental.Accessors;" + "\n";
        commonAnnotation += "/**\n" +
                " *@author wqy\n" +
                " */\n" +
                " @Data\n" +
                " @EqualsAndHashCode(callSuper = false)\n" +
                " @Accessors(chain = true)\n" +
                " public class " + entryName + "Dto" + " implements  Serializable " + "{\n";
        String string = "";
        if ("0".equals(nested)){
            string = StringText.updateString();
        }else{
            string = StringText.updateStringDto();
        }
        commonAnnotation += string;
        commonAnnotation += "}";
        DevFileUtil.createFile(commonAnnotation, dtoExtImpPath + entryName + "Dto.java");

    }



    /**
     * Service写到指定目录
     *
     * @param servicePath
     * @param packagePath 包的地址
     * @param entryName   实体类name
     * @param strings     方法名数组遍历生成
     * @param createFlag  是否生成文件 1 生成  0在控制台显示
     */
    private static void writeService(String servicePath, String packagePath, String entryName, String[] strings, String createFlag) {

        //代码
        String commonAnnotation = "";
        commonAnnotation += "package " + packagePath + "\n";
        commonAnnotation += "import com.eccoal.tianyan.business.dto." + entryName + "Dto;\n";
        commonAnnotation += "import java.util.List;\n";
        commonAnnotation += "/**\n" +
                " *@author wqy\n" +
                " */\n" +
                " public interface " + entryName + "Service {\n";

        for (int i = 0; i < strings.length; i++) {
            if (!DataUtil.isEmpty(strings)) {
                String name = strings[i].split(" ")[0];
                String type = "public List<" + entryName + "Dto>";
                String note = strings[i].split(" ")[1] + strings[i].split(" ")[2];

                commonAnnotation += "   /**\n" +
                        "     * " + note + "\n" +
                        "     * @param keyword 关键字\n" +
                        "     * @return " + entryName + "Dto\n" +
                        "     */\n" +
                        "    " + type + " " + name + "(String keyword);\n";
            }
        }
        commonAnnotation += "\n" +
                "}";

        if ("0".equals(createFlag)) {
            System.out.println(commonAnnotation);
        } else {
            DevFileUtil.createFile(commonAnnotation, servicePath + entryName + "Service.java");
        }
    }

    /**
     * ServiceImpl写到指定目录
     *
     * @param serviceImplPath ServiceImpl写在哪的地址
     * @param packagePath     包的地址
     * @param entryName       实体类name
     * @param strings         方法名数组遍历生成
     * @param createFlag      是否生成文件 1 生成  0在控制台显示
     * @param ipageFlag       ipageFlag     是否查询list 1 是  0单一
     */
    private static void writeServiceImpl(String serviceImplPath, String packagePath, String entryName, String[] strings, String createFlag, String ipageFlag) {
        //代码
        String entryFirstName = entryName.replace(entryName.substring(0, 1), entryName.substring(0, 1).toLowerCase());
        String commonAnnotation = "";
        commonAnnotation += "package " + packagePath + "\n";
        commonAnnotation += "import com.alibaba.fastjson.JSONArray;\n";
        commonAnnotation += "import com.alibaba.fastjson.JSONObject;\n";
        commonAnnotation += "import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.common.Constants;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.common.em.ResultCode;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.common.restTemplate.RestTemplateResponse;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.common.restTemplate.RestTyTemplateUtil;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.common.utils.CommonUtil;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.dto.RestResultDto;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.dto." + entryName + "Dto;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.entity.T" + entryName + ";\n";
        commonAnnotation += "import com.eccoal.tianyan.business.mapper.T" + entryName + "Mapper;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.service.CallRecordService;\n";
        commonAnnotation += "import com.eccoal.tianyan.business.service." + entryName + "Service;\n";
        commonAnnotation += "import com.eccoal.tianyan.framework.config.ConfigProperties;\n";
        commonAnnotation += "import lombok.extern.slf4j.Slf4j;\n";
        commonAnnotation += "import org.springframework.beans.BeanUtils;\n";
        commonAnnotation += "import org.springframework.beans.factory.annotation.Autowired;\n";
        commonAnnotation += "import org.springframework.stereotype.Service;\n";

        commonAnnotation += "import java.text.MessageFormat;\n";
        commonAnnotation += "import java.time.LocalDateTime;\n";
        commonAnnotation += "import java.util.ArrayList;\n";
        commonAnnotation += "import java.util.HashMap;\n";
        commonAnnotation += "import java.util.List;\n";
        commonAnnotation += "/**\n" +
                " *@author wqy\n" +
                " *@Description API\n" +
                " *@Date 2021-01-13\n" +
                " *@Version V1.0\n" +
                " */\n" +
                " @Slf4j\n" +
                " @Service\n" +
                " public class " + entryName + "ServiceImpl " + " implements " + entryName + "Service" + " {\n" +
                "     @Autowired\n" +
                "     private T" + entryName + "Mapper " + entryFirstName + "Mapper;\n" +
                "     @Autowired\n" +
                "     private CallRecordService " + "callRecordService;\n" +
                "     @Autowired\n" +
                "     private ConfigProperties configProperties;\n";
        String selectFromTy = "";
        if ("0".equals(ipageFlag)) {
            selectFromTy = createSelectFromTY(strings, entryName);
        } else {
            selectFromTy = createSelectFromTY_ipage(strings, entryName);
        }
        String selectFromDB = createSelectFromDB(strings, entryName);

        String saveToDb = createSaveToDb(entryName, strings[0].split(" ")[2] + "写入DB");
        commonAnnotation += selectFromTy + "\n";
        commonAnnotation += saveToDb + "\n";
        commonAnnotation += selectFromDB + "\n";
        commonAnnotation += "\n" +
                "}";

        //0在控制台显示
        if ("0".equals(createFlag)) {
            System.out.println(commonAnnotation);
        } else {
            DevFileUtil.createFile(commonAnnotation, serviceImplPath + entryName + "ServiceImpl.java");
        }
    }

    /**
     * 查询TY 数据方法
     *
     * @param strings   查询
     * @param entryName 实体类名称
     * @return
     */
    private static String createSelectFromTY(String[] strings, String entryName) {
        String entryFirstName = entryName.replace(entryName.substring(0, 1), entryName.substring(0, 1).toLowerCase());
        String commonAnnotation = "";
        String name = strings[0].split(" ")[0];
        String type = "public List<" + entryName + "Dto>";
        String note = strings[0].split(" ")[1] + strings[0].split(" ")[2];

        commonAnnotation += "   /**\n" +
                "     * " + note + "\n" +
                "     * @param keyword 关键字\n" +
                "     * @return " + entryName + "Dto\n" +
                "     */\n" +
                "     @Override" +
                "    " + type + " " + name + "(String keyword){\n";
        commonAnnotation += "List<" + entryName + "Dto> " + entryFirstName + "List = null;\n" +
                "\t\t// 组装URL\n" +
                "\t\tString url = configProperties.getTianyanPath() + configProperties.get" + entryName + "Url();\n" +
                "\t\turl = MessageFormat.format(url,keyword);\n" +
                "\t\tRestTemplateResponse rtn = RestTyTemplateUtil.getRestTemplateRequest(url,RestResultDto.class);\n" +
                "\t\tRestResultDto resultInfoDto = null;\n" +
                "\t\tlog.info(\"调用天眼[质押走势]API，Keyword:{} ResultCode：{}\",keyword,rtn.getResultCode());\n" +
                "\t\t// 调用成功\n" +
                "\t\tif (ResultCode.SUCCESS.getCode().equals(rtn.getResultCode())){\n" +
                "\t\t\t// 天眼数据\n" +
                "\t\t\tresultInfoDto = (RestResultDto)rtn.getBody();\n" +
                "\t\t\t// 返回状态\n" +
                "\t\t\tString errorCode = resultInfoDto.getError_code();\n" +
                "\t\t\t// 返回信息\n" +
                "\t\t\tString reason = resultInfoDto.getReason();\n" +
                "\t\t\tif (Constants.ErrorCode.E_000000.equals(errorCode)) {\n" +
                "\t\t\t\t// 调用天眼接口\n" +
                "\t\t\t\t" + entryFirstName + "List = JSONArray.parseArray(JSONObject.toJSONString(resultInfoDto.getResult().get(\"items\")), " + entryName + "Dto.class);\n" +
                "\t\t\t\tthis.saveToDb(" + entryFirstName + "List,keyword);\n" +
                "\t\t\t}\n" +
                "\t\t\t// 调用记录\n" +
                "\t\t\tcallRecordService.saveApiCallRecord(Constants.ApiKey.STOCK_PLEDGE_TREND,keyword,errorCode,reason);\n" +
                "\t\t}\n" +
                "\n" +
                "\t\treturn " + entryFirstName + "List;";
        commonAnnotation += "\n" +
                "}";
        return commonAnnotation;
    }

    /**
     * 查询TY 数据方法
     *
     * @param strings   查询
     * @param entryName 实体类名称
     * @return
     */
    private static String createSelectFromTY_ipage(String[] strings, String entryName) {
        String entryFirstName = entryName.replace(entryName.substring(0, 1), entryName.substring(0, 1).toLowerCase());
        String commonAnnotation = "";
        String name = strings[0].split(" ")[0];
        String type = "public List<" + entryName + "Dto>";
        String note = strings[0].split(" ")[1] + strings[0].split(" ")[2];

        commonAnnotation += "   /**\n" +
                "     * " + note + "\n" +
                "     * @param keyword 关键字\n" +
                "     * @return " + entryName + "Dto\n" +
                "     */\n" +
                "     @Override\n" +
                "    " + type + " " + name + "(String keyword){\n";
        commonAnnotation += "List<" + entryName + "Dto> " + entryFirstName + "List = null;\n" +
                "\t\t// 组装URL\n" +
                "\t\tString url = configProperties.getTianyanPath() + configProperties.get" + entryName + "Url();\n" +
                "\t\turl = MessageFormat.format(url,Constants.MAX_INT,keyword,1);\n" +
                "\t\tRestTemplateResponse rtn = RestTyTemplateUtil.getRestTemplateRequest(url,RestResultDto.class);\n" +
                "\t\tRestResultDto resultInfoDto = null;\n" +
                "\t\tlog.info(\"调用天眼[质押走势]API，Keyword:{} ResultCode：{}\",keyword,rtn.getResultCode());\n" +
                "\t\t// 调用成功\n" +
                "\t\tif (ResultCode.SUCCESS.getCode().equals(rtn.getResultCode())){\n" +
                "\t\t\t// 天眼数据\n" +
                "\t\t\tresultInfoDto = (RestResultDto)rtn.getBody();\n" +
                "\t\t\t// 返回状态\n" +
                "\t\t\tString errorCode = resultInfoDto.getError_code();\n" +
                "\t\t\t// 返回信息\n" +
                "\t\t\tString reason = resultInfoDto.getReason();\n" +
                "\t\t\tif (Constants.ErrorCode.E_000000.equals(errorCode)) {\n" +
                "\t\t\t\t// 调用天眼接口\n" +
                "\t\t\t\t" + entryFirstName + "List = JSONArray.parseArray(JSONObject.toJSONString(resultInfoDto.getResult().get(\"items\")), " + entryName + "Dto.class);\n" +
                "\t\t\t\tthis.saveToDb(" + entryFirstName + "List,keyword);\n" +
                "\t\t\t}\n" +
                "\t\t\t// 调用记录\n" +
                "\t\t\tcallRecordService.saveApiCallRecord(Constants.ApiKey.STOCK_PLEDGE_TREND,keyword,errorCode,reason);\n" +
                "\t\t}\n" +
                "\n" +
                "\t\treturn " + entryFirstName + "List;";
        commonAnnotation += "\n" +
                "}";
        return commonAnnotation;
    }

    /**
     * 查询DB 数据方法
     *
     * @param strings   查询
     * @param entryName 实体类名称
     * @return
     */
    private static String createSelectFromDB(String[] strings, String entryName) {
        String entryFirstName = entryName.replace(entryName.substring(0, 1), entryName.substring(0, 1).toLowerCase());
        String commonAnnotation = "";
        String name = strings[1].split(" ")[0];
        String type = "public List<" + entryName + "Dto>";
        String note = strings[1].split(" ")[1] + strings[1].split(" ")[2];

        commonAnnotation += "   /**\n" +
                "     * " + note + "\n" +
                "     * @param keyword 关键字\n" +
                "     * @return " + entryName + "Dto\n" +
                "     */\n" +
                "     @Override\n" +
                "    " + type + " " + name + "(String keyword){\n";
        commonAnnotation +=
                "\t\tList<" + entryName + "Dto> dtoList = null;\n" +
                "\t\tQueryWrapper<T" + entryName + "> wrapper = new QueryWrapper<>();\n" +
                "\t\twrapper.eq(\"keyword\",keyword);\n" +
                "\t\tList<T" + entryName + "> entityList = " + entryFirstName + "Mapper.selectList(wrapper);\n" +
                "\t\tif (entityList != null && entityList.size() > 0) {\n" +
                "\t\t\tdtoList = new ArrayList<>();\n" +
                "\t\t\tfor (T" + entryName + " entity:entityList) {\n" +
                "\t\t\t\t// 复制\n" +
                "\t\t\t\t" + entryName + "Dto dto = new " + entryName + "Dto();\n" +
                "\t\t\t\tBeanUtils.copyProperties(entity, dto);\n" +
                "\t\t\t\tdtoList.add(dto);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t\treturn dtoList;";
        commonAnnotation += "\n" +
                "}";
        return commonAnnotation;
    }

    /**
     * saveToDb写入DB 方法
     *
     * @param entryName 实体类名称
     * @param note      方法注释
     */
    private static String createSaveToDb(String entryName, String note) {
        String entryFirstName = entryName.replace(entryName.substring(0, 1), entryName.substring(0, 1).toLowerCase());
        String commonAnnotation = "";
        commonAnnotation += "   /**\n" +
                "     * " + note + "\n" +
                "     * @param " + entryFirstName + "List note\n" +
                "     * @param keyword 关键字\n" +
                "     */\n" +
                "    " + "private void saveToDb" + " (List<" + entryName + "Dto> " + entryFirstName + "List,String keyword){\n";
        commonAnnotation += "\t\tif (" + entryFirstName + "List != null) {\n" +
                "\t\t\t// 先删除，在写入\n" +
                "\t\t\tHashMap<String,Object> delMap = new HashMap<>();\n" +
                "\t\t\tdelMap.put(\"keyword\",keyword);\n" +
                "\t\t\t" + entryFirstName + "Mapper.deleteByMap(delMap);\n" +
                "\t\t\t// 遍历写入\n" +
                "\t\t\tfor (" + entryName + "Dto dto:" + entryFirstName + "List) {\n" +
                "\t\t\t\tT" + entryName + " entity = new T" + entryName + "();\n" +
                "\t\t\t\tentity.setKeyword(keyword);\n" +
                "\t\t\t\tentity.setCreateTime(LocalDateTime.now());\n" +
                "\t\t\t\tentity.setUpdateTime(LocalDateTime.now());\n" +
                "\t\t\t\tentity.setCreateUser(\"sys\");\n" +
                "\t\t\t\tentity.setUpdateUser(\"sys\");\n" +
                "\t\t\t\tentity.setDeleted(\"0\");\n" +
                "\t\t\t\t" + entryFirstName + "Mapper.insert(entity);\n" +
                "\t\t\t}\n" +
                "\t\t}";
        commonAnnotation += "\n" +
                "}";
        return commonAnnotation;
    }

}
