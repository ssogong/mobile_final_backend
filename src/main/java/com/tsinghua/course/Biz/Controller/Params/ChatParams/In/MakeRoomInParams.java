package com.tsinghua.course.Biz.Controller.Params.ChatParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

import java.util.List;

/**
 * @描述 创建房间的入参
 */
@BizType(BizTypeEnum.CHAT_MAKE_ROOM)
public class MakeRoomInParams extends CommonInParams {
    // 房间名
    private String room_name;
    // 邀请成员username列表
    // 长度为1为单人聊天
    // 大于1为群聊
    private List<String> username_list;

    public String getRoom_name() {
        return room_name;
    }
    public List<String> getUsername_list() {
        return username_list;
    }
}
