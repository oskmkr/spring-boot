package com.oskm.spring.mvc.home;

import com.oskm.parser.ClienEvent;
import com.oskm.parser.DoctcEvent;
import com.oskm.parser.PpompuEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by oskm on 2016-01-04.
 */
@Controller
public class EventListController {

    private static final Logger LOG = LoggerFactory.getLogger(EventListController.class);

    @RequestMapping(value = "/eventList")
    public ModelAndView list(Model model) {

        doctcEventCrawler.analyze();
        List<DoctcEvent> doctcEventList = doctcEventCrawler.findEvent();

        clienEventCrawler.analyze();
        List<ClienEvent> clienEventList = clienEventCrawler.findEvent();

        ppompuEventCrawler.analyze();
        List<PpompuEvent> ppompuEventList = ppompuEventCrawler.findEvent();

        /*
        for (DoctcEvent event : eventList) {
            LOG.debug("[event model]" + event);
            System.out.println("[event model]" + event);
        }
        */

        model.addAttribute("doctcEventList", doctcEventList);
        model.addAttribute("clienEventList", clienEventList);
        model.addAttribute("ppompuEventList", ppompuEventList);

        return new ModelAndView("EventList", model.asMap());
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
