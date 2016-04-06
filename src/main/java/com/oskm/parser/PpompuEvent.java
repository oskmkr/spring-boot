package com.oskm.parser;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by oskm on 2016-03-08.
 */
public class PpompuEvent implements Serializable, ReadCountFindable, WriteDateFindable, Event {

    private static final long serialVersionUID = -4548681402123269922L;

    private String category;
    private String thumbnail;
    private String title;
    private String link;
    private String writeDate;
    private Integer readCount;

    public String getCategory() {
        return "[" + category + "]";
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return "http://www.ppomppu.co.kr/zboard/" + link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getWriteDate() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(this.getWriteDateTime());
    }

    public Date getWriteDateTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yy.MM.dd HH:mm:ss");

        Date writeDate = null;
        try {
            writeDate = sf.parse(this.writeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }
}
