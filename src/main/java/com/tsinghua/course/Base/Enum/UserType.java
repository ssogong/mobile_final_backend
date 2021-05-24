package com.tsinghua.course.Base.Enum;

/**
 * @描述 用户类型枚举
 **/
public enum UserType {
    NORMAL("User"),
    ADMIN("Admin")
    ;

    UserType(String name) {
        this.name = name;
    }

    String name;

    public String getName() {
        return name;
    }
}
