package com.oskm.websocket;

public interface ArticleHandler {

    interface Whole extends ArticleHandler {
        void onPost(String subject, String body);
    }

    interface Partial extends ArticleHandler {
        void onPost(String subject, String body, boolean last);
    }

}
