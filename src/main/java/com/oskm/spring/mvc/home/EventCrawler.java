package com.oskm.spring.mvc.home;

import com.oskm.db.file.FileStoreTemplate;
import com.oskm.parser.DoctcEvent;
import com.oskm.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by oskmkr on 2015-12-26.
 */
@Service
public class EventCrawler {


    @Autowired
    @Qualifier("doctcEventPageParser")
    private Parser<DoctcEvent> parser;

    @Autowired
    private FileStoreTemplate<DoctcEvent> fileStoreTemplate;

    public void analyzeDoctcEvent() {
        DoctcEvent event = parser.parse("");

        try {
            fileStoreTemplate.save(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DoctcEvent findDoctcEvent() {
        DoctcEvent event = fileStoreTemplate.load();

        return event;
    }


}
