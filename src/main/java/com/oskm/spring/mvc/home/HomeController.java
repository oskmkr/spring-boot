package com.oskm.spring.mvc.home;

import com.oskm.parser.DoctcEvent;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController implements BeanFactoryAware, ApplicationContextAware {

    private static final Logger LOG = Logger.getLogger(HomeController.class);

    @Autowired
    private EventCrawler eventCrawler;

    // @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/hello")
    public String viewHome(Model modelAndView) {
        LOG.debug("### home..");


        return "hello-world";
    }

    @RequestMapping("/hello2")
    @ResponseBody
    public Map<String, String> hello() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("url", "www.home.com");

        return m;
    }

    @RequestMapping(value = "analyze", method = {RequestMethod.GET})
    @ResponseBody
    public String analyze() {
        LOG.debug("ana..");
        eventCrawler.analyzeDoctcEvent();
        return "analyze completed..";
    }

    @RequestMapping(value = "see", method = {RequestMethod.GET})
    @ResponseBody
    public String see(/*@RequestParam Integer age*/) {
        LOG.debug("se..");

        DoctcEvent event = eventCrawler.findDoctcEvent();

        return event.getTitle();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LOG.debug(beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOG.debug(applicationContext);
    }
}