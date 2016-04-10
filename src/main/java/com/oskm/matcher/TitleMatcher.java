package com.oskm.matcher;

/**
 * Created by oskm on 2016-04-06.
 */
public class TitleMatcher implements KeywordMatcher {

    public boolean matches(String keyword, String title) {

        if (title.contains(keyword)) {
            return true;
        }

        return false;
    }
}

