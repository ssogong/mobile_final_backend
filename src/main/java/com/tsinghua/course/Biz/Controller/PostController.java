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
import com.tsinghua.course.Biz.Controller.Params.PostParams.Out.GetPostsOutParams;
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
                String oldFileName = file.getFilename();
                String newFileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
                String url = GlobalConstant.STATIC_PATH + newFileName;
                FileOutputStream out = new FileOutputStream(url);
                out.write(file.get());
                out.flush();
                out.close();
                tmpImageList.add(url);
            }
            post.setPostPhotoMessageList(tmpImageList);
        }
        // 视频
        FileUpload videoFile = inParams.getPost_video_message();
        if (videoFile != null) {
            String oldFileName = videoFile.getFilename();
            String newFileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
            String url = GlobalConstant.STATIC_PATH + newFileName;
            FileOutputStream out = new FileOutputStream(url);
            out.write(videoFile.get());
            out.flush();
            out.close();
            post.setPostVideoMessage(url);
        }
        postProcessor.addPost(post);
        return new CommonOutParams(true);
    }

}
