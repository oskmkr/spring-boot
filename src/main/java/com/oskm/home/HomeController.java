package com.oskm.home;

import com.oskm.parser.ClienEvent;
import com.oskm.parser.DoctcEvent;
import com.oskm.parser.Event;
import com.oskm.parser.PpompuEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oskm on 2016-01-04.
 */
@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private DoctcEventCrawler eventCrawler;

    @RequestMapping(value = "/{app}")
    public ModelAndView loadHomePage(Model model) {
        List<ClienEvent> clienEventList = clienEventCrawler.findEvent();
        List<PpompuEvent> ppompuEventList = ppompuEventCrawler.findEvent();

        /*
        for (DoctcEvent event : eventList) {
            LOG.debug("[event model]" + event);
            System.out.println("[event model]" + event);
        }
        */

        List<ClienEvent> top3ClienEventList = new TopReadEventFinder<ClienEvent>().find(clienEventList);
        List<PpompuEvent> top3PpompuEventList = new TopReadEventFinder<PpompuEvent>().find(ppompuEventList);

        List<Event> top3EventList = new ArrayList<Event>();
        top3EventList.addAll(top3ClienEventList);
        top3EventList.addAll(top3PpompuEventList);


        model.addAttribute("top3EventList", top3EventList);
        model.addAttribute("top3ClienEventList", top3ClienEventList);

        return new ModelAndView("home", model.asMap());
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
