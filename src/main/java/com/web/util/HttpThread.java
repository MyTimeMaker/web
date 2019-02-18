package com.web.util;

import com.web.entity.AccessToken;
import com.web.service.HttpService;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class HttpThread implements Runnable{
    public static AccessToken accessToken;
    public static String url;
    private HttpService httpService;

    public HttpThread(HttpService httpService){
        this.httpService=httpService;
    }
    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpService getHttpService() {
        return httpService;
    }

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public void run() {
        while(true){
            try {
                accessToken=this.getAccessToken(url);
                if(accessToken!=null){
                    httpService.deleteAccessToken();
                    httpService.insertAccessToken(accessToken.getAccess_token(),accessToken.getExpires_in());
                    Thread.sleep(7000*1000);
                }
                else {
                    Thread.sleep(1000*3);
                }

            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000*10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }
    public AccessToken getAccessToken(String url){
        AccessToken accessToken=null;
        JSONObject jsonObject= HttpUtil.doGetstr(url);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
            }
        }
        return accessToken;
    }
}
