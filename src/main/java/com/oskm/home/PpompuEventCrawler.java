package com.oskm.home;

import com.oskm.crawler.EventCrawler;
import com.oskm.db.file.FileStoreTemplate;
import com.oskm.parser.PpompuEvent;
import com.oskm.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by oskmkr on 2015-12-26.
 */
@Service(value = "ppompuEventCrawler")
public class PpompuEventCrawler implements EventCrawler {


    @Autowired
    @Qualifier("ppompuEventParser")
    private Parser<PpompuEvent> parser;

    @Autowired
    private FileStoreTemplate<List<PpompuEvent>> fileStoreTemplate;

    @Override
    public void analyze() {
        List<PpompuEvent> eventList = parser.parse();

        try {
            fileStoreTemplate.save("ppompuEventList", eventList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PpompuEvent> findEvent() {
        List<PpompuEvent> eventList = fileStoreTemplate.load("ppompuEventList");

        return eventList;
    }


    public void setFileStoreTemplate(FileStoreTemplate<List<PpompuEvent>> fileStoreTemplate) {
        this.fileStoreTemplate = fileStoreTemplate;
    }

    public void setParser(Parser<PpompuEvent> parser) {
        this.parser = parser;
    }
}
