/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.db.nosql.mongodb.springdata;

import com.oskm.db.nosql.mongodb.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by sungkyu.eo on 2014-09-26.
 */
public class MongoDAO {

    private static final String COLLECTION_NAME = "users";

    public void insert(User user) {
        mongoTemplate.insert(user, COLLECTION_NAME);
    }

    public List<User> selectUser(String userId, String userName) {
        Query query = new Query(Criteria.where("userId").is(userId).where("userName").is(userName));

        return mongoTemplate.find(query, User.class, COLLECTION_NAME);
    }

    public List<User> selectByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));

        return mongoTemplate.find(query, User.class, COLLECTION_NAME);
    }

    public List<User> selectAll() {
        return mongoTemplate.findAll(User.class, COLLECTION_NAME);
    }

    public void updateByUserId(User user) {
        Query query = new Query(Criteria.where("userId").is(user.getUserId()));

        Update update = new Update();
        update.set("userName", user.getUserName());
        update.set("age", user.getAge());
        update.set("updateDate", user.getUpdateDate());

        mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
    }

    public void deleteByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        mongoTemplate.remove(query, COLLECTION_NAME);

    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
