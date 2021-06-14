package com.tsinghua.course.Base.Model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @描述 对应mongodb中的Post集合
 */
@Document("Post")
public class Post {
    // mongodb唯一id
    String id;
    // 发布者用户名
    String username;
    // 发布者昵称
    String realName;
    // 头像
    String avartar;
    // 动态文字
    String postTextMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public String getPostTextMessage() {
        return postTextMessage;
    }

    public void setPostTextMessage(String postTextMessage) {
        this.postTextMessage = postTextMessage;
    }

}
