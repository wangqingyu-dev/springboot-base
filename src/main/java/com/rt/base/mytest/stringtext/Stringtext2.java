package com.rt.base.mytest.stringtext;

import com.rt.base.mytest.util.DevFileUtil;

/**
 * @author wqy
 */
public class Stringtext2 {
    public static void main(String[] args) {
        updateSql(sql());
    }


    public static String updateSql(String sql) {
        String tianYanString = "";
        String[] split = sql.split("\n");
        int count = 0;
        for (String s : split) {
            s = s.replace("\t","");
            tianYanString += s + " ";
            count ++;
            if (count == 5){
                tianYanString += "\t\n";
                count = 0;
            }
        }
        String[] split1 = tianYanString.split("\n");
        String wode = "";
        for (String s : split1) {
            String[] split2 = s.split(" ");
            wode += "    /**\n";

            if (split2.length > 3){
                wode += "     * " + split2[4] +"\n";
            }else{
                wode += "     * " + split2[3] +"\n";
            }
            wode += "     * 1\n" +
                    "     */\n";
            wode += "    private " + split2[1] + " " + split2[0] + ";\n";
        }
        System.out.println(wode);
        return wode;
    }
    public static String sql() {
        String tableName = "deptNo\n" +
                "String\n" +
                "是\t\n" +
                "EBU4418046536619\n" +
                "事业部编号；最大长度50\n" +
                "newWBType\n" +
                "Number\n" +
                "是\t\n" +
                "2\n" +
                "单号类型；查询的单号类型，填0时no字段请填写商家订单号orderNo、填1时no字段请填写ECLP单号wbNo、填2时no字段请填写青龙运单号lwbNo\n" +
                "no\n" +
                "String\n" +
                "是\t\n" +
                "JDVC00000186621\n" +
                "单号；根据newWBType填写所需的单号；最大长度50";

        return tableName;
    }
}
