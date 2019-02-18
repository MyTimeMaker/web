package com.web.util;

import com.web.entity.Menu;
import net.sf.json.JSONObject;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

public class MenuUtil {
    public static boolean createMenu(Menu menu,String access_token){
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
        String parameters= JSONObject.fromObject(menu).toString();
        JSONObject jsonObject=HttpUtil.doPoststr(url,parameters);
        if(jsonObject!=null){
            return true;
        }
        else {
            return false;
        }
    }
    public static Menu getMenu(){
        Menu menu=new Menu();
        return menu;
    }
    public static boolean deleteMenu(){
        return true;
    }
}
