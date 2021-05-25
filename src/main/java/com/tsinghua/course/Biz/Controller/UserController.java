package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Enum.UserType;
import com.tsinghua.course.Biz.BizTypeEnum;
import com.tsinghua.course.Base.Error.CourseWarn;
import com.tsinghua.course.Base.Error.UserWarnEnum;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.In.ChangePassInParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.In.EditUserInParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.In.LoginInParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.In.SignupInParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.Out.LoginOutParams;
import com.tsinghua.course.Biz.Processor.UserProcessor;
import com.tsinghua.course.Frame.Util.*;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @描述 用户控制器，用于执行用户相关的业务
 **/
@Component
public class UserController {

    @Autowired
    UserProcessor userProcessor;

    /** 用户登录业务 */
    @BizType(BizTypeEnum.USER_LOGIN)
    public CommonOutParams userLogin(LoginInParams inParams) throws Exception {
        String username = inParams.getUsername();
        if (username == null)
            throw new CourseWarn(UserWarnEnum.LOGIN_FAILED);
        User user = userProcessor.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(inParams.getPassword()))
            throw new CourseWarn(UserWarnEnum.LOGIN_FAILED);

        /** 登录成功，记录登录状态 */
        ChannelHandlerContext ctx =  ThreadUtil.getCtx();
        /** ctx不为空记录WebSocket状态，否则记录http状态 */
        if (ctx != null)
            SocketUtil.setUserSocket(username, ctx);
        else {
            HttpSession httpSession = ThreadUtil.getHttpSession();
            if (httpSession != null) {
                httpSession.setUsername(username);
            }
        }
        return new LoginOutParams(user);
    }

    /** 用户注册业务 */
    @BizType(BizTypeEnum.USER_SIGN_UP)
    public CommonOutParams userSignUp(SignupInParams inParams) throws Exception {
        String resisterName = inParams.getResisterName();
        String password = inParams.getPassword();
        String userType = inParams.getUserType();
        String realName = inParams.getReal_name();
        String dateOfBirth = inParams.getDate_of_birth();
        String gender = inParams.getGender();
        if (resisterName == null || password == null)
            throw new CourseWarn(UserWarnEnum.SIGNUP_NULL_FAILED);
        /** 检查用户是否已存在 */
        User tmpUser = userProcessor.getUserByUsername(resisterName);
        if (tmpUser != null)
            throw new CourseWarn(UserWarnEnum.SIGNUP_EXIST_FAILED);

        /** 添加用户 */
        User user = new User();
        user.setUsername(resisterName);
        user.setPassword(password);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setDateJoined(simpleDateFormat.format(new Date()));
        user.setRealName(realName);
        user.setDateOfBirth(dateOfBirth);
        user.setGender(gender);
        /** 没有userType字段，默认为普通用户 */
        if (userType != null)
            user.setUserType(UserType.ADMIN);
        else
            user.setUserType(UserType.NORMAL);
        userProcessor.insertUser(user);

        return new CommonOutParams(true);
    }
    /** 修改密码 */
    @NeedLogin
    @BizType(BizTypeEnum.USER_CHANGE_PASS)
    public CommonOutParams changePass(ChangePassInParams inParams) throws Exception {
        String username = inParams.getUsername();
        String newPass = inParams.getNew_password();
        if (newPass == null)
            throw new CourseWarn(UserWarnEnum.CHANGE_PASS_FAILED);
        userProcessor.changePass(username, newPass);

        return new CommonOutParams(true);
    }

    /** 修改个人信息 */
    @NeedLogin
    @BizType(BizTypeEnum.USER_EDIT)
    public CommonOutParams editUser(EditUserInParams inParams) throws Exception {
        String username = inParams.getUsername();
        String realNameNew = inParams.getReal_name();
        String dateOfBirthNew = inParams.getDate_of_birth();
        if (realNameNew == null || dateOfBirthNew == null)
            throw new CourseWarn(UserWarnEnum.EDIT_FAILED);
        userProcessor.editUser(username, realNameNew, dateOfBirthNew);

        return new CommonOutParams(true);
    }


}
