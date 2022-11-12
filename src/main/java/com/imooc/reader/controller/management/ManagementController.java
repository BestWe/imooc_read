package com.imooc.reader.controller.management;

import com.imooc.reader.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 后台管理系统控制器
 */
@Controller
@RequestMapping("/management")
public class ManagementController {
    @GetMapping("/index.html")
    public ModelAndView showIndex(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user==null){
            return new ModelAndView("/management/login");
        }
        return new ModelAndView("/management/index");
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView("/management/login");
    }
}
