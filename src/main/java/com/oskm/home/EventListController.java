package com.oskm.home;

import com.oskm.crawler.EventCrawler;
import com.oskm.parser.ClienEvent;
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

    @RequestMapping(value = "/clien")
    public ModelAndView clien(Model model) {

        List<ClienEvent> clienEventList = clienEventCrawler.findEvent();

        model.addAttribute("clienEventList", clienEventList);

        return new ModelAndView("clien", model.asMap());
    }

    @RequestMapping(value = "/ppompu")
    public ModelAndView ppompu(Model model) {

        List<PpompuEvent> ppompuEventList = ppompuEventCrawler.findEvent();
        model.addAttribute("ppompuEventList", ppompuEventList);

        return new ModelAndView("ppompu", model.asMap());
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
