package com.oskm.spring.mvc.home;

import java.util.List;

/**
 * Created by oskm on 2016-03-06.
 */
public interface EventCrawler<T> {

    void analyze();

    List<T> findEvent();
}
