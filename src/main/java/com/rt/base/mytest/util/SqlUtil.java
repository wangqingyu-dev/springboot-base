package com.rt.base.mytest.util;

import org.springframework.util.ObjectUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author liW
 * @version 1.0
 * @date 2019-7-23 10:33
 */
public class SqlUtil {

    /**
     * DB20
     * 获取Connection(oracle)
     *
     * @return DB20
     */
    public static Connection getConnDB20() {
        try {
            //URL指向要访问的数据库名mydata
            String url = "jdbc:oracle:thin:@//113.235.127.182:15219/DB20";
            //驱动程序名
//            String driver = "oracle.jdbc.driver.OracleDriver";
            String driver = "oracle.jdbc.OracleDriver";
            //MySQL配置时的用户名
            String user = "db20";
            //MySQL配置时的密码
            String password = "db20";

            //加载驱动程序
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("连接出错！！！");
            e.printStackTrace();
            return null;
        }
    }


    /**
     * icropuser2
     * 获取Connection(oracle)
     *
     * @return icropuser2
     */
    public static Connection getConnicropuser2() {
        try {
            //URL指向要访问的数据库名mydata
            String url = "jdbc:oracle:thin:@//113.235.127.182:15219/icropuser2";
            //驱动程序名
//            String driver = "oracle.jdbc.driver.OracleDriver";
            String driver = "oracle.jdbc.OracleDriver";
            //MySQL配置时的用户名
            String user = "icropuser2";
            //MySQL配置时的密码
            String password = "icropuser2";

            //加载驱动程序
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("连接出错！！！");
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 专用zzzzzzzzzzzzzz
     * 查询某表的数据
     *
     * @param con
     * @param tableName
     * @return
     * @throws Exception
     */
    public static ResultSet getOralceTableColumnInfo(Connection con, String tableName) throws Exception {

        if (!con.isClosed()) {
//            System.out.println("Succeeded connecting to the Database!");
        }
        //2.创建statement类对象，用来执行SQL语句！！
        Statement statement = con.createStatement();
        //要执行的SQL语句
        String sql = "SELECT\n" +
                "\tT .column_name AS name,\n" +
                "\t--列名\n" +
                "\tT .column_type AS type,\n" +
                "\t---字段类型\n" +
                "\tT .data_length AS length,\n" +
                "\t--字段长度\n" +
                "\tT .data_scale AS scale,\n" +
                "\t--字段精度\n" +
                "\tT .column_comment AS commentate,\n" +
                "\t--字段注释\n" +
                "\n" +
                "b.constraint_type AS is_pk,\n" +
                " --是否主键\n" +
                "DECODE (\n" +
                "\tT .nullable,\n" +
                "\t'N',\n" +
                "\tT .nullable,\n" +
                "\tNULL\n" +
                ") is_d_b_must   --是否为空\n" +
                "FROM\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tUB.tablespace_name AS database_name,\n" +
                "\t\t\tUTC.table_name AS table_name,\n" +
                "\t\t\tUTC.column_name AS column_name,\n" +
                "\t\t\tNVL(utc.data_precision,UTC.data_length) as data_length,\n" +
                "\t\t\tUTC.data_type AS column_type,\n" +
                "\t\t\tutc.data_scale AS data_scale,\n" +
                "\t\t\tucc.comments AS column_comment,\n" +
                "\t\t\tutc.column_id,\n" +
                "\t\t\tutc.nullable\n" +
                "\t\tFROM\n" +
                "\t\t\tuser_tables ub\n" +
                "\t\tLEFT JOIN user_tab_columns utc ON ub.table_name = UTC.table_name\n" +
                "\t\tLEFT JOIN user_col_comments ucc ON utc.column_name = ucc.column_name\n" +
                "\t\tAND utc.table_name = ucc.table_name\n" +
                "\t) T\n" +
                "LEFT JOIN (\n" +
                "\tSELECT\n" +
                "\t\tUCC.table_name AS table_name,\n" +
                "\t\tucc.column_name AS column_name,\n" +
                "\t\twm_concat (UC.constraint_type) AS constraint_type\n" +
                "\tFROM\n" +
                "\t\tuser_cons_columns ucc\n" +
                "\tLEFT JOIN user_constraints uc ON UCC.constraint_name = UC.constraint_name\n" +
                "\tGROUP BY\n" +
                "\t\tUCC.table_name,\n" +
                "\t\tucc.column_name\n" +
                ") b ON T .table_name = b.TABLE_NAME\n" +
                "AND T .column_name = b.column_name\n" +

                "where T.table_name='" + tableName + "' \n" +
                "order by T.column_id";
//        System.out.println(sql);
        //3.ResultSet类，用来存放获取的结果集！！
        ResultSet rs = statement.executeQuery(sql);
        return rs;
    }

    /**
     * 获取表中字段信息
     *
     * @param con 连接
     * @param sql 要执行的sql
     * @return
     * @throws Exception
     */
    public static ResultSet executeQuery(Connection con, String sql) throws Exception {
        //2.创建statement类对象，用来执行SQL语句！！
        Statement statement = con.createStatement();
        //3.ResultSet类，用来存放获取的结果集！！
        ResultSet rs = statement.executeQuery(sql);
        return rs;
    }

    /**
     * 所有的表
     *
     * @return
     */
    public static String getIndSql() {
        return getIndSql("");
    }

    /**
     * @param tableName 表名，若为空，则为所有
     * @return
     */
    public static String getIndSql(String tableName) {
        //要执行的SQL语句
        String sql = "select S.TABLE_NAME AS tableName, S.INDEX_NAME AS indexName, \n" +
                "S.COLUMN_NAME AS cloumnName, S.COLUMN_POSITION AS columnPosition, \n" +
                "S.DESCEND AS descend \n" +
                "from user_ind_columns S \n";
        if (!ObjectUtils.isEmpty(tableName)) {
            sql = sql + "WHERE s.table_name = '" + tableName + "'\n";
        }
        sql = sql + "ORDER BY S.TABLE_NAME, S.INDEX_NAME ,S.COLUMN_POSITION ";
        return sql;
    }

    /**
     * 所有的表
     *
     * @return
     */
    public static String getFieldSql() {
        return getFieldSql("");
    }


    /**
     * @param tableName 表名，若为空，则为所有
     * @return
     */
    public static String getFieldSql(String tableName) {
        //要执行的SQL语句
        String sql = "SELECT\n" +
                "T.table_name, --表名\n" +
                "T.comments as table_comments, --表名注释\n" +
                "\tT .column_name AS name,--列名\n" +
                "\tT .column_comment AS commentate,--字段注释\n" +
                "\tT .data_length AS length,--字段长度\n" +
                "\tT .data_scale AS scale,--字段精度\n" +
                "\tT .column_type AS type,---字段类型\n" +
                "DECODE (T .nullable,'N',T .nullable,NULL) is_d_b_must,--是否为空\n" +
                "b.constraint_type AS is_pk,--是否主键\n" +
                "\tT .data_defautl AS defaultValue--默认值\n" +
                "FROM\n" +
                "(\n" +
                "SELECT\n" +
                "UB.tablespace_name AS database_name,\n" +
                "UTC.table_name AS table_name,\n" +
                "UTC.column_name AS column_name,\n" +
                "NVL(utc.data_precision,UTC.data_length) AS data_length,\n" +
                "UTC.data_type AS column_type,\n" +
                "utc.data_scale AS data_scale,\n" +
                "ucc.comments AS column_comment,\n" +
                "utc.column_id,\n" +
                "utc.nullable,\n" +
                "utc.DATA_DEFAULT AS data_defautl,\n" +
                "co.comments\n" +
                "FROM\n" +
                "user_tables ub\n" +
                "LEFT JOIN user_tab_columns utc ON ub.table_name = UTC.table_name\n" +
                "LEFT JOIN user_col_comments ucc ON utc.column_name = ucc.column_name AND utc.table_name = ucc.table_name \n" +
                "LEFT JOIN user_tab_comments co ON co.table_name = ub.table_name\n" +
                ") T\n" +
                "LEFT JOIN (\n" +
                "SELECT\n" +
                "UCC.table_name AS table_name,\n" +
                "ucc.column_name AS column_name,\n" +
                "wm_concat (UC.constraint_type) AS constraint_type\n" +
                "FROM\n" +
                "user_cons_columns ucc\n" +
                "LEFT JOIN user_constraints uc ON UCC.constraint_name = UC.constraint_name\n" +
                "GROUP BY\n" +
                "UCC.table_name,\n" +
                "ucc.column_name\n" +
                ") b ON T .table_name = b.TABLE_NAME\n" +
                "\n" +
                "AND T .column_name = b.column_name\n";
        if (!ObjectUtils.isEmpty(tableName)) {
            sql = sql + "WHERE t.table_name = '" + tableName + "'\n";
        }
        sql = sql + "order by T.table_name, T.column_id";
        return sql;
    }

}
