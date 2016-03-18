package com.oskm.home;

import com.oskm.db.file.FileStoreTemplate;
import com.oskm.parser.DoctcEvent;
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
public class DoctcEventCrawler implements EventCrawler {


    @Autowired
    @Qualifier("doctcEventPageParser")
    private Parser<DoctcEvent> parser;

    @Autowired
    private FileStoreTemplate<List<DoctcEvent>> fileStoreTemplate;

    @Override
    public void analyze() {
        List<DoctcEvent> eventList = parser.parse();

        try {
            fileStoreTemplate.save("doctcEventList", eventList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DoctcEvent> findEvent() {
        List<DoctcEvent> eventList = fileStoreTemplate.load("doctcEventList");

        return eventList;
    }


}
