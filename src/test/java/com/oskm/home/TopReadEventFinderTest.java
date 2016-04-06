package com.oskm.home;

import com.oskm.finder.TopReadEventFinder;
import com.oskm.parser.ClienEvent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oskm on 2016-03-22.
 */
public class TopReadEventFinderTest {

    private TopReadEventFinder<ClienEvent> uut = new TopReadEventFinder<>();

    @Test
    public void find() {

        List<ClienEvent> clienEvents = new ArrayList<>();

        ClienEvent event = new ClienEvent();
        event.setTitle("1개조회수 이벤트");
        event.setReadCount(1);

        clienEvents.add(event);

        event = new ClienEvent();
        event.setTitle("2개조회수 이벤트");
        event.setReadCount(2);

        clienEvents.add(event);

        event = new ClienEvent();
        event.setTitle("3개조회수 이벤트");
        event.setReadCount(3);

        clienEvents.add(event);

        List<ClienEvent> actual = uut.find(clienEvents);


        for(ClienEvent each : actual) {
            System.out.println(each.getTitle());
        }




    }

}