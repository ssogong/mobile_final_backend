package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 修改个人信息的入参
 */
@BizType(BizTypeEnum.USER_EDIT)
public class EditUserInParams extends CommonInParams {
    @Required
    private String real_name;
    @Required
    private String date_of_birth;

    public String getReal_name() { return real_name; }
    public String getDate_of_birth() { return date_of_birth; }
}
