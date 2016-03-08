package com.oskm.parser.html;

import com.oskm.parser.PpompuEvent;
import org.junit.Test;

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

}