package com.oskm.parser.html;

import com.oskm.parser.DoctcEvent;
import com.oskm.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oskm on 2015-12-06.
 */
@Service
public class DoctcEventPageParser implements Parser<DoctcEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(DoctcEventPageParser.class);


    public List<DoctcEvent> parse() {
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL("http://www.doctc.com/shop/event/event_main.php"), 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Elements elParent = doc.select("#event_top > div > div");

        int size = elParent.size();

        LOG.debug("size : " + size);

        DoctcEvent event;
        List<DoctcEvent> eventList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Elements el = doc.select("#event_top > div > div:nth-child(" + (i + 1) + ")");

            Elements thumbnail = el.select("div.thumbnail");

            event = new DoctcEvent();

            event.setLink(thumbnail.attr("onclick"));
            event.setMainImage(el.select("div.gdListImg > img").attr("src"));
            event.setTitle(el.select("div.gdListText > div.gdListName").html());
            event.setDuration(el.select("div.gdListText > div.gdDate").html());

            LOG.debug("상세 링크 : " + event.getLink());
            LOG.debug("대표 이미지 : " + event.getMainImage());
            LOG.debug("타이틀 : " + event.getTitle());
            LOG.debug("기간 : " + event.getDuration());

            eventList.add(event);
            //LOG.debug(s);
        }

        return eventList;
    }

}
