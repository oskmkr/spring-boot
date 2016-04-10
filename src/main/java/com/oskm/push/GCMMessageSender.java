package com.oskm.push;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * <p>
 * <p>
 * Created by oskm on 2016-03-14.
 */
public class GCMMessageSender {
    private static final Logger LOG = LoggerFactory.getLogger(GCMMessageSender.class);

    public void send() {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        String payload = null;
        try {
            payload = IOUtils.toString(new BufferedInputStream(ClassLoader.getSystemResourceAsStream("templates/data.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", "key=");

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<String> result = restTemplate.exchange("https://gcm-http.googleapis.com/gcm/send", HttpMethod.POST, entity, String.class);

        String body = result.getBody();

        LOG.debug("response body " + body);


    }

}
