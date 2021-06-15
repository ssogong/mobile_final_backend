package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Constant.KeyConstant;
import com.tsinghua.course.Base.Model.Post;
import com.tsinghua.course.Base.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @描述 动态原子处理器，所有与动态相关的原子操作都在此处理器中执行
 */
@Component
public class PostProcessor {
    @Autowired
    MongoTemplate mongoTemplate;

    /** 根据post id从数据库获取动态 */
    public Post getPostById(String postId) {
        Query query = new Query();
        query.addCriteria(Criteria.where(KeyConstant.MONGO_ID).is(postId));
        return mongoTemplate.findOne(query, Post.class);
    }
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
    /** 根据post id，添加点赞信息 */
    public void addLikeById(String postId, Map<String, String> like) {
        Query query = new Query();
        query.addCriteria(Criteria.where(KeyConstant.MONGO_ID).is(postId));
        Update update = new Update().addToSet(KeyConstant.LIKES, like);
        mongoTemplate.updateFirst(query, update, Post.class);
    }
    /** 根据post id，添加评论信息 */
    public void addCommentById(String postId, Map<String, String> comment) {
        Query query = new Query();
        query.addCriteria(Criteria.where(KeyConstant.MONGO_ID).is(postId));
        Update update = new Update().addToSet(KeyConstant.COMMENTS, comment);
        mongoTemplate.updateFirst(query, update, Post.class);
    }

}
