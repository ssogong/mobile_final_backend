package com.tsinghua.course.Base.Constant;

/**
 * @描述 用于key的常量列表，一般对应于类中的属性名称，如User类中的username属性
 **/
public class KeyConstant {
    /** 以下为全局通用key */
    // 参数
    public static final String PARAMS = "params";
    // 路径
    public static final String PATH = "path";
    // 操作类型
    public static final String BIZ_TYPE = "bizType";
    // MongoDB id
    public static final String MONGO_ID = "_id";

    /** 以下为User关键key */
    // 用户名
    public static final String USERNAME = "username";
    // 密码
    public static final String PASSWORD = "password";
    // 实名
    public static final String REALNAME = "realName";
    // 生日
    public static final String DATE_OF_BIRTH = "dateOfBirth";
    // 头像url
    public static final String AVARTAR_URL = "avartar";
    // 联系人列表
    public static final String CONTACT_LIST = "contactList";
    // 房间列表
    public static final String ROOM_LIST = "roomList";

    /** 以下为Post关键key */
    // 点赞列表
    public static final String LIKES = "likes";
    // 评论列表
    public static final String COMMENTS = "comments";

    /** 以下为CharRoom关键key */
    // 房间id
    public static final String ROOM_ID = "roomId";
    // 成员列表
    public static final String USER_LIST = "userList";
    // 消息列表
    public static final String MESSAGES = "messages";
}
