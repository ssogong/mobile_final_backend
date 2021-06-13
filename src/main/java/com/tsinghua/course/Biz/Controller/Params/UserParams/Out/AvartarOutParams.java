package com.tsinghua.course.Biz.Controller.Params.UserParams.Out;

import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

/**
 * @描述 上传头像返回的类型
 */
public class AvartarOutParams extends CommonOutParams {
    public String avartar;
    public AvartarOutParams(String url) {
        avartar = url;
    }
}

