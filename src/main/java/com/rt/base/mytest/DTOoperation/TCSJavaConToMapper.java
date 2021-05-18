package com.rt.base.mytest.DTOoperation;

import com.rt.base.mytest.util.DataUtil;
import com.rt.base.mytest.util.DevFileUtil;

/**
 * @author :static
 * 一键从controller到数据库增删改查
 */
public class TCSJavaConToMapper {
    private static String controlPath = "D:\\tli-tcs\\tcs-api-server\\src\\main\\java\\com\\tli\\tcs\\controller\\";
    private static String servicePath = "D:\\tli-tcs\\tcs-api-server\\src\\main\\java\\com\\tli\\tcs\\service\\tcs\\";
    private static String serviceImplPath = "D:\\tli-tcs\\tcs-api-server\\src\\main\\java\\com\\tli\\tcs\\service\\impl\\";
    private static String dtoExtImpPath = "D:\\tli-tcs\\tcs-api-server\\src\\main\\java\\com\\tli\\tcs\\dto\\";

    public static void main(String[] args) {
        //TODO 如果不需要创建新文件就不用改动packagePath
        String packagePath = "D:\\tli-tcs\\tcs-api-server\\src\\main\\java\\com\\tli\\tcs\\controller";
        packagePath = packagePath.split("java")[1].replace("\\",".").substring(1) + ";";
        //方法名及注释
        String[] strings = {
                "selectList 根据条件查询",
                "insert 插入",
                "delete 删除",
                "update 修改",
        };
        String[] strings2 = {
                "selectCertificateByMemberId 查询教师证照/资历"
        };
        //实体类的类名
        String entryName = "TeacherCertificate";

//      createFlag     是否生成文件 1 生成  0在控制台显示
        String createFlag = "0";
//      createMapperFlag     是否生成createMapperFlag文件 1 生成  0在控制台显示
        String createMapperFlag = "0";
        writeControl(controlPath , packagePath,entryName,strings2,createFlag,createMapperFlag);
    }

    /**
     * Dto Ext写到指定目录
     * @param dtoExtImpPath dto ext 地址
     * @param packagePath  包的地址
     * @param entryName  实体类name
     */
    private static void writeDtoExt(String dtoExtImpPath, String packagePath, String entryName) {
        //代码
        packagePath = packagePath.replace("web.","dto.ext.");
        String commonAnnotation = "";
        commonAnnotation += "package " + packagePath + "\n";
        commonAnnotation += "import lombok.Data;" + "\n";
        commonAnnotation += "/**\n" +
                " *@author static\n" +
                " */\n" +
                " @Data\n" +
                " public class " + entryName + "Ext" +" extends " + entryName + "{\n" ;
        commonAnnotation +=  "   private int draw;\n";
        commonAnnotation +=  "   private Object columns;\n";
        commonAnnotation +=  "   private Object order;\n";
        commonAnnotation +=  "   private Object search;\n";
        commonAnnotation +=  "   private int start;\n";
        commonAnnotation +=  "   private int length;\n";
        commonAnnotation +=  "}";
        DevFileUtil.createFile(commonAnnotation, dtoExtImpPath + entryName + "Ext.java");

    }


    /**
     * Controller写到指定目录
     * @param controlPath   Controller写在哪的地址
     * @param packagePath  包的地址
     * @param entryName  实体类name
     * @param strings     方法名数组遍历生成
     * @param createFlag     是否生成文件 1 生成  0在控制台显示
     * @param createMapperFlag     是否生成createMapperFlag文件 1 生成  0在控制台显示
     */
    private static void writeControl(String controlPath , String  packagePath, String entryName , String[] strings, String createFlag, String createMapperFlag) {
        String entryFirstName = entryName.replace(entryName.substring(0,1),entryName.substring(0,1).toLowerCase());
        if ("0".equals(createFlag)) {
            //代码
            String commonAnnotation = "";
            for (int i = 0; i < strings.length; i++) {
                if (!DataUtil.isEmpty(strings)) {
                    String name = strings[i].split(" ")[0];
                    String type = "ResultInfo";
                    String note = strings[i].split(" ")[1];

                    commonAnnotation +=
                            "    @ApiOperation(value = \""+note+"\", httpMethod = \"POST\")\n" +
                            "    @RequestMapping(\"/" + name + "\")\n" +
                            "    @ResponseBody\n" +
                            "    public " + type + " " + name + "(HttpServletRequest request, @RequestBody " + entryName + "Ext target) {\n" +
                            "       return " + entryFirstName + "Service." + name + "(request, target);\n" +
                            "    }\n";
                }
                System.out.println(commonAnnotation);
            }
        } else {
            //代码
            String commonAnnotation = "";
            commonAnnotation += "package " + packagePath + "\n";
            commonAnnotation += "import lombok.extern.slf4j.Slf4j;\n";
            commonAnnotation += "import net.dlrt.tli.adminserver.common.util.result.ResultInfo;\n";
            commonAnnotation += "import net.dlrt.tli.adminserver.service.teacher.TeacherSalaryService;\n";
            commonAnnotation += "import org.springframework.beans.factory.annotation.Autowired;\n";
            commonAnnotation += "import org.springframework.stereotype.Controller;\n";
            commonAnnotation += "import org.springframework.web.bind.annotation.RequestBody;\n";
            commonAnnotation += "import org.springframework.web.bind.annotation.RequestMapping;\n";
            commonAnnotation += "import org.springframework.web.bind.annotation.ResponseBody;\n";
            commonAnnotation += "import javax.servlet.http.HttpServletRequest;\n";
            commonAnnotation += "/**\n" +
                    " *@author static\n" +
                    " */\n" +
                    " @Slf4j\n" +
                    " @Controller\n" +
                    " @RequestMapping(\""+entryFirstName+"\")\n" +
                    " public class "+entryName+"Controller {\n" +
                    "     @Autowired\n" +
                    "     private "+entryName+"Service "+entryFirstName+"Service;\n" ;

            for (int i = 0; i < strings.length; i++) {
                if (!DataUtil.isEmpty(strings)) {
                    String name = strings[i].split(" ")[0];
                    String type = "ResultInfo";
                    String note = strings[i].split(" ")[1];

                    commonAnnotation += "   /**\n" +
                            "     * " + note + "\n" +
                            "     * @param request\n" +
                            "     * @param target 对象\n" +
                            "     */\n" +
                            "    @RequestMapping(\"" + name + "\")\n" +
                            "    @ResponseBody\n" +
                            "    public " + type + " " + name + "(HttpServletRequest request, @RequestBody " + entryName + "Ext target) {\n" +
                            "       return " + entryFirstName + "Service." + name + "(request, target);\n" +
                            "    }\n";
                }
//                System.out.println(commonAnnotation);
            }
            commonAnnotation += "\n"+
                    "}";
            DevFileUtil.createFile(commonAnnotation,controlPath+entryName+"Controller.java");
        }
        packagePath = packagePath.replace("web","service");
        writeService(servicePath,packagePath,entryName,strings,createFlag,createMapperFlag);
        packagePath = packagePath.replace("web.tcs","dto.ext");
        writeDtoExt(dtoExtImpPath,packagePath,entryName);
    }


    /**
     * Service写到指定目录
     * @param servicePath
     * @param packagePath  包的地址
     * @param entryName  实体类name
     * @param strings     方法名数组遍历生成
     * @param createFlag     是否生成文件 1 生成  0在控制台显示
     * @param createMapperFlag     是否生成createMapperFlag文件 1 生成  0在控制台显示
     */
    private static void writeService(String servicePath,String packagePath ,String entryName ,String[] strings,  String createFlag,String createMapperFlag) {
        if ("0".equals(createFlag)) {
            //代码
            String commonAnnotation = "";
            for (int i = 0; i < strings.length; i++) {
                if (!DataUtil.isEmpty(strings)) {
                    String name = strings[i].split(" ")[0];
                    String type = "ResultInfo";
                    String note = strings[i].split(" ")[1];

                    commonAnnotation += "   /**\n" +
                            "     * " + note + "\n" +
                            "     * @param request request\n" +
                            "     * @param target 对象\n" +
                            "     */\n" +
                            "    " + type + " " + name + "(HttpServletRequest request, " + entryName + "Ext target) ;\n";
                }
                System.out.println(commonAnnotation);
            }
        } else {
            //代码
            String commonAnnotation = "";
            commonAnnotation += "package " + packagePath + "\n";
            commonAnnotation += "import net.dlrt.tli.adminserver.common.util.result.ResultInfo;\n";
            commonAnnotation += "import javax.servlet.http.HttpServletRequest;\n";
            commonAnnotation += "/**\n" +
                    " *@author static\n" +
                    " */\n" +
                    " public interface "+entryName+"Service {\n" ;

            for (int i = 0; i < strings.length; i++) {
                if (!DataUtil.isEmpty(strings)) {
                    String name = strings[i].split(" ")[0];
                    String type = "ResultInfo";
                    String note = strings[i].split(" ")[1];

                    commonAnnotation += "   /**\n" +
                            "     * " + note + "\n" +
                            "     * @param request request\n" +
                            "     * @param target 对象\n" +
                            "     * @return ResultInfo\n" +
                            "     */\n" +
                            "    " + type + " " + name + "(HttpServletRequest request," + entryName + "Ext target);\n";
                }
            }
            commonAnnotation += "\n"+
                    "}";
            DevFileUtil.createFile(commonAnnotation,servicePath+entryName+"Service.java");
        }
        packagePath = packagePath.replace("web","service").replace(";",".impl;");
        writeServiceImpl(serviceImplPath,packagePath,entryName,strings,createFlag,createMapperFlag);
    }

    /**
     * ServiceImpl写到指定目录
     * @param serviceImplPath   ServiceImpl写在哪的地址
     * @param packagePath  包的地址
     * @param entryName  实体类name
     * @param strings     方法名数组遍历生成
     * @param createFlag     是否生成文件 1 生成  0在控制台显示
     * @param createMapperFlag     是否生成createMapperFlag文件 1 生成  0在控制台显示
     */
    private static void writeServiceImpl(String serviceImplPath ,String  packagePath,String entryName ,String[] strings, String createFlag,String createMapperFlag) {
        //0在控制台显示
        if ("0".equals(createFlag)) {
            //代码
            String commonAnnotation = "";
            for (int i = 0; i < strings.length; i++) {
                if (!DataUtil.isEmpty(strings)) {
                    String name = strings[i].split(" ")[0];
                    String type = "ResultInfo";
                    String note = strings[i].split(" ")[1];

                    commonAnnotation += "   /**\n" +
                            "     * " + note + "\n" +
                            "     * @param request request\n" +
                            "     * @param target 对象\n" +
                            "     */\n" +
                            "    @Override\n" +
                            "    public " + type + " " + name + "(HttpServletRequest request," + entryName + "Ext target) {\n" +
                            "       return ResultInfo.success();\n" +
                            "    }\n";
                }
                System.out.println(commonAnnotation);
            }
        } else {
            //代码
            String entryFirstName = entryName.replace(entryName.substring(0,1),entryName.substring(0,1).toLowerCase());
            String commonAnnotation = "";
            commonAnnotation += "package " + packagePath + "\n";
            commonAnnotation += "import net.dlrt.tli.adminserver.common.util.result.ResultInfo;\n";
            commonAnnotation += "import org.springframework.beans.factory.annotation.Autowired;\n";
            commonAnnotation += "import org.springframework.stereotype.Service;\n";
            commonAnnotation += "import org.springframework.transaction.annotation.Transactional;\n";
            commonAnnotation += "import javax.servlet.http.HttpServletRequest;\n";
            commonAnnotation += "import org.slf4j.LoggerFactory;\n";
            commonAnnotation += "/**\n" +
                    " *@author static\n" +
                    " */\n" +
                    " @Service\n" +
                    " public class "+entryName+"ServiceImpl "+ " implements "+entryName+"Service" +" {\n" +
                    "     private static final org.slf4j.Logger logger = LoggerFactory.getLogger("+entryName+"ServiceImpl.class);\n\n" +
                    "     @Autowired\n" +
                    "     private "+entryName+"Mapper "+entryFirstName+"Mapper;\n"+
                    "     @Autowired\n" +
                    "     private "+entryName+"MapperExt "+entryFirstName+"MapperExt;\n";

            for (int i = 0; i < strings.length; i++) {
                if (!DataUtil.isEmpty(strings)) {
                    String name = strings[i].split(" ")[0];
                    String type = "ResultInfo";
                    String note = strings[i].split(" ")[1];
                    commonAnnotation += "   /**\n" +
                            "     * " + note + "\n" +
                            "     * @param request request\n" +
                            "     * @param target 对象\n" +
                            "     */\n" +
                            "    @Override\n" +
                            "    public " + type + " " + name + "(HttpServletRequest request," + entryName + "Ext target) {\n";
                    if ("selectList".equals(name)) {
                        commonAnnotation +=
                                "       List<"+entryName+"Ext> list = " + entryFirstName + "MapperExt." + name + "(target);\n" +
                                "       int count = " + entryFirstName + "MapperExt.selectCount(target);\n" +
                                "       ResultInfo resultInfo = new ResultInfo();;\n" +
                                "       resultInfo.put(\"data\", list);\n" +
                                "       resultInfo.put(\"draw\", target.getDraw());\n" +
                                "       resultInfo.put(\"recordsTotal\", count);\n" +
                                "       resultInfo.put(\"recordsFiltered\", count);\n" +
                                "       return resultInfo;\n" +
                                "    }\n";
                    } else {
                        commonAnnotation +=
                                "       " + entryFirstName + "MapperExt." + name + "(target);\n" +
                                        "       return ResultInfo.success();\n" +
                                        "    }\n";
                    }
                }
            }
            commonAnnotation += "\n"+
                    "}";
            DevFileUtil.createFile(commonAnnotation,serviceImplPath+entryName+"ServiceImpl.java");
        }
        packagePath = packagePath.replace("service","dao").replace(".impl;",".ext;");
//        writeMapperExtImp(mapperExtImpPath,packagePath,entryName,strings,createMapperFlag);
    }

    /**
     * mapperExtImpPath 写到指定目录
     * @param mapperExtImpPath   ServiceImpl写在哪的地址
     * @param packagePath  包的地址
     * @param entryName  实体类name
     * @param strings     方法名数组遍历生成
     * @param createMapperFlag     是否生成createMapperFlag文件 1 生成  0在控制台显示
     */
    private static void writeMapperExtImp(String mapperExtImpPath,String packagePath ,String entryName ,String[] strings,String createMapperFlag) {
        if ("0".equals(createMapperFlag)) {
            //代码
            String commonAnnotation = "";
            for (int i = 0; i < strings.length; i++) {
                if (!DataUtil.isEmpty(strings)) {
                    String name = strings[i].split(" ")[0];
                    String type = "int";
                    String note = strings[i].split(" ")[1];

                    commonAnnotation += "   /**\n" +
                            "     * " + note + "\n" +
                            "     * @param target 对象\n" +
                            "     * @return 结果\n" +
                            "     */\n" +
                            "    " + type + " " + name + "(" + entryName + "Ext target);\n";
                }
                System.out.println(commonAnnotation);
            }
        } else {
            //代码
            String commonAnnotation = "";
            commonAnnotation += "package " + packagePath + "\n";
            commonAnnotation += "import org.springframework.stereotype.Repository;" + "\n";
            commonAnnotation += "/**\n" +
                    " *@author static\n" +
                    " */\n" +
                    " @Repository\n" +
                    " public interface "+entryName+"MapperExt {\n" ;

            for (int i = 0; i < strings.length; i++) {
                if (!DataUtil.isEmpty(strings)) {
                    String name = strings[i].split(" ")[0];
                    String type = "List<" + entryName + "Ext>";
                    String note = strings[i].split(" ")[1];

                    commonAnnotation += "   /**\n" +
                            "     * " + note + "\n" +
                            "     * @param target 对象\n" +
                            "     * @return 结果\n" +
                            "     */\n" +
                            "    " + type + " " + name + "(" + entryName + "Ext target);\n";
                }
            }
            commonAnnotation += "\n"+
                    "}";
            DevFileUtil.createFile(commonAnnotation,mapperExtImpPath+entryName+"MapperExt.java");
        }
    }
}
