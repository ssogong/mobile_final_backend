package com.tsinghua.course.Biz.Controller.Params.ChatParams.Out;

import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;
import java.util.Map;

/**
 * @描述 获取成员信息
 */
public class GetUserListOutParams extends CommonOutParams {
    // 成员列表
    public List<Map<String, String>> user_list;
    public GetUserListOutParams(List<Map<String, String>> user_list) {
        this.user_list = user_list;
    }
}
