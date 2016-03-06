package com.oskm.spring.mvc.home;

import com.oskm.db.file.FileStoreTemplate;
import com.oskm.parser.ClienEvent;
import com.oskm.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by oskmkr on 2015-12-26.
 */
@Service
public class ClienEventCrawler implements EventCrawler {


    @Autowired
    @Qualifier("clienFrugalBoardPageParser")
    private Parser<ClienEvent> parser;

    @Autowired
    private FileStoreTemplate<List<ClienEvent>> fileStoreTemplate;

    @Override
    public void analyze() {
        List<ClienEvent> eventList = parser.parse();

        try {
            fileStoreTemplate.save("clienEventList", eventList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClienEvent> findEvent() {
        List<ClienEvent> eventList = fileStoreTemplate.load("clienEventList");

        return eventList;
    }


    public void setFileStoreTemplate(FileStoreTemplate<List<ClienEvent>> fileStoreTemplate) {
        this.fileStoreTemplate = fileStoreTemplate;
    }

    public void setParser(Parser<ClienEvent> parser) {
        this.parser = parser;
    }
}
