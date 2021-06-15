package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Model.ChatRoom;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.ChatParams.In.MakeRoomInParams;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Processor.ChatProcessor;
import com.tsinghua.course.Biz.Processor.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @描述 聊天控制器
 */
@Component
public class ChatController {
    @Autowired
    ChatProcessor chatProcessor;
    @Autowired
    UserProcessor userProcessor;

    /**     创建聊天室
     * 邀请一人为单人聊天
     * 邀请多人为群聊
     */
    @NeedLogin
    @BizType(BizTypeEnum.CHAT_MAKE_ROOM)
    public CommonOutParams makeRoom(MakeRoomInParams inParams) throws Exception {
        String username = inParams.getUsername();
        User user = userProcessor.getUserByUsername(username);
        ChatRoom newRoom = new ChatRoom();
        newRoom.setRoomId(UUID.randomUUID().toString());

        List<String> usernameList = inParams.getUsername_list();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        // 设置房间名称，默认为用户名拼接
        StringBuilder roomName = new StringBuilder(user.getRealName());
        for (int i = 0; i < usernameList.size(); i++) {
            User tmpUser = userProcessor.getUserByUsername(usernameList.get(i));
            userList.add(tmpUser);
            roomName.append("、");
            roomName.append(tmpUser.getRealName());
        }
        if (inParams.getRoom_name() != null){
            roomName = new StringBuilder(inParams.getRoom_name());
        }
        newRoom.setRoomName(roomName.toString());
        Map<String, String> roomInfo = new HashMap<>();
        // 添加到用户房间列表
        roomInfo.put("id", newRoom.getRoomId());
        roomInfo.put("room_name", newRoom.getRoomName());
        for (User u : userList) {
            chatProcessor.addToRoomList(u.getUsername(), roomInfo);
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("username", u.getUsername());
            userInfo.put("real_name", u.getRealName());
            userInfo.put("avartar", u.getAvartar());
            newRoom.addUser(userInfo);
        }
        // 添加房间
        chatProcessor.insertRoom(newRoom);

        return new CommonOutParams(true);
    }

}
