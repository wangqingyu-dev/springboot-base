package com.rt.base.mytest.stringtext;

import com.rt.base.mytest.util.DevFileUtil;

/**
 * @author wqy
 */
public class StringText1 {
    private static String dtoExtImpPath = "D:\\tianyan\\src\\main\\java\\com\\eccoal\\tianyan\\business\\dto\\";
    public static void main(String[] args) {
        updateString();
    }


    public static String updateString() {
        String tianYanString =
                                "area\tString\tvarchar(255)\t土地面积（公顷）\n" +
                                        "merchandise_time\tString\tvarchar(255)\t成交日期\n" +
                                        "user_change_now_clean\tString\tvarchar(255)\t现土地使用权人\n" +
                                        "location\tString\tvarchar(255)\t土地坐落\n" +
                                        "aministrativeArea\tString\tvarchar(255)\t所在行政区\n" +
                                        "id\tNumber\tint(10)\t转让id\n" +
                                        "user_change_pre_clean\tString\tvarchar(255)\t原土地使用权人\n" +
                                        "merchandise_price\tString\tvarchar(255)\t转让价格（万元）";
        String[] split = tianYanString.split("\\n");
        String commonAnnotation = "";
        for (String s : split) {
            String s1 = s.split("\\t")[0];
            String s2 = s.split("\\t")[1];
            String s3 = s.split("\\t")[3];
            commonAnnotation =  s1+" varchar(500) null comment \'"+s3+"\',";
            System.out.println(commonAnnotation);
        }
        return commonAnnotation;
    }




}
