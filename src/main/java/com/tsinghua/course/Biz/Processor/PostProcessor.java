package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Constant.KeyConstant;
import com.tsinghua.course.Base.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @描述 动态原子处理器，所有与动态相关的原子操作都在此处理器中执行
 */
@Component
public class PostProcessor {
    @Autowired
    MongoTemplate mongoTemplate;

    /** 根据用户名从数据库中获取所有动态 */
    public List<Post> getPostsByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where(KeyConstant.USERNAME).is(username));
        return mongoTemplate.find(query, Post.class);
    }
    /** 发布一个动态 */
    public void addPost(Post post) {
        mongoTemplate.insert(post);
    }

}
