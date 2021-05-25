package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 用户登录的入参
 **/
@BizType(BizTypeEnum.USER_CHANGE_PASS)
public class ChangePassInParams extends CommonInParams {
    // 新密码
    @Required
    private String new_password;

    public String getNew_password() {
        return new_password;
    }
}