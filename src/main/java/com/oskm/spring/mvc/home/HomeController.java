package com.oskm.spring.mvc.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by oskm on 2016-01-04.
 */
@Controller
public class HomeController {

    @RequestMapping(value="/")
    public String loadHomePage(Model model){
        return "index";
    }

    @RequestMapping(value="/crawler")
    public String crawler(Model model){
        return "crawler";
    }

}
