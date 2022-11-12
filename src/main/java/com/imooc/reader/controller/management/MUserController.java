package com.imooc.reader.controller.management;

import com.imooc.reader.entity.User;
import com.imooc.reader.service.UserService;
import com.imooc.reader.service.exception.BussinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/management/user")
public class MUserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public ModelAndView login(User user, ModelAndView modelAndView, HttpSession session){
        try {
            User loginUser = userService.login(user);
            session.setAttribute("user",loginUser);
            modelAndView.addObject("code", "0");
            modelAndView.addObject("msg", "success");
            modelAndView.setViewName("/management/index");
        } catch (BussinessException ex) {
            ex.printStackTrace();
            modelAndView.addObject("code", ex.getCode());
            modelAndView.addObject("msg", ex.getMsg());
            modelAndView.setViewName("/management/login");
        }
        return modelAndView;
    }
}
