package com.tsinghua.course.Base.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @描述 对应mongodb中的Post集合
 */
@Document("Post")
public class Post implements Comparable<Post> {
    // mongodb唯一id
    String id;
    // 发布者用户名
    String username;
    // 发布者昵称
    String realName;
    // 发布时间
    String date;
    // 头像
    String avartar;
    // 动态文字
    String postTextMessage;
    // 动态图片list
    List<String> postPhotoMessageList;
    // 动态视频
    String postVideoMessage;

    // 按时间比较重载
    @Override
    public int compareTo(Post o) {
        return o.getDate().compareTo(getDate());
    }

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
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public List<String> getPostPhotoMessageList() {
        return postPhotoMessageList;
    }

    public void setPostPhotoMessageList(List<String> postPhotoMessageList) {
        this.postPhotoMessageList = postPhotoMessageList;
    }

    public String getPostVideoMessage() {
        return postVideoMessage;
    }

    public void setPostVideoMessage(String postVideoMessage) {
        this.postVideoMessage = postVideoMessage;
    }

}
