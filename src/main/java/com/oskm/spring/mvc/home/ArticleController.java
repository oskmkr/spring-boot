package com.oskm.spring.mvc.home;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/")
@Controller
public class ArticleController {

    private static final Logger LOG = Logger.getLogger(ArticleController.class);

    //@Secured("ROLE_ADMIN")
    @RequestMapping(value = "Article", method = {RequestMethod.GET})
    @ResponseBody
    public String read() {
        LOG.debug("home..");

        return "Home";
    }
}
