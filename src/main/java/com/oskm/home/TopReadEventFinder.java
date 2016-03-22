package com.oskm.home;

import com.oskm.parser.ReadCountFindable;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by oskm on 2016-03-22.
 */
public class TopReadEventFinder<T extends ReadCountFindable> {

    public List<T> find(List<T> eventList) {

        if (CollectionUtils.isEmpty(eventList)) {
            return Collections.emptyList();
        }

        eventList.sort(comparator);


        if (eventList.size() < 3) {
            return eventList;
        }

        return eventList.subList(0, 3);
    }

    private Comparator<T> comparator = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {

            if (o1.getReadCount() < o2.getReadCount()) {
                return 1;
            }

            if (o1.getReadCount() == o2.getReadCount()) {
                return 0;
            }

            return -1;
        }
    };

}
