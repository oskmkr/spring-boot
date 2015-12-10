package com.oskm.parser.html;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class HtmlPageParserTest {
    private HtmlPageParser parser = new HtmlPageParser();

    @Test
    public void parse() throws IOException, URISyntaxException {
        parser.parse("");
    }

}