/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.db.nosql.mongodb;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/
 * Created by sungkyu.eo on 2014-09-12.
 */
public class MongoDBTemplate {

    private Logger LOG = LoggerFactory.getLogger(MongoDBTemplate.class);

    private MongoClient client;
    private DBCollection dbCollection;

    public MongoDBTemplate(String dbName, String collectionName) {
        try {
            client = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            LOG.error(String.valueOf(e), e);
        }
        DB db = client.getDB(dbName);

        //boolean auth = db.authenticate("username", "password".toCharArray());
        /*
        List<String> dbNameList = client.getDatabaseNames();

        for (String dbName : dbNameList) {
            LOG.debug("[dbName]" + dbName);
        }
        */
        dbCollection = db.getCollection(collectionName);
    }

    public void insert(DBObject document) {
        dbCollection.insert(document);
    }

    public List<DBObject> select(DBObject searchQuery) {
        DBCursor cursor = dbCollection.find(searchQuery);

        List<DBObject> result = new ArrayList<DBObject>();

        while (cursor.hasNext()) {
            result.add(cursor.next());
        }

        return result;
    }

    public void update(DBObject query, DBObject updateObj) {
        dbCollection.updateMulti(query, updateObj);
    }

    public void delete(DBObject searchQuery) {
        dbCollection.remove(searchQuery);
    }


}
