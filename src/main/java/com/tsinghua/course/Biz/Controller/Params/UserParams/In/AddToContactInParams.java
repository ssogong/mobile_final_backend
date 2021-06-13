package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 添加到联系人入参
 */
@BizType(BizTypeEnum.CONTACT_ADD)
public class AddToContactInParams extends CommonInParams {
    // 目标用户名
    @Required
    private String new_username;

    public String getNew_username() {
        return new_username;
    }
}
