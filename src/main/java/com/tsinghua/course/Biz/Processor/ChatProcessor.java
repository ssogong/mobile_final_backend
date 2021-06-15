package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Constant.KeyConstant;
import com.tsinghua.course.Base.Model.ChatRoom;
import com.tsinghua.course.Base.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @描述 聊天原子处理器
 */
@Component
public class ChatProcessor {
    @Autowired
    MongoTemplate mongoTemplate;

    /** 添加一个房间 */
    public void insertRoom(ChatRoom chatRoom) {
        mongoTemplate.insert(chatRoom);
    }
    /** 加入聊天室 */
    public void addToRoomList(String username, Map<String, String> room_info) {
        Query query = new Query();
        query.addCriteria(Criteria.where(KeyConstant.USERNAME).is(username));
        Update update = new Update().addToSet(KeyConstant.ROOM_LIST, room_info);
        mongoTemplate.updateFirst(query, update, User.class);
    }
    /** 添加用户到聊天室 */
    public void addToUserList(String roomId, Map<String, String> user_info) {
        Query query = new Query();
        query.addCriteria(Criteria.where(KeyConstant.ROOM_ID).is(roomId));
        Update update = new Update().addToSet(KeyConstant.USER_LIST, user_info);
        mongoTemplate.updateFirst(query, update, ChatRoom.class);
    }
    /** 添加一条消息 */
    public void addMessageToList(String roomId, Map<String, String> message) {
        Query query = new Query();
        query.addCriteria(Criteria.where(KeyConstant.ROOM_ID).is(roomId));
        Update update = new Update().addToSet(KeyConstant.MESSAGES, message);
        mongoTemplate.updateFirst(query, update, ChatRoom.class);
    }
}
