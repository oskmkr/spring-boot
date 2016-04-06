package com.oskm.parser;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oskm on 2016-03-03.
 */
public class ClienEvent implements Serializable, ReadCountFindable, WriteDateFindable, Event {

    private static final long serialVersionUID = 8599115566008150034L;

    private String category;
    private String title;
    private String link;
    private String writeDate;
    private Integer readCount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return "http://www.clien.net/cs2" + link.replaceAll("\\.\\.", "");
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getWriteDate() {
        return writeDate;
    }


    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public Date getWriteDateTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date writeDate = null;
        try {
            writeDate = sf.parse(this.writeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return writeDate;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }
}
