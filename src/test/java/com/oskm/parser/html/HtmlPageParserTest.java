package com.oskm.parser.html;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class HtmlPageParserTest {
    private DoctcEventPageParser parser = new DoctcEventPageParser();

    @Test
    public void parse() throws IOException, URISyntaxException {
        parser.parse();
    }

}