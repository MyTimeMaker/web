package com.web.entity.message;

import org.springframework.stereotype.Component;

@Component
public class BaseMessage {
    /*
    消息的父类，用于生成文本，图片，视频等各种消息
     */
    public String ToUserName;
    public String FromUserName;
    public long CreateTime;

    public String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
