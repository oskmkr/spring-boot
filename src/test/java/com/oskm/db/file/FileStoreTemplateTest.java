package com.oskm.db.file;

import com.oskm.parser.ClienEvent;
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

    private static final String FILE_NAME = "tmp";

    @Test
    public void saveAndLoad() throws IOException {

        FileStoreTemplate<String> template = new FileStoreTemplate<String>();

        String str = "test";

        template.save(FILE_NAME, str);

        String actual = template.load(FILE_NAME);

        assertThat(actual, is(str));

    }

    @Test
    public void saveAndLoadArrayList() throws IOException {

        FileStoreTemplate<List<String>> template = new FileStoreTemplate<>();


        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");

        template.save(FILE_NAME, list);

        List<String> actual = template.load(FILE_NAME);

        System.out.printf(actual.get(0));

        assertThat(actual, Is.<List<String>>is(list));

    }

    @Test
    public void saveAndLoad_DoctcEvent() throws IOException {

        FileStoreTemplate<DoctcEvent> template = new FileStoreTemplate<DoctcEvent>();

        DoctcEvent event = new DoctcEvent();
        event.setTitle("이벤트명");

        template.save(FILE_NAME, event);

        DoctcEvent actual = template.load(FILE_NAME);

        System.out.printf(actual.getTitle());

        assertThat(actual.getTitle(), is(event.getTitle()));

    }

    @Test
    public void saveAndLoad_ClienEvent() throws IOException {

        FileStoreTemplate<ClienEvent> template = new FileStoreTemplate<ClienEvent>();

        ClienEvent event = new ClienEvent();
        event.setTitle("이벤트명");

        template.save(FILE_NAME, event);

        ClienEvent actual = template.load(FILE_NAME);

        System.out.printf(actual.getTitle());

        assertThat(actual.getTitle(), is(event.getTitle()));

    }


}