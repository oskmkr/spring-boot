package com.oskm.home;

import com.oskm.parser.DoctcEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by oskm on 2016-01-04.
 */
@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private DoctcEventCrawler eventCrawler;

    @RequestMapping(value = "/")
    public String loadHomePage(Model model) {
        return "forward:eventList";
    }

    @RequestMapping(value = "/crawler")
    public String crawler(Model model) {

        eventCrawler.analyze();
        List<DoctcEvent> eventList = eventCrawler.findEvent();

        for (DoctcEvent event : eventList) {
            LOG.debug("[event model]" + event);
            System.out.println("[event model]" + event);
        }

        return "crawler";
    }
}
