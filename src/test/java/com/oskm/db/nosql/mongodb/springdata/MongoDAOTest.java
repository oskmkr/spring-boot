/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.db.nosql.mongodb.springdata;

import com.oskm.db.nosql.mongodb.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-propertyplaceholder.xml", "classpath:applicationContext-mongo.xml"})
public class MongoDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(MongoDAOTest.class);

    private MongoDAO dao = new MongoDAO();

    @Before
    public void before() {
        dao.setMongoTemplate(mongoTemplate);
    }

    @Test
    public void insert() {
        User user = new User();

        user.setUserId("oskm");
        user.setAge(30);
        user.setUserName("sungkyu");
        user.setUpdateDate(new Date());

        dao.insert(user);
    }

    @Test
    public void selectByUserName() {
        List<User> userList = dao.selectByUserName("sungkyu");

        for (User user : userList) {
            LOG.debug("[userName]" + user.getUserName());
        }
    }

    @Test
    public void selectAll() {
        List<User> userList = dao.selectAll();

        for (User user : userList) {
            LOG.debug("[userName]" + user.getUserName());
        }
    }

    @Test
    public void selectUser() {
        List<User> userList = dao.selectUser("oskm", "sungkyu");

        for (User user : userList) {
            LOG.debug("[userName]" + user.getUserName());
        }
    }

    @Test
    public void updateByUserId() {

        User user = new User();
        user.setUserId("oskm");
        user.setUserName("sungkyu");
        user.setAge(20);
        user.setUpdateDate(new Date());

        dao.updateByUserId(user);
    }

    @Test
    public void deleteByUserName() {
        String userId = "gg";
        String userName = "goodgame";

        User user = new User();

        user.setUserId(userId);
        user.setAge(18);
        user.setUserName(userName);
        user.setUpdateDate(new Date());

        dao.insert(user);

        dao.deleteByUserName(userName);


    }

    @Autowired
    private MongoTemplate mongoTemplate;
}