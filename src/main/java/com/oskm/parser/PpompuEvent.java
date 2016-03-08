package com.oskm.parser;

import java.io.Serializable;

/**
 * Created by oskm on 2016-03-08.
 */
public class PpompuEvent implements Serializable {

    private static final long serialVersionUID = -4548681402123269922L;

    private String category;
    private String thumbnail;
    private String title;
    private String link;
    private String writeDate;

    public String getCategory() {
        return category;
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
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }
}
