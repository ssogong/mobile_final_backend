package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Constant.GlobalConstant;
import com.tsinghua.course.Base.Model.ChatRoom;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.ChatParams.In.*;
import com.tsinghua.course.Biz.Controller.Params.ChatParams.Out.GetMessagesOutParams;
import com.tsinghua.course.Biz.Controller.Params.ChatParams.Out.GetMyRoomsOutParams;
import com.tsinghua.course.Biz.Controller.Params.ChatParams.Out.GetUserListOutParams;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Processor.ChatProcessor;
import com.tsinghua.course.Biz.Processor.UserProcessor;
import io.netty.handler.codec.http.multipart.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

    /**
     * 获取所有加入的聊天室
     */
    @NeedLogin
    @BizType(BizTypeEnum.CHAT_GET_ROOMS)
    public GetMyRoomsOutParams getMyRooms(CommonInParams inParams) throws Exception {
        String username = inParams.getUsername();
        User user = userProcessor.getUserByUsername(username);
        return new GetMyRoomsOutParams(user.getRoomList());
    }
    /**
     * 发送一条消息
     */
    @NeedLogin
    @BizType(BizTypeEnum.CHAT_SEND_MESSAGE)
    public CommonOutParams sendMessage(SendMessageInParams inParams) throws Exception {
        String username = inParams.getUsername();
        String roomId = inParams.getRoom_id();
        Map<String, String> message = new HashMap<>();
        message.put("message_id", UUID.randomUUID().toString());
        message.put("sender_username", username);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.put("date", simpleDateFormat.format(new Date()));
        //文字 / 语音 / 视频 / 定位
        if (inParams.getText_message() != null) {
            message.put("text_message", inParams.getText_message());
        } else if (inParams.getAudio_message() != null) {
            FileUpload file = inParams.getAudio_message();
            String url = DownloadFileToLocal(file);
            message.put("audio_message", url);
        } else if (inParams.getVideo_message() != null) {
            FileUpload file = inParams.getVideo_message();
            String url = DownloadFileToLocal(file);
            message.put("video_message", url);
        } else {
            message.put("location_message", inParams.getLocation_message());
        }
        // 添加到room
        chatProcessor.addMessageToList(roomId, message);

        return new CommonOutParams(true);
    }

    /**
     * 删除一条消息
     */
    @NeedLogin
    @BizType(BizTypeEnum.CHAT_DELETE_MESSAGE)
    public CommonOutParams deleteMessage(DeleteMessageInParams inParams) throws Exception {
        String roomId = inParams.getRoom_id();
        String messageId = inParams.getMessage_id();
        ChatRoom room = chatProcessor.getRoomByRoomId(roomId);
        List<Map<String, String>> messages = room.getMessages();
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).get("message_id").equals(messageId)) {
                messages.remove(i);
                break;
            }
        }
        chatProcessor.updateMessages(roomId, messages);
        return new CommonOutParams(true);
    }

    /**
     * 获取消息列表
     */
    @NeedLogin
    @BizType(BizTypeEnum.CHAT_GET_MESSAGES)
    public GetMessagesOutParams getMessages(GetMessagesInParams inParams) throws Exception {
        String roomId = inParams.getRoom_id();
        ChatRoom room = chatProcessor.getRoomByRoomId(roomId);
        return new GetMessagesOutParams(room.getMessages());
    }

    /**
     * 获取成员列表
     */
    @BizType(BizTypeEnum.CHAT_GET_MEMBERS_INFO)
    public GetUserListOutParams getUserList(GetUserListInParams inParams) throws Exception {
        String roomId = inParams.getRoom_id();
        ChatRoom room = chatProcessor.getRoomByRoomId(roomId);
        return new GetUserListOutParams(room.getUserList());
    }

    static String DownloadFileToLocal(FileUpload file) throws IOException {
        String oldFileName = file.getFilename();
        String newFileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
        String url = GlobalConstant.STATIC_PATH + newFileName;
        FileOutputStream out = new FileOutputStream(url);
        out.write(file.get());
        out.flush();
        out.close();
        return url;
    }

}
