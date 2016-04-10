package com.oskm.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

/**
 * Created by oskm on 2016-04-10.
 */
@RestController
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value = "/register", consumes = {"application/json"}, produces = {"application/json"}, method = {RequestMethod.POST})
    @ResponseBody
    public String register(@RequestBody(required = false) String payload) {

        System.out.println("payload : " + payload);

        return payload;

    }
}
