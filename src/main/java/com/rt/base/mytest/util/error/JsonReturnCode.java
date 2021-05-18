package com.rt.base.mytest.util.error;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 描述: json格式数据返回码
 *<ul>
 *      <li>100 : 用户未登录 </li>
 *      <li>200 : 成功 </li>
 *      <li>300 : 失败 </li>
 * </ul>
 * @author : Administrator
 */
public enum JsonReturnCode {

    /**
     * 所有的错误信息码
     */
    BIZ_SUCCESS("0","执行成功"),
    BIZ_FAIL("0001","执行失败"),

    /*
     * 是 或者 否 操作
     * 1开头
     */
    CHECk("1000", "#{msg}"),

    /**
     * 数据校验错误
     * 2开头
     */
    FIELD_UUID_ERROR("2001", "uuid生成错误"),
    FIELD_NOT_FOUND_ERROR("2002", "未找到要更新字段"),

    FIELD_NOT_CONFORM_ERROR("2100", "#{msg}不符合。"),
    FIELD_REPEAT_ERROR("2200", "#{msg}已经存在。"),
    FIELD_INVALID_ERROR("2400", "#{msg}无效。"),
    FIELD_PAST_ERROR("2500", "#{msg}过期。"),
    FIELD_EXIST_ERROR("2600", "#{msg}存在。"),
    FIELD_NULL_ERROR("2700", "#{msg}为空。"),



    /**
     * 类型转换错误
     * 3开头
     */
    FIELD_TRANSFER_ERROR("3001", "json转对象异常"),
    FIELD_TO_MAP_ERROR("3002", "对象转Map异常"),
    DATE_FORMTER_ERROR("3003", "日期格式化异常"),
    FIELD_TO_FIELD_ERROR("3004", "对象转对象异常"),
    STRING_TO_DOCUMENT_ERROR("3005", "字符串转换Document异常"),
    FIELD_TO_FIELD_ERROR_PARAM("3006", "对象转对象异常(#{msg})"),
    STRING_TO_DOCUMENT_ERROR_PARAM("3007", "字符串转换Document异常(#{msg})"),

    // 转换数字异常
    TO_NUM_ERROR_PARAM("3008", "转换数字异常(#{msg})"),

    /**
     * 系统错误
     * 4开头
     */
    QR_IMG_SIZE_TO_LARGE("4000", "二维码图片大小超过800"),
    REFLECT_NEW_OBJECT_ERROR("4001", "反射创建对象失败"),
    FIELD_ACCESS_ERROR("4002", "设置字段权限失败"),
    ENCODDING_ERROR("4003", "不支持字符集"),
    DECODDING_ERROR("4004", "字符集错误"),
    IO_ERROR("4005","IO错误"),
    CLASS_INIT_ERROR("4006", "实例化错误"),
    DECRYPT_ERROR("4007", "解密错误"),
    HTTP_RESPOSE_ERROR("4008", "解密错误"),
    SYSTEM_ERROR("4999","系统异常,请尽快联系技术人员"),
    /**
     * 权限类错误
     * 5开头
     */
    SESSION_EXPIRED("5000", "登录已过期，请重新登录"),
    USERNAME_NOT_FOUND("5001", "该用户未注册"),
    ENTERPRISE_NOT_FOUND("5002", "未找到用户企业"),
    BIZ_LOGIN_EROR("5003","登录异常"),
    BIZ_USER_PASSWORD_ERROR("5004","用户名或密码错误"),
    BIZ_USER_LOCK("5005","账号已锁定"),
    BIZ_USER_DISABLED("5006","账号已禁用"),
    BIZ_USER_EXPIRED("5007","账号已过期"),
    BIZ_USER_LOGIN_ERROR("5008","登录失败"),
    BIZ_USER_TS_FLAG_DISABLE("5010", "该企业已注销出口退免税资格"),
    BIZ_TOKEN_ERROR("5011","TOKEN获取错误"),
    BIZ_TOKEN_DISABLED("5012","TOKEN失效"),
    BIZ_TOKEN_NOT_FOUND("5013","未找到token"),
    BIZ_TOKEN_INVALID("5014","TOKEN无效"),
    USERNAME_FOUND("5015", "该用户已注册 #{msg}"),
    BIZ_TOKEN_NO_AUTH("5016","当前企业无权限访问接口"),
    /**
     * 业务类错误
     * 6开头
     */
    BIZ_REGIST_ERROR("6000","注册失败"),
    BIZ_STATUS_ERROR("6001","不支持的业务状态"),
    BIZ_ERROR_SUPPORT("6002", "不支持的业务类型"),
    BIZ_LC_CREATE_ERROR("6003", "流程创建失败"),
    BIZ_LC_UPDATE_ERROR("6004", "流程更新失败"),
    BIZ_LC_STATUS_ERRLR("6005", "当前录入的所属期(批次)已申报"),
    BIZ_GETDATE_ERROR("6006","获取数据失败"),
    BIZ_CHECKDATE_ERROR("6007","录入数据格式不正确"),
    BIZ_COMPANY_ERROR("6008","未找到zip中的公司信息"),
    BIZ_YM_PC_ERROR("6009","正审中未找到申报年月和申报批次"),
    BIZ_OPERATETIME_ERROR("6010","操作时间间隔小于一分钟,稍后操作"),
    BIZ_ERROR_SUPPORT_PARAM("6011", "不支持的业务类型(#{msg})"),
    /**
     * 文件类错误
     * 7开头
     */
    BIZ_PDF_TEMPLATE_FILE_NOT_FOUND("7003", "PDF模板文件未找到"),
    BIZ_PDF_JRE_EXCEPTION("7004", "PDF插件报错"),
    BIZ_PDF_CREATE_ERROR("7005", "PDF生成出错"),
    BIZ_HTML_CREATE_ERROR("7006", "HTML生成出错"),
    BIZ_XML_CREATE_ERROR("7007", "生成申报文件异常"),
    BIZ_XML_JIEXI_ERROR("7008", "解析xml文件报错"),
    BIZ_FILE_NOT_FOUND("7009", "压缩包中未找到文件#{msg}"),
    BIZ_YM_ERR("7010", "数据中未找到申报年月"),
    BIZ_PC_ERR("7011", "数据中未找到申报批次"),
    BIZ_COMPANY_ERR("7012", "文件包中公司id与登录id不同"),
    BIZ_JM_ERR("7013", "解密异常，请选择符合规范的申报文件"),
    BIZ_JSON_JIEXI_ERROR("7014", "JSON 解析错误"),
    BIZ_FILE_CONFIG_ERROR("7101", "excel文件配置错误"),
    BIZ_FILE_FIELD_SIZE_NOT_MATCH("7102", "excel文件配置字段错误"),
    BIZ_FILE_READ("7103", "excel文件读取错误"),
    BIZ_FILE_NULL("7104", "excel文件内容为空"),

    /**
     * 外部调用
     */
    BIZ_EXTERNAL_CELL_ERROR("8100", "外部调用失败！"),
    /**
     * 存储过程错误
     */
    BIZ_PROCESS_ERROR("8200", "存储过程错误！"),
    /**
     * 反射类
     */
    BIZ_NO_CLASS_PATH_ERROR("8300", "反射类路径未找到！"),
    BIZ_NO_METHOD_ERROR("8301", "未找到反射方法！"),
    BIZ_ILLEGAL_ACCESS_ERROR("8302", "反射方法没有访问权限！"),
    BIZ_INVOCATION_TARGET_ERROR("8303", "反射方法内部出错！"),
    BIZ_NO_FIELD("8304", "未找到反射字段#{msg}！"),
    BIZ_ACCESS("8305", "反射字段 #{msg} 权限异常！"),
    BIZ_NO_METHOD_ERROR_PARAM("8306", "未找到反射方法(#{msg})！"),
    BIZ_ILLEGAL_ACCESS_ERROR_PARAM("8307", "反射(#{msg})没有访问权限！"),
    BIZ_INVOCATION_TARGET_ERROR_PARAM("8308", "反射方法(#{msg})内部出错！"),
    BIZ_NULL_CLASS_PARAM("8309", "实体(#{msg})不能为空！"),
    BIZ_NO_INST_PARAM("8310", "对象(#{msg})不能实例化！"),
    /**
     * 生成Example
     */
    BIZ_CREAT_SQL_ERROR("8400", "#{msg}拼接为sql错误！"),

    /**
     * 其他错误
     */
    BIZ_PARAM_ERROR("9100", "前台传入参数错误！"),
    BIZ_NO_DATA_ERROR("9200", "数据库没有查询到#{msg}！"),

    // 运算
    BIZ_CALCU_NULL_ERROR("9301", "计算公式为空！"),
    BIZ_CALCU_OP_ERROR_PARAM("9302", "运算符错误（#{msg}）！"),
    BIZ_CALCU_VALUE_NULL_PARAM("9303", "获取数值为空（#{msg}）！"),
    BIZ_CALCU_VALUE_TYPE_PARAM("9303", "未知的数字类型（#{msg}）！"),

    BIZ_JQ_SKPWC("9401", "税控盘未插！"),
    BIZ_JQ_KHDBZX("9402", "客户端不在线！"),
    BIZ_JQ_ERROR("9403","加签异常"),
    BIZ_JQ_TIMEOUT("9404","加签超时"),

    BIZ_OTHER_ERROR("9900", "其他操作失败！");


    private String code;
    private String msg;
    private Map<String, Object> params;

    JsonReturnCode(String code, String desc) {
        this.code = code;
        this.msg = desc;
        params = new HashMap<>();
    }

    /**
     * 有参数的错误信息
     * @param value
     * @return
     */
    public JsonReturnCode addParam(String key, String value) {
        params.put(key,value);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        String retMsg = msg;
        Iterator<Map.Entry<String, Object>> iterator = this.params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            retMsg = retMsg.replace("#{"+next.getKey()+"}", String.valueOf(next.getValue()));
        }
        return retMsg;
    }

    public void setMsg(String msg) {
       this.msg = msg;
    }
}
