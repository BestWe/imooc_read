package com.imooc.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.User;
import com.imooc.reader.service.EvaluationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/management/comment")
public class MCommentController {
    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/comment.html")
    public ModelAndView showIndex(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user==null){
            //可能会有bug
            return new ModelAndView("/management/login");
        }
        return new ModelAndView("/management/comment");
    }

    @GetMapping("/list")
    @ResponseBody
    public Map list(Integer page , Integer limit){
        Map result = new HashMap();
        IPage<Evaluation> evaluations = evaluationService.queryAllComment(page,limit);
        result.put("code", "0");
        result.put("msg", "success");
        result.put("count",evaluations.getTotal());
        result.put("data",evaluations.getRecords());
        return result;
    }
    @ResponseBody
    @PostMapping("/{state}")
    public Map changeState(Long evaluationId, String reason , @PathVariable("state") String state){
        Map result = new HashMap();
        evaluationService.changeState(evaluationId,state,reason);
        result.put("code", "0");
        result.put("msg", "success");
        return result;
    }
}
