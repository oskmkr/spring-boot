package com.oskm.parser.html;

import com.oskm.parser.ClienEvent;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by oskm on 2016-03-04.
 */
public class ClienFrugalBoardPageParserTest {

    private ClienFrugalBoardPageParser uut = new ClienFrugalBoardPageParser();

    @Test
    public void parse() {
        ClienEvent actual = uut.parse("");
    }
}