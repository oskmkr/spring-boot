package com.oskm.parser;

import java.io.Serializable;

/**
 * Created by oskm on 2015-12-21.
 */
public class DoctcEvent implements Serializable {

    private static final long serialVersionUID = -5634384620489650847L;

    private String link;
    private String mainImage;
    private String title;
    private String duration;

    public String getLink() {
        return "http://www.doctc.com" + link.replaceAll("\"", "").replaceAll("location.href=", "");
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMainImage() {
        return "http://www.doctc.com" + mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
