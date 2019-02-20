package com.web.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.web.entity.AccessToken;
import com.web.entity.Menu;
import com.web.service.HttpService;
import com.web.service.MenuService;
import com.web.service.MessageService;
import com.web.util.HttpThread;
import com.web.util.MenuUtil;
import com.web.util.MessageUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class MainController implements InitializingBean{
    @Autowired
    private HttpService httpService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MessageService messageService;
    private final  String TOKEN="life";
    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx0f4cfe04c082c7cd&secret=32e8db6920c4e7c483d5b11ce8d5319a";
    @RequestMapping(value = "/test",method = RequestMethod.GET)       //检验服务器url和token，token设置为life
    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("开始校验签名");
        String signature= request.getParameter("signature");
        String timestamp=request.getParameter("timestamp");
        String nonce=request.getParameter("nonce");
        String echostr=request.getParameter("echostr");
        String sortStr=sort(TOKEN,timestamp,nonce);
        String mySignature=shal(sortStr);
        if(!"".equals(signature)&&!"".equals(mySignature)&&signature.equals(mySignature)){
            System.out.println("签名校验通过");
            response.getWriter().write(echostr);
        }else {
            System.out.println("签名校验失败");
        }
    }
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String getWeChatMessage(HttpServletRequest request,HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String respXml=messageService.weixinMessageHandle(request,response);
        return respXml;
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

    @Override
    public void afterPropertiesSet() throws Exception{                 //程序启动后，自动执行的入口
        HttpThread.url=ACCESS_TOKEN_URL;
        Thread httpThread=new Thread(new HttpThread(httpService));
        httpThread.start();
        AccessToken accessToken=httpService.getAccessToken();           //从数据库中获取access_token
        Menu menu=menuService.createMenu();
        Boolean createMenuResult=false;
        createMenuResult=MenuUtil.createMenu(menu,accessToken.getAccess_token());
        if(createMenuResult){
            System.out.println("菜单创建成功!");
        }
        else {
            System.out.println("菜单创建失败!");
        }
    }
}
