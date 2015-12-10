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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by sungkyu.eo on 2014-09-12.
 */
public class MongoDAO {
    private Logger LOG = LoggerFactory.getLogger(MongoDAO.class);
    private MongoDBTemplate client = new MongoDBTemplate("myMongoDB", "users");

    public List<DBObject> select() {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "oskm");

        List<DBObject> result = client.select(searchQuery);

        return result;

    }

    public void delete(DBObject query) {
        client.delete(query);

    }

    public void update(DBObject query, DBObject document) {
        client.update(query, document);
    }

    public void insert(DBObject document) {
        client.insert(document);
    }
}