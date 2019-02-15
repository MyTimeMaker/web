package com.web.controller;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import com.web.entity.AccessToken;
import com.web.util.HttpUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class MainController {
    private final  String TOKEN="life";
    private final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxdac092b85b46e8ba&secret=42475294a874ff28bcaa961eb820d3fe";
    @RequestMapping(path = {"/test"},method = {RequestMethod.GET})
    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-----开始校验签名-----");
        String signature= request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        String echostr=request.getParameter("echostr");
        String sortStr=sort(TOKEN,timestamp,nonce);
        String mySignature=shal(sortStr);
        if(!"".equals(signature)&&!"".equals(mySignature)&&signature.equals(mySignature)){
            System.out.println("------签名校验通过------");
            response.getWriter().write(echostr);
        }else {
            System.out.println("------签名校验失败------");
        }
    }
    public String sort(String token, String timestamp, String nonce) throws NullPointerException{
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }
        return sb.toString();
    }
    public String shal(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping("/getAccessToken")
    public String getAccessToken(){
        AccessToken accessToken=null;
        JSONObject jsonObject= HttpUtil.doGetstr(ACCESS_TOKEN_URL);
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setAccess_token(jsonObject.getString("access_token"));
                accessToken.setExpires_in(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
            }
        }
        return accessToken.getAccess_token();
    }
}
