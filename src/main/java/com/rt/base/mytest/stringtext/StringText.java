package com.rt.base.mytest.stringtext;

import com.rt.base.mytest.util.DevFileUtil;

import java.io.Serializable;

/**
 * @author wqy
 */
public class StringText {
    private static String dtoExtImpPath = "D:\\tianyan\\src\\main\\java\\com\\eccoal\\tianyan\\business\\dto\\";
    public static void main(String[] args) {
        updateStringss();
    }


    public static String updateString() {
        String tianYanString =
                                "purchaser\tString\tvarchar(256)\t采购人\n" +
                                        "publishTime\tString\tdatetime\t发布时间\n" +
                                        "link\tString\tvarchar(128)\t详细信息链接\n" +
                                        "bidUrl\tString\tvarchar(128)\t天眼查链接\n" +
                                        "intro\tString\tvarchar(128)\t正文简介\n" +
                                        "content\tString\tlongtext\t正文信息\n" +
                                        "id\tString\tbigint(20)\tid\n" +
                                        "title\tString\tvarchar(256)\t标题\n" +
                                        "abs\tString\tvarchar(6000)\t摘要信息\n" +
                                        "proxy\tString\tvarchar(256)\t代理机构\n" +
                                        "uuid\tString\tvarchar(50)\tuuid\n" +
                                        "type\tString\tvarchar(50)\t公告类型\n" +
                                        "province\tString\tvarchar(50)\t省份地区";
        String[] split = tianYanString.split("\\n");
        String commonAnnotation = "";
        for (String s : split) {
            String s1 = s.split("\\t")[0];
            String s2 = "String";
            String s3 = s.split("\\t")[3];
            commonAnnotation += "   /**\n" +
                    "     * " + s3 + "\n" +
                    "     */\n";
            commonAnnotation +=  "   private "+s2+" "+s1+";\n";
        }
        return commonAnnotation;
    }

    public static String updateStringDto() {
        String tianYanString =
                "";
        String[] split = tianYanString.split("\\n");
        String commonAnnotation = "";
        String result = "";
        for (String s : split) {
                String s1 = s.split("\\t")[0];
                String s2 = s.split("\\t")[1];
                if ("Object".equals(s2)){
                    result += "   /**\n" +
                            "     * " + s1 + "\n" +
                            "     */\n";
                    String s3 = s1.replace(s1.substring(0, 1), s1.substring(0, 1).toUpperCase());
                    result +=  "   private "+s3+"Dto "+s1+";\n";
                }else if ("Array".equals(s2)){
                result += "   /**\n" +
                        "     * " + s1 + "\n" +
                        "     */\n";
                String s3 = s1.replace(s1.substring(0, 1), s1.substring(0, 1).toUpperCase());
                result +=  "   private List<"+s3+"Dto> "+s1+";\n";
            }else{
                    String s3 = s.split("\\t")[3];
                    commonAnnotation += "   /**\n" +
                            "     * " + s3 + "\n" +
                            "     */\n";
                    commonAnnotation +=  "   private "+s2+" "+s1+";\n";
                }

        }
        System.out.println(result);
        return result+commonAnnotation;
    }
    public static void updateStringss() {
        String dto = "detail";
        dto = dto.replace(dto.substring(0,1),dto.substring(0,1).toUpperCase());
        String tianYanString =
                        "consult_price\tNumber\t \t评估价格\n" +
                                "initial_price\tNumber\t \t起拍价格\n" +
                                "title\tString\tvarchar(255)\t标题\n" +
                                "jid\tNumber\t \t对应表id";
        String[] split = tianYanString.split("\\n");
        String commonAnnotation = "";
        for (String s : split) {
            String s1 = s.split("\\t")[0];
            String s2 = "String";
            String s3 = s.split("\\t")[3];
            commonAnnotation += "   /**\n" +
                    "     * " + s3 + "\n" +
                    "     */\n";
            commonAnnotation +=  "   private "+s2+" "+s1+";\n";
        }
        String commonAnnotation1 = "";
        commonAnnotation1 += "package com.eccoal.tianyan.business.dto;\n";
        commonAnnotation1 += "import lombok.Data;" + "\n";
        commonAnnotation1 += "import lombok.EqualsAndHashCode;" + "\n";
        commonAnnotation1 += "import lombok.experimental.Accessors;" + "\n";
        commonAnnotation1 += "import java.io.Serializable;" + "\n";
        commonAnnotation1 += "/**\n" +
                " *@author wqy\n" +
                " */\n" +
                " @Data\n" +
                " @EqualsAndHashCode(callSuper = false)\n" +
                " @Accessors(chain = true)\n" +
                " public class " +dto+"Dto" + " implements Serializable " + "{\n";
        commonAnnotation1 += commonAnnotation;
        commonAnnotation1 += "}";
        DevFileUtil.createFile(commonAnnotation1, dtoExtImpPath  + dto+"Dto.java");
    }


}
