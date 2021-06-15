package com.tsinghua.course.Biz.Controller.Params.ChatParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;
import io.netty.handler.codec.http.multipart.FileUpload;

/**
 * @描述 发送消息的入参
 */
@BizType(BizTypeEnum.CHAT_SEND_MESSAGE)
public class SendMessageInParams extends CommonInParams {
    // 房间id
    @Required
    private String room_id;
    // 文字
    private String text_message;
    // 语音
    private FileUpload audio_message;
    // 视频
    private FileUpload video_message;
    // 定位url
    private String location_message;

    public String getRoom_id() {
        return room_id;
    }

    public String getText_message() {
        return text_message;
    }

    public FileUpload getAudio_message() {
        return audio_message;
    }

    public FileUpload getVideo_message() {
        return video_message;
    }

    public String getLocation_message() {
        return location_message;
    }
}
