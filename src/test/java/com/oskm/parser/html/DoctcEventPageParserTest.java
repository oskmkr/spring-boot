package com.oskm.parser.html;

import com.oskm.parser.DoctcEvent;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oskm on 2016-03-06.
 */
public class DoctcEventPageParserTest {

    private DoctcEventPageParser uut = new DoctcEventPageParser();

    @Test
    public void parse() throws Exception {
        List<DoctcEvent> actual = uut.parse();
    }
}