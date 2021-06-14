package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Model.Post;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;
import com.tsinghua.course.Biz.Controller.Params.PostParams.Out.GetPostsOutParams;
import com.tsinghua.course.Biz.Processor.PostProcessor;
import com.tsinghua.course.Biz.Processor.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @描述 动态控制器，用于执行动态相关的业务
 */
@Component
public class PostController {
    @Autowired
    PostProcessor postProcessor;
    @Autowired
    UserProcessor userProcessor;

    /** 获取所有动态 */
    @NeedLogin
    @BizType(BizTypeEnum.POST_GET_ALL)
    public GetPostsOutParams getPosts(CommonInParams inParams) throws Exception {
        String username = inParams.getUsername();
        User user = userProcessor.getUserByUsername(username);
        String[] contactList = user.getContactList();
        // 依据联系人列表，获取动态
        List<Post> posts = postProcessor.getPostsByUsername(username);  // 本人的
        for (String tmpUser : contactList) {
            List<Post> tmpPosts = postProcessor.getPostsByUsername(tmpUser);
            posts.addAll(tmpPosts);
        }
        // 依据时间排序

        //
        return new GetPostsOutParams(posts);
    }

}
