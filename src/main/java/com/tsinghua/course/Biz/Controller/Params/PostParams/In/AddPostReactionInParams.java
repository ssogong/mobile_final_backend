package com.tsinghua.course.Biz.Controller.Params.PostParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 点赞或发布动态的入参
 */
@BizType(BizTypeEnum.POST_ADD_REACTION)
public class AddPostReactionInParams extends CommonInParams {
    // post id
    private String post_id;
    // 点赞
    private String liked;
    // 评论
    private String comment;

    public String getPost_id() {
        return post_id;
    }
    public String getLiked() {
        return liked;
    }

    public String getComment() {
        return comment;
    }
}
