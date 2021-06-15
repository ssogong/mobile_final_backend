package com.tsinghua.course.Base.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    // 点赞列表
    List<Map<String, String>> likes;
    // 评论列表
    List<Map<String, String>> comments;

    public Post() {
        likes = new ArrayList<>();
        comments = new ArrayList<>();
    }
    public void initPostPhotoMessageList() {
        postPhotoMessageList = new ArrayList<>();
    }

    // 按时间比较重载
    @Override
    public int compareTo(Post o) {
        return o.getDate().compareTo(getDate());
    }

    public void addLikes(Map<String, String> m) {
        likes.add(m);
    }
    public void addComments(Map<String, String> m) {
        comments.add(m);
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

    public List<Map<String, String>> getLikes() {
        return likes;
    }

    public void setLikes(List<Map<String, String>> likes) {
        this.likes = likes;
    }

    public List<Map<String, String>> getComments() {
        return comments;
    }

    public void setComments(List<Map<String, String>> comments) {
        this.comments = comments;
    }

}
