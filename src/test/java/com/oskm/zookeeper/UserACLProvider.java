/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.zookeeper;

import org.apache.curator.framework.api.ACLProvider;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sungkyu.eo on 2015-01-07.
 */
public class UserACLProvider implements ACLProvider {
    List<ACL> aclList = new ArrayList<ACL>();

    public UserACLProvider() {
        aclList.addAll(ZooDefs.Ids.CREATOR_ALL_ACL);
        aclList.addAll(ZooDefs.Ids.READ_ACL_UNSAFE);
    }

    @Override
    public List<ACL> getDefaultAcl() {
        return aclList;
    }

    @Override
    public List<ACL> getAclForPath(String s) {
        return aclList;
    }
}