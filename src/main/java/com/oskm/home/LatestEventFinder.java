package com.oskm.home;

import com.oskm.parser.Event;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by oskm on 2016-03-22.
 */
public class LatestEventFinder<T extends Event> {

    public List<T> find(final List<T> eventList) {

        List<T> sortedEventList = new ArrayList<>(eventList);

        if (sortedEventList.size() < 3) {
            return sortedEventList;
        }

        return sortedEventList.subList(0, 3);
    }

}
