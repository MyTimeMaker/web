package com.web.service;

import com.web.entity.message.TextMessage;
import com.web.util.MessageUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Service
public class MessageService {

    public String weixinMessageHandle(HttpServletRequest request, HttpServletResponse response){
        String responseMessage=null;
        try {
            String requestMessage=null;
            Map<String,String> map= MessageUtil.parseXml(request);
            String fromUserName=map.get("FromUserName");
            String toUserName=map.get("ToUserName");
            String msgType=map.get("MsgType");
            TextMessage textMessage=new TextMessage();
            textMessage.setFromUserName(toUserName);
            textMessage.setToUserName(fromUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
            if(msgType.equals(MessageUtil.RESP_MESSAGE_TYPE_TEXT)){
                textMessage.setContent("回复成功!");
                responseMessage=MessageUtil.textMessageToXml(textMessage);
            }else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
                String event=map.get("Event");
                switch (event){
                    case "subscribe":{
                        textMessage.setContent("欢迎关注Life6的微信号!");
                        responseMessage=MessageUtil.textMessageToXml(textMessage);
                        break;
                    }
                    case "unsubscribe":{

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(responseMessage);
        return responseMessage;
    }
}
