package com.oskm.schedule;

import com.oskm.home.EventCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PeriodCaller implements InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(PeriodCaller.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 10분 마다 실행
     */
    @Scheduled(cron = "0 0/10 0-23 * * *")
    public void analyze() {
        LOG.debug("#serverPort" + serverPort);

        if (serverPort != 8080) {
            return;
        }

        LOG.info("[analyzing start time] : " + dateFormat.format(new Date()));


        clienEventCrawler.analyze();
        ppompuEventCrawler.analyze();

        LOG.info("[analyzing end time] : " + dateFormat.format(new Date()));
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.analyze();
    }

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    @Qualifier("doctcEventCrawler")
    private EventCrawler doctcEventCrawler;

    @Autowired
    @Qualifier("clienEventCrawler")
    private EventCrawler clienEventCrawler;

    @Autowired
    @Qualifier("ppompuEventCrawler")
    private EventCrawler ppompuEventCrawler;


}
