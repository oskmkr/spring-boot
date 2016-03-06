package com.oskm.parser.html;

import com.oskm.parser.ClienEvent;
import com.oskm.parser.DoctcEvent;
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
 * Created by oskm on 2015-12-06.
 */
@Service
public class ClienFrugalBoardPageParser implements Parser<ClienEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ClienFrugalBoardPageParser.class);


    public List<ClienEvent> parse() {

        Document doc = null;
        try {
            doc = Jsoup.parse(new URL("http://www.clien.net/cs2/bbs/board.php?bo_table=jirum"), 3000);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Elements elParent = doc.select("#content > div.board_main > table > tbody > tr");

        int size = elParent.size();

        LOG.debug("size : " + size);

        ClienEvent event;
        List<ClienEvent> eventList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Elements el = doc.select("#content > div.board_main > table > tbody > tr:nth-child(" + (i + 1) + ")");

            Elements thumbnail = el.select("div.thumbnail");

            event = new ClienEvent();

            event.setCategory(el.select("td.post_category > a").html());
            event.setLink(el.select("td.post_subject > a").attr("href"));
            event.setTitle(el.select("td.post_subject > a").html());
            event.setWriteDate(el.select("td:nth-child(5) > span").attr("title"));

            if(!StringUtils.hasLength(event.getTitle()) || !StringUtils.hasLength(event.getCategory())) {
                continue;
            }

            //LOG.debug(s);
            eventList.add(event);
        }

        for(ClienEvent each : eventList) {
            LOG.debug("카테고리 : " + each.getCategory());
            LOG.debug("상세 링크 : " + each.getLink());
            LOG.debug("타이틀 : " + each.getTitle());
            LOG.debug("시각 : " + each.getWriteDate());
        }

        return eventList;
    }

}
