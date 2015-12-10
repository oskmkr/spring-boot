package com.oskm.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by oskmkr on 2015-06-19.
 */
public class CharResponseWrapper extends HttpServletResponseWrapper {

    private final CharArrayWriter output;

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);

        output = new CharArrayWriter();
    }


    @Override
    public String toString() {
        return output.toString();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output);
    }
}
