package com.oskm.spring.mvc.home;

import com.oskm.Application;
import com.oskm.db.file.FileStoreTemplate;
import com.oskm.parser.PpompuEvent;
import com.oskm.parser.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oskm on 2016-03-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class PpompuEventCrawlerTest {

    @Configuration
    @Import({Application.class})
    static class ContextConfiguration {
    }

    private PpompuEventCrawler uut = new PpompuEventCrawler();

    @Autowired
    @Qualifier("ppompuEventParser")
    private Parser<PpompuEvent> parser;

    @Autowired
    private FileStoreTemplate<List<PpompuEvent>> fileStoreTemplate;

    @Before
    public void before() {
        uut.setParser(parser);
        uut.setFileStoreTemplate(fileStoreTemplate);
    }

    @Test
    public void testAnalyze()  {
        uut.analyze();
    }

    @Test
    public void testFindEvent()  {
        // given

        // when
        List<PpompuEvent> actual = uut.findEvent();

        // then
        assertThat(actual.size(), is(20));
    }
}