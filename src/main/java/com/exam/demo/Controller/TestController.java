package com.exam.demo.Controller;

import com.exam.demo.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping(value={"/index","/","/hello"})
    public String index(Model model){
        model.addAttribute("title", "测试");
        model.addAttribute("atext", "这个冬天不太Cool");
        return "index";
    }
}
