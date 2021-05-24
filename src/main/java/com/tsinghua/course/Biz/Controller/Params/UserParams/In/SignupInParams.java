package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

/**
 * @描述 用户注册的入参
 */
@BizType(BizTypeEnum.USER_SIGN_UP)
public class SignupInParams extends CommonInParams {
    // 待注册用户名
    @Required String resister_name;
    // 密码
    @Required
    private String password;
    // 实名
    @Required
    private String real_name;
    // 性别
    @Required
    private String gender;
    // 生日
    @Required
    private String date_of_birth;

    // 用户类型
    private String user_type;

    public String getResisterName() { return resister_name; }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return user_type;
    }
    public String getReal_name() {
        return real_name;
    }

    public String getGender() {
        return gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

}
