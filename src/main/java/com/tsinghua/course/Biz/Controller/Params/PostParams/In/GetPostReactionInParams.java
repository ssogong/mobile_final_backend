package com.tsinghua.course.Biz.Controller.Params.PostParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

import java.util.List;
import java.util.Map;

/**
 * @描述 获取动态的点赞和评论信息的入参
 */
@BizType(BizTypeEnum.POST_GET_REACTION)
public class GetPostReactionInParams extends CommonInParams {
    // 动态id
    private String post_id;

    public String getPost_id() {
        return post_id;
    }

}

