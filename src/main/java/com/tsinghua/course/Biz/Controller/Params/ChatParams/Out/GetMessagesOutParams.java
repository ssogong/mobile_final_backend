package com.tsinghua.course.Biz.Controller.Params.ChatParams.Out;

import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;
import java.util.Map;

/**
 * @描述 获取消息信息
 */
public class GetMessagesOutParams extends CommonOutParams {
    // 所有消息
    public List<Map<String, String>> messages;

    public GetMessagesOutParams(List<Map<String, String>> messages) {
        this.messages = messages;
    }
}
