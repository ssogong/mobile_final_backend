package com.tsinghua.course.Biz.Controller.Params.PostParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;
import io.netty.handler.codec.http.multipart.FileUpload;

import java.util.List;

/**
 * @描述 发布动态的入参
 */
@BizType(BizTypeEnum.POST_ADD)
public class AddPostInParams extends CommonInParams {
    // 文字
    private String post_text_message;
    // 图片
    private List<FileUpload> post_photo_message;
    // 视频
    private FileUpload post_video_message;

    public String getPost_text_message() {
        return post_text_message;
    }
    public List<FileUpload> getPost_photo_message() {
        return post_photo_message;
    }
    public FileUpload getPost_video_message() {
        return post_video_message;
    }
}
