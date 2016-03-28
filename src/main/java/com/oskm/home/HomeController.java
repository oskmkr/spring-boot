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
import java.util.Comparator;
import java.util.List;

/**
 * Created by oskm on 2016-01-04.
 */
@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/{app}")
    public ModelAndView loadHomePage(Model model) {
        List<ClienEvent> clienEventList = clienEventCrawler.findEvent();
        List<PpompuEvent> ppompuEventList = ppompuEventCrawler.findEvent();

        List<ClienEvent> top3ClienEventList = new TopReadEventFinder<ClienEvent>().find(clienEventList);
        List<PpompuEvent> top3PpompuEventList = new TopReadEventFinder<PpompuEvent>().find(ppompuEventList);

        List<Event> top3EventList = new ArrayList<Event>();

        top3EventList.addAll(top3ClienEventList);
        top3EventList.addAll(top3PpompuEventList);

        top3EventList.sort(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if (o1.getReadCount() < o2.getReadCount()) {
                    return 1;
                }

                if (o1.getReadCount() > o2.getReadCount()) {
                    return -1;
                }

                return 0;
            }
        });

        List<ClienEvent> latestClienEventList = latestClienEventFinder.find(clienEventList);
        List<PpompuEvent> latestPpompuEventList = latestPpompuEventFinder.find(ppompuEventList);

        model.addAttribute("top3EventList", top3EventList);
        model.addAttribute("latestClienEventList", latestClienEventList);
        model.addAttribute("latestPpompuEventList", latestPpompuEventList);

        return new ModelAndView("home", model.asMap());
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

    @Autowired
    private LatestEventFinder<ClienEvent> latestClienEventFinder;
    @Autowired
    private LatestEventFinder<PpompuEvent> latestPpompuEventFinder;
}
