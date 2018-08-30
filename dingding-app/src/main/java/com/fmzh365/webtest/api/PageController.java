package com.fmzh365.webtest.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 2018/8/27
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
