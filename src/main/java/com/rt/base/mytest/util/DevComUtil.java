package com.rt.base.mytest.util;

import com.rt.base.mytest.model.DBConnModel;
import org.springframework.util.ObjectUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DevComUtil {

//    public static ObjectMapper getMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE));
//        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);//框架默认的是大小写不敏感的
//        // 忽略json字符串中不识别的属性
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        // 忽略无法转换的对象 “No serializer found for class com.xxx.xxx”
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
//        return objectMapper;
//    }

    public static String getDecPath() {
//        String path = System.getProperty("user.dir") + "\\apifkcore\\src\\main\\java\\com\\holyjet\\devTools";
        String path = getRootPath() + "/common/src/main/java/";
        return path;
    }

    public static String getRootPath () {
        return  System.getProperty("user.dir");
    }

    /**
     * 比较两个元素
     *
     * @param o1 元素1
     * @param o2 元素2
     * @return 是否相等
     */
    public static boolean compare2Obj(Object o1, Object o2) {
        boolean flag = false;
        if ((o1 == null && o2 == null) || (o1 != null && o1.equals(o2))) {
            flag = true;
        }
        return flag;
    }


    /**
     * 比较两个元素
     *
     * @param o1 元素1
     * @param o2 元素2
     * @return 是否相等
     */
    public static boolean compareArrNoOrder(Object[] o1, Object[] o2) {
        boolean flag = false;
        if ((o1 == null && o2 == null)) {
            flag = true;
        }
        if (o1 != null && o2 != null) {
            if (o1.length == o2.length) {
                for (int i = 0; i < o1.length; i++) {
                    if (!o1[i].equals(o2[i])) {
                        break;
                    }
                }
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 比较两个元素
     *
     * @param o1 元素1
     * @param o2 元素2
     * @return 是否相等
     */
    public static boolean compareArr(Object[] o1, Object[] o2) {
        boolean flag = false;
        if ((o1 == null && o2 == null)) {
            flag = true;
        }
        if (o1 != null && o2 != null) {
            if (o1.length == o2.length) {
                for (int i = 0; i < o1.length; i++) {
                    if (!o1[i].equals(o2[i])) {
                        break;
                    }
                }
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 通用TTTTTT
     * 判断数组中是否包含某元素
     *
     * @param arr     数组
     * @param element 元素
     * @return
     */
    public static boolean arrContain(Object[] arr, Object element) {
        boolean isContain = false;
        if (!ObjectUtils.isEmpty(arr)) {
            for (Object obj : arr) {
                System.out.println(obj);
                if ((obj != null && obj.equals(element)) || obj == element) {
                    isContain = true;
                    break;
                }
            }
        }
        return isContain;
    }


    /**
     * 通用TTTTTT
     * 读取的Excel数据放入List中--字符串型
     *
     * @param list
     * @param object
     */
    public static void addList(List list, Object object) {
        String str = "";
        if (object != null) {
            str = object.toString();
        }
        list.add(str);
    }

    /**
     * 通用TTTTTT
     * 首字符转换为大写。
     *
     * @param str
     * @return
     */
    public static String toUpperCaseFirstOne(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return "";
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /***
     * 通用TTTTTT
     * 下划线命名转为驼峰命名
     * @param para
     */

    public static String UnderlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /***
     * 通用TTTTTT
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String HumpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;//定位
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 通用TTTTTT
     * 去除字符串中所有的 换行，空格等
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    /**
     * 通用TTTTTT
     * 数组打印
     *
     * @param s
     */
    public static void printStr(String[] s) {
        if (s == null) {
            System.out.println("数组是空的！！！");
        } else {
            for (int i = 0, n = s.length; i < n; i++) {
                System.out.print(i + ":  " + s[i] + "   ");
                if ((i + 1) % 10 == 0) {
                    System.out.println();
                }
            }
        }
    }

    /**
     * 通用TTTTTT
     * 获取Connection(oracle)
     *
     * @return
     */
    public static Connection getConn(DBConnModel dbConnModel) {
        try {
            //URL指向要访问的数据库名mydata
            String url = dbConnModel.getDBUrl();
            //驱动程序名
            String driver = dbConnModel.getDBDriver();
            //MySQL配置时的用户名
            String user = dbConnModel.getDBUser();
            //MySQL配置时的密码
            String password = dbConnModel.getDBPassword();

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
     * 通用TTTTTT
     * 获取Connection(oracle)
     *
     * @return
     */
    public static Connection getConn() {
        try {
            //URL指向要访问的数据库名mydata
            String url = "jdbc:oracle:thin:@//113.235.127.182:15219/DB20";
//            String url = "jdbc:oracle:thin:@113.235.127.182:15219:DB20";
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
     * 通用TTTTTT
     * 获取Resultset
     *
     * @param con
     * @param sql
     * @return
     * @throws Exception
     */
    public static ResultSet getResultSet(Connection con, String sql) throws Exception {

        if (!con.isClosed()) {
            System.out.println("Succeeded connecting to the Database!");
        }
        //2.创建statement类对象，用来执行SQL语句！！
        Statement statement = con.createStatement();
        //要执行的SQL语句

        //3.ResultSet类，用来存放获取的结果集！！
        ResultSet rs = statement.executeQuery(sql);
        return rs;
    }

    /**
     * 通用TTTTTT
     * 关闭数据库
     *
     * @param con
     * @param rs
     * @throws Exception
     */
    public static void closeDB(Connection con, ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (con != null) {
            con.close();
        }
    }


}
