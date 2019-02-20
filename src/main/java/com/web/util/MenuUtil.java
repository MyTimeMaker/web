package com.web.util;

import com.web.entity.Menu;
import net.sf.json.JSONObject;

public class MenuUtil {
    public static boolean createMenu(Menu menu,String access_token){
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
        String parameters= JSONObject.fromObject(menu).toString();
        JSONObject jsonObject=HttpUtil.doPoststr(url,parameters);
        Boolean result=false;
        if(jsonObject!=null){
            int errorCode=jsonObject.getInt("errcode");
            String errmsg=jsonObject.getString("errmsg");
            if (errorCode==0){
                result=true;
            }
            else {
                result=false;
            }
        }
        return result;
    }
    public static Menu getMenu(){
        Menu menu=new Menu();
        return menu;
    }
    public static boolean deleteMenu(){
        return true;
    }
}
