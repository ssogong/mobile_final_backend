package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Constant.GlobalConstant;
import com.tsinghua.course.Base.Model.Post;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.PostParams.In.AddPostInParams;
import com.tsinghua.course.Biz.Controller.Params.PostParams.In.AddPostReactionInParams;
import com.tsinghua.course.Biz.Controller.Params.PostParams.In.GetPostReactionInParams;
import com.tsinghua.course.Biz.Controller.Params.PostParams.Out.GetPostsOutParams;
import com.tsinghua.course.Biz.Controller.Params.PostParams.Out.GetReactionOutParams;
import com.tsinghua.course.Biz.Processor.PostProcessor;
import com.tsinghua.course.Biz.Processor.UserProcessor;
import io.netty.handler.codec.http.multipart.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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
        Collections.sort(posts);

        return new GetPostsOutParams(posts);
    }
    /** 添加一个动态 */
    @NeedLogin
    @BizType(BizTypeEnum.POST_ADD)
    public CommonOutParams addPost(AddPostInParams inParams) throws Exception {
        String username = inParams.getUsername();
        User user = userProcessor.getUserByUsername(username);
        Post post = new Post();
        // 发布者名称与昵称
        post.setUsername(username);
        post.setRealName(user.getRealName());
        // 发布时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        post.setDate(simpleDateFormat.format(new Date()));
        // 文字
        post.setPostTextMessage(inParams.getPost_text_message());
        // 图片list
        List<FileUpload> imageList = inParams.getPost_photo_message();
        if (imageList != null) {
            List<String> tmpImageList = new ArrayList<>();
            for (FileUpload file : imageList) {
                String url = ChatController.DownloadFileToLocal(file);
                tmpImageList.add(url);
            }
            post.setPostPhotoMessageList(tmpImageList);
        }
        // 视频
        FileUpload videoFile = inParams.getPost_video_message();
        if (videoFile != null) {
            String url = ChatController.DownloadFileToLocal(videoFile);
            post.setPostVideoMessage(url);
        }
        postProcessor.addPost(post);
        return new CommonOutParams(true);
    }
    /** 对动态点赞或评论 */
    @NeedLogin
    @BizType(BizTypeEnum.POST_ADD_REACTION)
    public CommonOutParams addReaction(AddPostReactionInParams inParams) {
        String username = inParams.getUsername();
        User user = userProcessor.getUserByUsername(username);
        Post post = postProcessor.getPostById(inParams.getPost_id());
        Map<String, String> newReaction = new HashMap<>();
        newReaction.put("sender_username", username);
        newReaction.put("sender_real_name", user.getRealName());
        newReaction.put("avartar", user.getAvartar());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        newReaction.put("date", simpleDateFormat.format(new Date()));

        // 先判断是不是点赞信息，否则是评论
        String isLike = inParams.getLiked();
        if (isLike != null) {
            if (isLike.equals("true")) {
                post.addLikes(newReaction);
                postProcessor.addLikeById(inParams.getPost_id(), newReaction);
            }
            // false 取消点赞?
        } else {
            String comment = inParams.getComment();
            newReaction.put("text_message", comment);
            post.addComments(newReaction);
            postProcessor.addCommentById(inParams.getPost_id(), newReaction);
        }

        return new CommonOutParams(true);
    }

    /** 获取动态点赞和评论信息 */
    @NeedLogin
    @BizType(BizTypeEnum.POST_GET_REACTION)
    public GetReactionOutParams getReaction(GetPostReactionInParams inParams) {
        String postId = inParams.getPost_id();
        Post post = postProcessor.getPostById(postId);
        return new GetReactionOutParams(post.getLikes(), post.getComments());
    }
}
