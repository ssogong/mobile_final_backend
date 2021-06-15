package com.tsinghua.course.Base.Error;

/**
 * @描述 用户操作警告枚举
 **/
public enum UserWarnEnum implements ExceptionInterface {
    LOGIN_FAILED("UserWarn001", "用户或密码不正确"),
    NEED_LOGIN("UserWarn002", "用户未登录或登录已过期"),

    PERMISSION_DENIED("UserWarn003", "无权限访问对应内容"),

    SIGNUP_NULL_FAILED("UserWarn004", "用户名或密码不能为空"),
    SIGNUP_EXIST_FAILED("UserWarn005", "用户已存在"),


    EDIT_FAILED("UserWarn006", "修改个人信息失败"),
    CHANGE_PASS_FAILED("UserWarn007", "修改密码失败"),

    SEARCH_NULL_FAILED("UserWarn008", "用户不存在")
    ;

    UserWarnEnum(String code, String msg) {
        errorCode = code;
        errorMsg = msg;
    }

    private String errorCode;
    private String errorMsg;
    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMsg;
    }
}
