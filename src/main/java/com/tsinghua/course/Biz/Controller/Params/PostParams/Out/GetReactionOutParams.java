package com.tsinghua.course.Biz.Controller.Params.PostParams.Out;

import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;
import java.util.Map;

/**
 * @描述 获取点赞和评论信息的出参
 */
public class GetReactionOutParams extends CommonOutParams {
    // 点赞列表
    private List<Map<String, String>> likes;
    // 评论列表
    private List<Map<String, String>> comments;

    public GetReactionOutParams(List<Map<String, String>> likes, List<Map<String, String>> comments) {
        this.likes = likes;
        this.comments = comments;
    }

}
