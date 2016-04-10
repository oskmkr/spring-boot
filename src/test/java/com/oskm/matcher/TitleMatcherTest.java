package com.oskm.matcher;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by oskm on 2016-04-10.
 */
public class TitleMatcherTest {

    private TitleMatcher uut = new TitleMatcher();

    @Test
    public void matches() {

        boolean actual = uut.matches("상품권", "[상품] 해피 머니 상품권 10% 할인");

        assertThat(actual, is(true));
    }

}