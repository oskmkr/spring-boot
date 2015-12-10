package com.oskm.parser.html;

import com.oskm.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by oskm on 2015-12-06.
 */
public class HtmlPageParser implements Parser {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlPageParser.class);


    public void parse(String url) throws URISyntaxException, IOException {
        Document doc = Jsoup.parse(new URL("http://www.doctc.com/shop/event/event_main.php"), 3000);


        Elements el = doc.select("#event_top > div > div:nth-child(1)");

        Elements thumbnail = el.select("div.thumbnail");

        LOG.debug("상세 링크 : " + thumbnail.attr("onclick"));
        LOG.debug("대표 이미지 : " + el.select("div.gdListImg > img").attr("src"));
        LOG.debug("타이틀 : " + el.select("div.gdListText > div.gdListName").html());
        LOG.debug("기간 : " + el.select("div.gdListText > div.gdDate").html());


        String s = el.html();

        LOG.debug(s);
    }

}
