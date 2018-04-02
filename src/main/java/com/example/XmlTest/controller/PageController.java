package com.example.XmlTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/4/2 0002
 */
@RestController
public class PageController {

    @RequestMapping("/jspPage")
    public ModelAndView jspPage(){
        Map<String, Object> data = new HashMap<>();
        data.put("time", new Date());
        ModelAndView view = new ModelAndView("index");
        view.addAllObjects(data);
        return view;
    }


    @RequestMapping("/time")
    public Map timeFormat(){
        Map<String, Object> data = new HashMap<>();
        data.put("time", new Date());
        return data;
    }
}
