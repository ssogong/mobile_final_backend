package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 删除联系人的入参
 */
@BizType(BizTypeEnum.CONTACT_DELETE)
public class DeleteUserInParams extends CommonInParams {
    @Required
    private String delete_username;

    public String getDelete_username() {
        return delete_username;
    }
}
