package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;
import io.netty.handler.codec.http.multipart.FileUpload;

/**
 * @描述 上传头像
 */
@BizType(BizTypeEnum.USER_IMAGE)
public class UploadUserImageInParams extends CommonInParams {
    // 头像文件
    @Required
    private FileUpload file;

    public FileUpload getFile() { return file; }
}
