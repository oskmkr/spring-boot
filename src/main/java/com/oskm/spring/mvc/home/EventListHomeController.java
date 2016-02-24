package com.oskm.spring.mvc.home;

import com.oskm.parser.DoctcEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by oskm on 2016-01-04.
 */
@Controller
public class EventListHomeController {

    private static final Logger LOG = LoggerFactory.getLogger(EventListHomeController.class);
    @Autowired
    private EventCrawler eventCrawler;

    @RequestMapping(value = "/eventList")
    public ModelAndView list(Model model) {

        DoctcEvent event = eventCrawler.findDoctcEvent();

        model.addAttribute("doctcEvent", event);

        return new ModelAndView("list", model.asMap());
    }
}
