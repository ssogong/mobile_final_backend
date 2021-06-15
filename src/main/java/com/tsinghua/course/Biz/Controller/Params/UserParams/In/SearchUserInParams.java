package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 搜索用户的入参
 */
@BizType(BizTypeEnum.USER_SEARCH)
public class SearchUserInParams extends CommonInParams {
    @Required
    private String search_username;

    public String getSearch_username() {
        return search_username;
    }
}
