package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Constant.KeyConstant;
import com.tsinghua.course.Base.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * @描述 用户原子处理器，所有与用户相关的原子操作都在此处理器中执行
 **/
@Component
public class UserProcessor {
    @Autowired
    MongoTemplate mongoTemplate;

    /** 根据用户名从数据库中获取用户 */
    public User getUserByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where(KeyConstant.USERNAME).is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    /** 添加一个用户 */
    public void insertUser(User user) {
        mongoTemplate.insert(user);
    }
    /** 修改密码 */
    public void changePass(String username, String newPass) {
        Query query = new Query(Criteria.where(KeyConstant.USERNAME).is(username));
        Update update = new Update().set(KeyConstant.PASSWORD, newPass);
        mongoTemplate.updateFirst(query, update, User.class);
    }

    /** 修改用户个人信息 */
    public void editUser(String username, String realName, String dateOfBirth) {
        Query query = new Query(Criteria.where(KeyConstant.USERNAME).is(username));
        Update update = new Update().set(KeyConstant.REALNAME, realName);
        update.set(KeyConstant.DATE_OF_BIRTH, dateOfBirth);
        mongoTemplate.updateMulti(query, update, User.class);
    }
}
