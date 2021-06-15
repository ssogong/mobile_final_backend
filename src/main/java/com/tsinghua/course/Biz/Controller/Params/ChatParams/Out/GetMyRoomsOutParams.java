package com.tsinghua.course.Biz.Controller.Params.ChatParams.Out;

import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;
import java.util.Map;

/**
 * @描述 获取我的所有房间
 */
public class GetMyRoomsOutParams extends CommonOutParams {
    // 所有房间
    public List<Map<String, String>> room_list;

    public GetMyRoomsOutParams(List<Map<String, String>> room_list) {
        this.room_list = room_list;
    }
}
