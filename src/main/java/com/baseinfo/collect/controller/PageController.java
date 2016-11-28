package com.baseinfo.collect.controller;


import com.baseinfo.collect.common.IndexConstants;
import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.dao.*;
import com.baseinfo.collect.service.ESSearchService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by luzhaohui on 2016/11/17.
 */
@Controller
public class PageController {

    @RequestMapping(value = "/index")
    public String searchIndex(HttpServletRequest request, HttpServletResponse response) {
        return "/search";
    }

    @RequestMapping(value = "/loginpage")
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        return "/login";
    }

    @RequestMapping(value = "/uploadpage")
    public String uploadPage(HttpServletRequest request, HttpServletResponse response) {
        return "/upload";
    }

    @RequestMapping(value = "/adduser")
    public String addUser(HttpServletRequest request, HttpServletResponse response) {
        return "/adduser";
    }
}
