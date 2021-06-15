package com.tsinghua.course.Biz;

import com.tsinghua.course.Biz.Controller.*;

/**
 * @描述 业务类型枚举，所有的业务类型都需要枚举在此类中
 **/
public enum BizTypeEnum {
    /** 用户业务类型 */
    USER_LOGIN(UserController.class, "/user/login", "用户登录"),
    USER_LOGOUT(UserController.class, "/user/logout", "用户注销"),
    USER_SIGN_UP(UserController.class, "/user/signup", "用户注册"),
    USER_CHANGE_PASS(UserController.class, "/user/pass", "修改密码"),
    USER_EDIT(UserController.class, "/user/edit", "修改个人信息"),
    USER_IMAGE(UserController.class, "/user/image", "上传头像"),
    USER_SEARCH(UserController.class, "/user/search", "搜索用户"),
    CONTACT_GET(UserController.class, "/contact/get", "获取联系人列表"),
    CONTACT_ADD(UserController.class, "/contact/add", "添加联系人"),
    CONTACT_DELETE(UserController.class, "/contact/delete", "删除联系人"),

    /** 动态业务类型 */
    POST_GET_ALL(PostController.class, "/post/getAll", "获取所有动态"),
    POST_ADD(PostController.class, "/post/add", "添加动态"),
    POST_GET_REACTION(PostController.class, "/post/getReaction", "获取动态点赞和评论"),
    POST_ADD_REACTION(PostController.class, "/post/addReaction", "对动态点赞或评论"),

    /** 聊天业务类型 */
    CHAT_GET_ROOMS(ChatController.class, "/chat/myRooms", "我的所有聊天室"),
    CHAT_MAKE_ROOM(ChatController.class, "/chat/makeRoom", "创建聊天"),
    CHAT_SEND_MESSAGE(ChatController.class, "/chat/send", "发送消息"),
    CHAT_DELETE_MESSAGE(ChatController.class, "/chat/delete", "删除一条信息"),
    CHAT_GET_MESSAGES(ChatController.class, "/chat/messages", "获取所有聊天记录"),
    CHAT_GET_MEMBERS_INFO(ChatController.class, "/chat/members", "获取成员信息"),
    CHAT_INVITE(ChatController.class, "/chat/invite", "邀请用户加入"),
    CHAT_EXIT(ChatController.class, "/chat/exit", "退出房间"),

    /** 定时任务业务测试 */
    LOG_TEST(TimerController.class, null, "定时日志测试"),

    /** 测试业务，在书写正式代码时可以删除，在书写正式代码前先运行测试业务，如果测试业务无问题说明各模块正常 */
    LOGIN_TEST(TestController.class, "/test/loginPermission", "登录控制测试"),
    ADMIN_TEST(TestController.class, "/test/adminPermission", "管理员权限控制测试"),
    REDIS_TEST(TestController.class, "/test/redis", "redis缓存测试"),
    TIMER_TEST(TestController.class, "/test/timer", "定时器测试"),
    ERROR_TEST(TestController.class, "/test/error", "内部报错测试"),
    FILE_UPLOAD_TEST(TestController.class, "/test/upload", "文件上传测试"),
    FILE_DOWNLOAD_TEST(TestController.class, "/test/url", "获取文件下载的路径"),
    MULTI_RETURN_TEST(TestController.class, "/test/multiParams", "返回多个参数的测试"),
    MONGODB_TEST(TestController.class, "/test/mongodb", "mongodb数据库功能测试")
    ;

    BizTypeEnum(Class<?> controller, String httpPath, String description) {
        this.controller = controller;
        this.description = description;
        this.httpPath = httpPath;
    }

    /** 执行业务具体的类 */
    Class<?> controller;
    /** 业务对应的http请求路径 */
    String httpPath;
    /** 业务描述 */
    String description;

    public Class<?> getControllerClass() {
        return controller;
    }

    public String getDescription() {
        return description;
    }

    public String getHttpPath() {
        return httpPath;
    }
}
