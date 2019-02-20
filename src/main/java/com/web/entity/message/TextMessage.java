package com.web.entity.message;

import org.springframework.stereotype.Component;

public class TextMessage extends BaseMessage{
    public String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
