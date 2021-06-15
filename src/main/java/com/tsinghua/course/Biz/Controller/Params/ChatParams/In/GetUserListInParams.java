package com.tsinghua.course.Biz.Controller.Params.ChatParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * 描述 获取消息信息或消息列表的入参
 */
@BizType(BizTypeEnum.CHAT_GET_MEMBERS_INFO)
public class GetUserListInParams extends CommonInParams {
    // 房间id
    @Required
    private String room_id;
    
    public String getRoom_id() {
        return room_id;
    }
}
