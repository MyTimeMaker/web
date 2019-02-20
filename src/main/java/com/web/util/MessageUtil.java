package com.web.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.web.entity.message.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {
    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE="image";

    /**
     * 请求消息类型：语音
     */
    public static final String REQ_MESSAGE_TYPE_VOICE="voice";

    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO="video";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION="location";

    /**
     * 请求消息类型：小视频
     */
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO="shortvideo";

    /**
     *请求消息类型：事件推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 消息返回类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE="image";

    /**
     * 消息返回类型:语音
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 消息返回类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 消息返回类型：图文
     */
    public static final  String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 消息返回类型：视频
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO="video";

    /**
     * 事件类型:订阅
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：取消订阅
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：scan(关注用户扫描带参二维码)
     */
    public static final String EVENT_TYPE_SCAN = "scan";

    /**
     * 事件类型：location(上报地理位置)
     */
    public static final String EVENT_TYPE_LOCATION = "location";

    /**
     * 事件类型：CLICK(点击菜单拉取消息)
     */
    public static final String EVENT_TYPE_CLICK ="CLICK";

    /**
     * 事件类型：VIEW(自定义菜单URl视图)
     */
    public static final String EVENT_TYPE_VIEW ="VIEW";

    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{
        Map<String,String> result=new HashMap<String, String>();
        InputStream inputStream=request.getInputStream();
        SAXReader saxReader=new SAXReader();
        Document document=saxReader.read(inputStream);
        Element root=document.getRootElement();
        List<Element> elements=root.elements();
        if(elements.size()==0){
            result.put(root.getName(),root.getText());
        }
        else {
            for(Element element:elements){
                result.put(element.getName(),element.getText());
            }
        }
        inputStream.close();
        inputStream=null;
        return result;
    }
    //回复文本text
    public static String textMessageToXml(TextMessage textMessage){
        xStream.alias("xml",textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    private static XStream xStream=new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                boolean cdata=true;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                    else {
                        writer.write(text);
                    }
                }
            };
        }
    });

}
