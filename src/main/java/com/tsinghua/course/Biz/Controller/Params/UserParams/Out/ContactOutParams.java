package com.tsinghua.course.Biz.Controller.Params.UserParams.Out;

import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

/**
 * @描述 返回联系人列表
 */
public class ContactOutParams extends CommonOutParams {
    public String[] contact_list;

    public ContactOutParams(String[] contactList) {
        contact_list = contactList;
    }
}
