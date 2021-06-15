package com.tsinghua.course.Biz.Controller.Params.UserParams.Out;

import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

/**
 * @描述 搜索联系人的出参
 */
public class SearchUserOutParams extends CommonOutParams {
    public String username;
    public String real_name;
    public String gender;
    public String avartar;

    public SearchUserOutParams(User user) {
        username = user.getUsername();
        real_name = user.getRealName();
        gender = user.getGender();
        avartar = user.getAvartar();
    }
}
