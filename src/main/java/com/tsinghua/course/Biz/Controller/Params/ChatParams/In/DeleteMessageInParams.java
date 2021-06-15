package com.tsinghua.course.Biz.Controller.Params.ChatParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 删除一条消息
 */
@BizType(BizTypeEnum.CHAT_DELETE_MESSAGE)
public class DeleteMessageInParams extends CommonInParams {
    // 房间id
    @Required
    private String room_id;
    // 消息id
    @Required
    private String message_id;

    public String getRoom_id() {
        return room_id;
    }

    public String getMessage_id() {
        return message_id;
    }
}
