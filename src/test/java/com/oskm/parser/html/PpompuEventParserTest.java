package com.oskm.parser.html;

import com.oskm.parser.PpompuEvent;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by oskm on 2016-03-08.
 */
public class PpompuEventParserTest {

    private PpompuEventParser uut = new PpompuEventParser();

    @Test
    public void parse() throws Exception {
        List<PpompuEvent> actual = uut.parse();
    }

    @Test
    public void test() {
        String d = "2016-04-06 20:08:09";

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sf.parse(d);

            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sf2 = new SimpleDateFormat("yy/MM/dd");

        System.out.println("# : " + sf2.format(date));

    }

}