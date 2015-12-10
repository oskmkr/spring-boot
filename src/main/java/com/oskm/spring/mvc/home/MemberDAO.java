package com.oskm.spring.mvc.home;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDAO {
    private static final String NAMESPACE = "oskm.test.Member.";

    public void insertMember(String userId, String nickName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("nickName", nickName);

        client.insert(NAMESPACE + "insertMember", params);
    }

    public Member selectMember(String userId) {
        return client.selectOne(NAMESPACE + "selectMember", userId);
    }

    @Autowired
    @Qualifier("testTemplate")
    private SqlSessionTemplate client;

    public void setClient(SqlSessionTemplate client) {
        this.client = client;
    }

}
