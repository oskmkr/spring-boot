package com.oskm;

import com.oskm.finder.LatestEventFinder;
import com.oskm.parser.ClienEvent;
import com.oskm.parser.PpompuEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by oskm on 2016-03-28.
 */
@Configuration
public class BeanConfig {

    @Bean
    public LatestEventFinder<ClienEvent> getLatestClienEventFinder() {
        LatestEventFinder<ClienEvent> latestClienEventEventFinder = new LatestEventFinder<>();
        return latestClienEventEventFinder;
    }

    @Bean
    public LatestEventFinder<PpompuEvent> getLatestPpompuEventFinder() {
        LatestEventFinder<PpompuEvent> latestPpompuEventEventFinder = new LatestEventFinder<>();
        return latestPpompuEventEventFinder;
    }

}
