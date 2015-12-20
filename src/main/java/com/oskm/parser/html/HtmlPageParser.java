package com.oskm.parser.html;

import com.oskm.parser.DoctcEvent;
import com.oskm.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by oskm on 2015-12-06.
 */
public class HtmlPageParser implements Parser {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlPageParser.class);


    public void parse(String url) throws URISyntaxException, IOException {
        Document doc = Jsoup.parse(new URL("http://www.doctc.com/shop/event/event_main.php"), 3000);


        Elements elParent = doc.select("#event_top > div > div");

        int size = elParent.size();

        LOG.debug("size : " + size);

        for (int i = 0; i < size; i++) {
            Elements el = doc.select("#event_top > div > div:nth-child(" + (i + 1) + ")");

            Elements thumbnail = el.select("div.thumbnail");

            DoctcEvent event = new DoctcEvent();

            event.setLink(thumbnail.attr("onclick"));
            event.setMainImage(el.select("div.gdListImg > img").attr("src"));
            event.setTitle(el.select("div.gdListText > div.gdListName").html());
            event.setDuration(el.select("div.gdListText > div.gdDate").html());

            LOG.debug("상세 링크 : " + event.getLink());
            LOG.debug("대표 이미지 : " + event.getMainImage());
            LOG.debug("타이틀 : " + event.getTitle());
            LOG.debug("기간 : " + event.getDuration());

            //String s = el.html();

            //LOG.debug(s);
        }


        //return event;
    }

}
