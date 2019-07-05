package com.alice.function.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.alice.common.util.DateUtils;
import com.alice.function.entity.HallOrderTurn;
import com.alice.function.entity.HallTalkgoodsContent;
import com.alice.function.service.impl.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * 测试
 */
@Controller
@RequestMapping("/hi")
public class HelloWorldController {

    @Autowired
    private HallServiceImpl hallServiceImpl;

    @ResponseBody
    @RequestMapping(value = "/hello")
    public String hello(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "hello";
    }


    public static void main(String[] args) {
        GregorianCalendar ca = new GregorianCalendar();
        System.out.println(ca.get(GregorianCalendar.AM_PM));

        DateFormat df1 = DateFormat.getDateInstance();
        System.out.println(df1.format(new Date()));

        System.out.println(DateUtils.getDate());

        System.out.println(9 % 10);

        HallOrderTurn hallOrderTurn = new HallOrderTurn();
        hallOrderTurn.setCust_id("1");

        HallTalkgoodsContent talkgoodsContent = new HallTalkgoodsContent();
        talkgoodsContent.setCust_id("1");

        System.out.println(hallOrderTurn.getCust_id() == talkgoodsContent.getCust_id());

        Map<String, Object> customerGroupMap = Maps.newHashMap();
        List<String> str = Lists.newCopyOnWriteArrayList();
        str.add("a");
        str.add("b");
        customerGroupMap.put("1", str);
        str.add("b");
        customerGroupMap.put("1", str);


    }
}
