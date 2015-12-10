package com.oskm.websocket;

public interface ArticleHandler {

    public interface Whole extends ArticleHandler {
        public void onPost(String subject, String body);
    }

    public interface Partial extends ArticleHandler {
        public void onPost(String subject, String body, boolean last);
    }

}
