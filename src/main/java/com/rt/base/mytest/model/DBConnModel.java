package com.rt.base.mytest.model;

import lombok.Data;

/**
 * @author liW
 * @version 1.0
 * @date 2019-7-15 9:31
 */
@Data
public class DBConnModel extends BaseModel {

    //URL指向要访问的数据库名mydata
    String DBUrl = "jdbc:oracle:thin:@//192.168.1.107:1521/orcl";
    //驱动程序名
    String DBDriver = "oracle.jdbc.driver.OracleDriver";
    //MySQL配置时的用户名
    String DBUser = "dlsb";
    //MySQL配置时的密码
    String DBPassword = "dlsb";

    public DBConnModel(String DBUrl, String DBDriver, String DBUser, String DBPassword) {
        this.DBUrl = DBUrl;
        this.DBDriver = DBDriver;
        this.DBUser = DBUser;
        this.DBPassword = DBPassword;
    }
}
