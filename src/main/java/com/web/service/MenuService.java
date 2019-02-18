package com.web.service;

import com.web.entity.FirstButton;
import com.web.entity.Menu;
import com.web.entity.SecondButton;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    public Menu createMenu(){
        Menu menu=new Menu();
        SecondButton secondButton11=new SecondButton();
        secondButton11.setName("微黄金");
        secondButton11.setType("view");
        secondButton11.setUrl("http://www.baidu.com");
        SecondButton secondButton12=new SecondButton();
        secondButton12.setName("投资理财");
        secondButton12.setType("view");
        secondButton12.setUrl("http://www.ccb.com/cn/home/indexv3.html");
        SecondButton[] first={secondButton11,secondButton12};
        FirstButton firstButton1=new FirstButton();
        firstButton1.setSub_button(first);
        firstButton1.setName("微金融");
        SecondButton secondButton21=new SecondButton();
        secondButton21.setName("生活缴费");
        secondButton21.setType("view");
        secondButton21.setUrl("http://life.ccb.com/cn/paymentv3/indexv3.html");
        SecondButton secondButton22=new SecondButton();
        secondButton22.setName("商旅出行");
        secondButton22.setType("view");
        secondButton22.setUrl("http://life.ccb.com/cn/paymentv3/bill_item/201601151634069336.html");
        SecondButton[] second={secondButton21,secondButton22};
        FirstButton firstButton2=new FirstButton();
        firstButton2.setName("悦生活");
        firstButton2.setSub_button(second);
        FirstButton[] buttons={firstButton1,firstButton2};
        menu.setButton(buttons);
        return menu;
    }
}
