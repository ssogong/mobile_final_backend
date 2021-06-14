package com.tsinghua.course.Biz.Controller.Params.PostParams.Out;

import com.tsinghua.course.Base.Model.Post;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;

/**
 * @描述 获取所有动态的出参
 */

public class GetPostsOutParams extends CommonOutParams {
    // 所有动态
    public List<Post> posts;

    public GetPostsOutParams(List<Post> posts) {
        this.posts = posts;
    }

}
