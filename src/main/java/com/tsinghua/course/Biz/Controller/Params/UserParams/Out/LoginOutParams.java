package com.tsinghua.course.Biz.Controller.Params.UserParams.Out;

import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

/**
 * @描述 用户登录的返回类型
 */
public class LoginOutParams extends CommonOutParams {
    private String id;
    private String username;
    private String date_joined;
    private String user_type;
    private String real_name;
    private String date_of_birth;
    private String gender;

    public LoginOutParams(User user) {
        id = user.getId();
        username = user.getUsername();
        date_joined = user.getDateJoined();
        user_type = user.getUserType().getName();
        real_name = user.getRealName();
        date_of_birth = user.getDateOfBirth();
        gender = user.getGender();
    }
}
