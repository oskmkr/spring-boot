package com.oskm.parser;

/**
 * Created by oskm on 2016-03-23.
 */
public interface Event {
    public String getCategory();

    public String getTitle();

    public String getLink();

    public String getWriteDate();

    public Integer getReadCount();
}
