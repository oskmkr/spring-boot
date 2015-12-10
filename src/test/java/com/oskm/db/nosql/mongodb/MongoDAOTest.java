/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.db.nosql.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class MongoDAOTest {
    private static final Logger LOG = LoggerFactory.getLogger(MongoDAOTest.class);

    private MongoDAO dao = new MongoDAO();

    @Test
    public void select() {

        // given

        // when
        List<DBObject> result = dao.select();

        LOG.debug("result size" + result.size());

        assertThat(result.size(), greaterThan(0));

        for (DBObject each : result) {
            LOG.debug("data : " + String.valueOf(each));
        }

        // then
    }

    @Test
    public void insert() {
        DBObject document = new BasicDBObject();
        document.put("name", "oskmkr");
        document.put("age", 30);
        document.put("createdDate", new Date());

        dao.insert(document);
    }

    @Test
    public void update() {
        DBObject query = new BasicDBObject();
        query.put("name", "oskmkr");

        DBObject newDocument = new BasicDBObject();
        newDocument.put("age", "31");

        DBObject updateObj = new BasicDBObject();
        updateObj.put("$set", newDocument);

        dao.update(query, updateObj);
    }

    @Test
    public void delete() {
        DBObject query = new BasicDBObject().append("name", "oskmkr");

        dao.delete(query);
    }

}