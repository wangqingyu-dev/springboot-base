package com.rt.base.mytest.util;

import java.sql.Connection;
import java.sql.ResultSet;

public class DevBizUtil {


    /**
     * 表中是否含有某字段
     * @param tableName     表名
     * @param columnName    某字段
     * @return
     */
    public static String hasField(String tableName, String columnName) {
        ResultSet rs = null;
        Connection conn = DevComUtil.getConn();
        try {
            rs = SqlUtil.getOralceTableColumnInfo(conn, tableName);
            while(rs.next()){
                String str =  rs.getString("name");
                if (columnName.equals(str)) {
                    return "true";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public static String getClzName (String tableName) {
        String clzName = DevComUtil.UnderlineToHump(tableName);
        return DevComUtil.toUpperCaseFirstOne(clzName);
    }

    /**
     * 生成映射规则（规则 ：结果表，去除_YGZ_，用下划线分割，去除第一份再拼接）
     *  或者（规则 ：申报表，去除_YGZ_，_SB_，_SQ_，用下划线分割，去除第一份再拼接）
     * @param tableName
     * @return
     */
    public static String getRelationName(String tableName) {
        String[] split = tableName.replace("_YGZ_", "_")
                .replace("_SB_", "_")
                .replace("_DQY_", "_")
                .replace("_SQ_", "_")
                .replace("_DZGL_", "_")
//                .replace("_CS_", "_")
//                .replace("_PZ_", "_")
//                .replace("_DM_", "_")
//                .replace("_CP_", "_")
                .split("_");
        String name = "";
        for (int i = 0; i < split.length; i++) {
            name = name + (i == 0 && ("T".equals(split[i])
                    || "GC".equals(split[i]) || "F".equals(split[i])
                    || "GZXX".equals(split[i])) ? "" : split[i]);
        }
        return name.toLowerCase();
    }

    /**
     * 生成映射规则（规则 ：结果表，去除_YGZ_，用下划线分割，去除第一份再拼接）
     *  或者（规则 ：申报表，去除_YGZ_，_SB_，_SQ_，用下划线分割，去除第一份再拼接）
     * @param tableName
     * @return
     */
    public static String formatName(String tableName) {
        return tableName.replace("_YGZ_", "_")
                .replace("_SB_", "_")
                .replace("_DQY_", "_")
                .replace("_SQ_", "_");
    }

}
