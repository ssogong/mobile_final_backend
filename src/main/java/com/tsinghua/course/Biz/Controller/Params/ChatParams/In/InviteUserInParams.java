package com.tsinghua.course.Biz.Controller.Params.ChatParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 邀请用户到群聊入参
 */
@BizType(BizTypeEnum.CHAT_INVITE)
public class InviteUserInParams extends CommonInParams {
    // 房间id
    @Required
    private String room_id;
    // 邀请用户username
    @Required
    private String invite_username;

    public String getRoom_id() {
        return room_id;
    }

    public String getInvite_username() {
        return invite_username;
    }
}
