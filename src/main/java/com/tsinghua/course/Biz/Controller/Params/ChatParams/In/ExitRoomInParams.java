package com.tsinghua.course.Biz.Controller.Params.ChatParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 退出群聊的入参
 */
@BizType(BizTypeEnum.CHAT_EXIT)
public class ExitRoomInParams extends CommonInParams {
    // 房间id
    @Required
    private String room_id;

    public String getRoom_id() {
        return room_id;
    }
}
