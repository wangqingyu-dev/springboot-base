package com.rt.base.business.common.em;

import com.rt.base.business.common.Code;

/**
 * @author wangq
 */
public enum ResultCode implements Code {
    /** 成功 */
    SUCCESS(200, "SUCCESS", "成功"),
    /** 异常 */
    ERROR(500, "ERROR", "糟糕！系统发生无法预知的错误，请稍后尝试或联系客服人员"),
    /** 未经授权 */
    UNAUTHORIZED(401, "UNAUTHORIZED", "未经授权"),
    /** 不存在 */
    NOT_FOUND(404, "NOT_FOUND", "不存在"),
    /** 参数错误 */
    PARAM_ERROR(400, "PARAM_ERROR", "参数错误"),
    /** 数据已存在 */
    DATA_EXIST(600, "DATA_EXISTS", "数据已存在");

    private Integer code;
    private String name;
    private String message;

    private ResultCode(Integer code, String name, String msg) {
        this.code = code;
        this.name = name;
        this.message = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Code setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }
}
