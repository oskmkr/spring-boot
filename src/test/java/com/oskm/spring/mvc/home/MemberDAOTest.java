package com.oskm.spring.mvc.home;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-propertyplaceholder.xml", "classpath:applicationContext-datasource.xml", "classpath:applicationContext-dao-config.xml"})
//@TransactionConfiguration(defaultRollback=true)
@Transactional
public class MemberDAOTest {

    private static final Logger LOG = Logger.getLogger(MemberDAOTest.class);

    private MemberDAO dao = new MemberDAO();

    @Autowired
    @Qualifier("testTemplate")
    SqlSessionTemplate client;

    @Before
    public void before() {
        dao.setClient(client);
    }

    @Test
    public void selectMember() {

        dao.insertMember("oskm", "oskmnick");

        Member member = dao.selectMember("oskm");


        LOG.debug(member.toString());

        assertThat(member.getUserId(), is("oskm"));
        assertThat(member.getNickName(), is("oskmnick"));
    }

}
