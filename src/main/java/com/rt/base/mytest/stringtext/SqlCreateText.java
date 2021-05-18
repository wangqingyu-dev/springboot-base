package com.rt.base.mytest.stringtext;

import com.rt.base.mytest.util.DevFileUtil;

/**
 * @author wqy
 */
public class SqlCreateText {
    public static void main(String[] args) {
        updateSql();
    }


    public static String updateSql() {
        String tableName = "product_set";
        String tableNameStr = "产品套装表";
        String tianYanString = "product_set_id\tvarchar\t36\t否\t是\t\t产品套装ID\n" +
                "enterprise_id\tvarchar\t36\t否\t否\t\t公司ID\n" +
                "product_id\tvarchar\t36\t否\t否\t\t产品ID\n" +
                "sales_item_type\tvarchar\t4\t否\t否\t\t售卖项目分类(10课时包，20教材，30团体课，40师资班)\n" +
                "sales_item_id\tvarchar\t36\t否\t否\t\t售卖项目ID\n" +
                "deleted\tvarchar\t1\t否\t否\t1\t逻辑删除\n" +
                "update_user_id\tvarchar\t36\t否\t否\t\t更新者\n" +
                "update_time\ttimestamp\t\t是\t否\tCURRENT_TIMESTAMP\t更新时间\n" +
                "insert_user_id\tvarchar\t36\t否\t否\t\t创建者\n" +
                "insert_time\ttimestamp\t\t是\t否\tCURRENT_TIMESTAMP\t创建时间\n";
        String[] split = tianYanString.split("\\n");
        String commonAnnotation = "create table "+ tableName +"\n";
        commonAnnotation += "(\n";
        for (String s : split) {
            String s1 = s.split("\\t")[0];
            String s2 = s.split("\\t")[1];
            String s3 = s.split("\\t")[3];
            commonAnnotation += "   /**\n" +
                    "     * " + s3 + "\n" +
                    "     */\n";
            commonAnnotation +=  "   private "+s2+" "+s1+";\n";
        }
        commonAnnotation += ")\n";
        return commonAnnotation;
    }
}
