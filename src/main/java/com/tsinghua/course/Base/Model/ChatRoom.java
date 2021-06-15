package com.tsinghua.course.Base.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @描述 对应mongodb中ChatRoom集合
 */
@Document("ChatRoom")
public class ChatRoom {
    // mongodb 唯一id
    String id;
    // 房间创建时唯一id
    String roomId;
    // 房间名称。单人聊天为对方昵称，群聊为自定义（默认用户名称）
    String roomName;
    /**     消息集合
     * message_id：消息唯一id
     * sender_username: 发送者用户名
     * date：发送时间
     * text_message：文字
     * audio_message：语音
     * video_message：视频
     * location_message：定位url
     */
    List<Map<String, String>> messages;

    /**     成员列表
     * username: 用户名
     * real_name: 昵称
     * avartar：头像
     */
    List<Map<String, String>> userList;

    public ChatRoom() {
        messages = new ArrayList<>();
        userList = new ArrayList<>();
    }

    public void addUser(Map<String, String> user) {
        userList.add(user);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<Map<String, String>> getMessages() {
        return messages;
    }

    public void setMessages(List<Map<String, String>> messages) {
        this.messages = messages;
    }

    public List<Map<String, String>> getUserList() {
        return userList;
    }

    public void setUserList(List<Map<String, String>> userList) {
        this.userList = userList;
    }
}
