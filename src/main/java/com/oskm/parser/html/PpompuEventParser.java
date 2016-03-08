package com.oskm.parser.html;

import com.oskm.parser.PpompuEvent;
import com.oskm.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oskm on 2016-03-08.
 */
@Service(value = "ppompuEventParser")
public class PpompuEventParser implements Parser<PpompuEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(PpompuEventParser.class);


    public List<PpompuEvent> parse() {

        Document doc = null;
        try {
            doc = Jsoup.parse(new URL("http://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu"), 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elParent = doc.select("#revolution_main_table > tbody > tr");

        int size = elParent.size();

        LOG.debug("size : " + size);

        PpompuEvent event;
        List<PpompuEvent> eventList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Elements el = doc.select("#revolution_main_table > tbody > tr:nth-child(" + (i + 1) + ")");

            Elements thumbnail = el.select("td:nth-child(4) > table > tbody > tr > td:nth-child(1) > a > img");

            event = new PpompuEvent();

            event.setThumbnail(thumbnail.attr("src"));
            event.setCategory(el.select("td.han4.list_vspace > nobr").html());
            event.setLink(el.select("td:nth-child(4) > table > tbody > tr > td:nth-child(2) > a").attr("href"));
            event.setTitle(el.select("td:nth-child(4) > table > tbody > tr > td:nth-child(2) > a > font").html());
            event.setWriteDate(el.select("td:nth-child(5)").attr("title"));

            if (!StringUtils.hasLength(event.getTitle()) || !StringUtils.hasLength(event.getCategory())) {
                continue;
            }

            //LOG.debug(s);
            eventList.add(event);
        }

        for (PpompuEvent each : eventList) {
            LOG.debug("카테고리 : " + each.getCategory());
            LOG.debug("썸네일 : " + each.getThumbnail());
            LOG.debug("상세 링크 : " + each.getLink());
            LOG.debug("타이틀 : " + each.getTitle());
            LOG.debug("시각 : " + each.getWriteDate());
        }

        return eventList;
    }
}
