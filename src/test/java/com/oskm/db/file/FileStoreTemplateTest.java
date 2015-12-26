package com.oskm.db.file;

import com.oskm.parser.DoctcEvent;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by oskmkr on 2015-12-26.
 */
public class FileStoreTemplateTest {

    @Test
    public void saveAndLoad() throws IOException {

        FileStoreTemplate<String> template = new FileStoreTemplate<String>();

        String str = "test";

        template.save(str);

        String actual = template.load();

        assertThat(actual, is(str));

    }

    @Test
    public void saveAndLoadArrayList() throws IOException {

        FileStoreTemplate<ArrayList> template = new FileStoreTemplate<ArrayList>();


        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");

        template.save(list);

        List<String> actual = template.load();

        System.out.printf(actual.get(0));

        assertThat(actual, Is.<List<String>>is(list));

    }

    @Test
    public void saveAndLoad_DoctcEvent() throws IOException {

        FileStoreTemplate<DoctcEvent> template = new FileStoreTemplate<DoctcEvent>();

        DoctcEvent event = new DoctcEvent();
        event.setTitle("이벤트명");

        template.save(event);

        DoctcEvent actual = template.load();

        System.out.printf(actual.getTitle());

        assertThat(actual.getTitle(), is(event.getTitle()));

    }


}