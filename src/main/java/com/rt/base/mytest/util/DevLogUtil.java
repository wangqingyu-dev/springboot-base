package com.rt.base.mytest.util;

import org.springframework.util.ObjectUtils;

import java.io.File;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Stack;

public class DevLogUtil {

    // 打印sql--计数用
    public static int logCount = 1;

    /**
     * 打印sql--读取文件成字符串
     *
     * @param file 文件
     * @param row  跳过的长度
     * @return
     */
    public static String getFileStr(File file, long row) {
        try {
            FileChannel inChannel = FileChannel.open(file.toPath(), StandardOpenOption.READ);
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, row, inChannel.size() - row);
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            return new String(dst);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("卧槽，读取文件错了。。。");
            throw new RuntimeException();
        }
    }

    /**
     * 打印sql--根据日志中的字符串，打印sql
     *
     * @param fileStr    日志信息
     * @param printParam 是否打印参数
     */
    public static String printLog(String fileStr, boolean printParam) {
        if (ObjectUtils.isEmpty(fileStr)) {
            return null;
        }
        String[] logRow = fileStr.split("\n");
        StringBuffer sb = new StringBuffer();
        String sql1 = "";
        for (String rowStr : logRow) {
            if (rowStr.contains("[==>")) {
                String[] logRows = rowStr.split("\\[==>");
                String sql = logRows[1];
                if (sql.contains("?")) {
                    sql1 = sql.toUpperCase();
                } else {
                    // 红色输出时间
                    sb.append("\033[31;4m" + (logCount++) + ":" + logRows[0] + "\033[0m");
                    if (sql.contains("Parameters")) {
                        //  Parameters: 61d5f036e71d497ea25512f4b96403f0(String),  (String), R(String)]
                        String[] split = sql.replace(" Parameters: ", "").split(", ");
                        int length = split.length;
                        String[] params = new String[length];
                        for (int i = 0; i < length; i++) {
                            params[i] = "'" + split[i].split("\\(")[0] + "'";
                        }
                        String printSql = sql1.replace("?", "%s");

                        // 打印预编译的SQL和参数
                        if (printParam) {
                            print(sb, printSql, params);
                        }

                        String format;
                        try {
                            format = String.format(printSql, params);
                            String sqlStr = format.replace("Preparing: ", "").replace(" ]", "");
                            sb.append(formatSql(sqlStr));
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("卧槽，此条sql打印有问题了，重新试一下啊。。。");
                        }

                    } else {
                        sb.append(sql);
                    }
                    sb.append("\n\n");
                }
            }
        }
        return sb.toString();
    }


//    [2019-09-16 10:31:57][DEBUG][http-nio-9191-exec-1][org.mybatis.spring.SqlSessionUtils:?][SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@756804b7] was not registered for synchronization because synchronization is not active]
//            [2019-09-16 10:31:57][DEBUG][http-nio-9191-exec-1][org.springframework.jdbc.datasource.DataSourceUtils:?][Fetching JDBC Connection from DataSource]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][org.mybatis.spring.transaction.SpringManagedTransaction:?][JDBC Connection [ProxyConnection[PooledConnection[oracle.jdbc.driver.T4CConnection@3faa373f]]] will not be managed by Spring]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][com.ding.mapper.index.TCpinfoDingMapper.getCpInfoByCorpId:?][==>  Preparing: select c.CPCODE AS cpCode , c.NAME AS cpName , c.NSRDJ_NO AS nsrNo , c.SHXY_NO AS shxyNo , c.qylx AS qylx , c.SB_YM AS sbYm , c.SB_PC AS sbPc , d.CORP_ID AS corpId from T_CPINFO c left join T_CPINFO_DING d on c.CPCODE=d.CPCODE where d.corp_id = ? ]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][com.ding.mapper.index.TCpinfoDingMapper.getCpInfoByCorpId:?][==> Parameters: ding96d4d053fa538be535c2f4657eb6378f(String)]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][com.ding.mapper.index.TCpinfoDingMapper.getCpInfoByCorpId:?][<==      Total: 1]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][org.mybatis.spring.SqlSessionUtils:?][Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@756804b7]]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][org.springframework.jdbc.datasource.DataSourceUtils:?][Returning JDBC Connection to DataSource]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][org.mybatis.spring.SqlSessionUtils:?][Creating a new SqlSession]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][org.mybatis.spring.SqlSessionUtils:?][SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2833716d] was not registered for synchronization because synchronization is not active]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][org.springframework.jdbc.datasource.DataSourceUtils:?][Fetching JDBC Connection from DataSource]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][org.mybatis.spring.transaction.SpringManagedTransaction:?][JDBC Connection [ProxyConnection[PooledConnection[oracle.jdbc.driver.T4CConnection@3faa373f]]] will not be managed by Spring]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][com.ding.mapper.CommonMapper.getDjtj:?][==>  Preparing: SELECT 'ckbgd' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_CKBGD WHERE CPCODE = ? UNION ALL (SELECT 'jkbgd' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_JKBGD WHERE CPCODE = ?) UNION ALL (SELECT 'dlckzm' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_DLCKZM WHERE CPCODE = ?) UNION ALL (SELECT 'zzszyfp' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_ZYFP WHERE CPCODE = ?) UNION ALL (SELECT 'jkzzs' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_ZZS WHERE CPCODE = ?) UNION ALL (SELECT 'jkxfs' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_XFS WHERE CPCODE = ?) UNION ALL (SELECT 'jkgs' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_GS WHERE CPCODE = ?) UNION ALL (SELECT 'jkfjs' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_TBGS WHERE CPCODE = ?) UNION ALL (SELECT 'cksh' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_SH WHERE CPCODE = ?) UNION ALL (SELECT 'yfk' AS "type", COUNT(*) AS "count" FROM T_DQY_XX_YFK WHERE CPCODE = ?) UNION ALL (SELECT 'ghs' AS "type", COUNT(*) AS "count" FROM T_DQY_KPH_XFQY WHERE CPCODE = ?) ]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][com.ding.mapper.CommonMapper.getDjtj:?][==> Parameters: 1234567890(String), 1234567890(String), 1234567890(String), 1234567890(String), 1234567890(String), 1234567890(String), 1234567890(String), 1234567890(String), 1234567890(String), 1234567890(String), 1234567890(String)]
//            [2019-09-16 10:31:58][DEBUG][http-nio-9191-exec-1][com.ding.mapper.CommonMapper.getDjtj:?][<==      Total: 11]





    /**
     * 打印sql--根据日志中的字符串，打印sql
     *
     * @param fileStr    日志信息
     * @param printParam 是否打印参数
     */
    public static String printLog2(String fileStr, boolean printParam) {
        if (ObjectUtils.isEmpty(fileStr)) {
            return null;
        }
        String[] logRow = fileStr.split("\n");
        StringBuffer sb = new StringBuffer();
        Stack<String> sql = new Stack<>();
        Stack<String> param = new Stack<>();

        for (String rowStr : logRow) {
            if (rowStr.contains("[==>")) {
                String[] logRows = rowStr.split("\\[==>");
                if (logRows[1].contains("Preparing")) {
                    sql.push(logRows[1].toUpperCase());
                } else if (logRows[1].contains("Parameters")) {
                    param.push(logRows[1]);
                }
            }
        }

        while (!sql.empty()) {
            String sqlText = sql.pop();
            if (sqlText.contains("?")) {
                String paramText = param.pop();
                //  Parameters: 61d5f036e71d497ea25512f4b96403f0(String),  (String), R(String)]
                String[] split = paramText.replace(" Parameters: ", "").split(", ");
                int length = split.length;
                String[] params = new String[length];
                for (int i = 0; i < length; i++) {
                    params[i] = "'" + split[i].split("\\(")[0] + "'";
                }
                String printSql = sqlText.replace("?", "%s");

                // 打印预编译的SQL和参数
                if (printParam) {
                    print(sb, printSql, params);
                }
                String format;
                try {
                    format = String.format(printSql, params);
                    String sqlStr = format.replace("Preparing: ", "").replace(" ]", "");
                    sb.append(formatSql(sqlStr));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("卧槽，此条sql打印有问题了，重新试一下啊。。。");
                }
            } else {
                sb.append(formatSql(sqlText));
            }
        }
        if(!param.empty()) {
            System.out.println("卧槽，参数没有匹配好。。。。");
        }
        return sb.toString();
    }


    /**
     * 打印sql--整理SQL--换行
     *
     * @param sqlStr SQL字符串
     * @return 换行后的SQL字符串
     */
    public static String formatSql(String sqlStr) {
        return sqlStr.replace("SELECT", "\nSELECT")
                .replace(" FROM", "\nFROM")
                .replace(" WHERE", "\nWHERE")
                .replace(" UPDATE", "\nUPDATE")
                .replace(" DELETE", "\nDELETE")
                .replace(" INSERT", "\nINSERT")
                .replace(" GROUP", "\nGROUP")
                .replace(" ORDWE", "\nORDWE");
    }

    /**
     * 打印sql--打印预编译的SQL和参数
     *
     * @param printSql 预编译的SQL字符串
     * @param params   参数数组
     */
    public static void print(StringBuffer sb, String printSql, String[] params) {
        for (int i = 0; i < params.length; i++) {
            sb.append("###########params[" + i + "]:  " + params[i]);
            sb.append("\n");
        }
        sb.append("**********printSql:  " + printSql);
        sb.append("\n");
    }
}
