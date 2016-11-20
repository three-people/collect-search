package com.baseinfo.collect.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import com.baseinfo.collect.common.IndexConstants;
import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.dao.UserDao;
import com.baseinfo.collect.service.ESSearchService;

/**
 * Created by luzhaohui on 2016/11/17.
 */
public class SearchController {

	@Autowired
    @Qualifier("esSearchService")
    private ESSearchService esSearchService;
	
    @RequestMapping(value = "/search")
    public BaseResponse search(HttpServletRequest request, HttpServletResponse response) {
        BaseResponse res = new BaseResponse();
        try {
        	String searchkey = request.getParameter("searchkey");
        	String type = request.getParameter("type").trim();
        	String pageindexStr = request.getParameter("pageindex");
        	int pageIndex = 1;
        	pageIndex = Integer.parseInt(pageindexStr);
        	String indexType = IndexConstants.getIndexByType(type);
        	esSearchService.queryForObjectNotEq(searchkey, pageindex, 20, indexType);
        	
        } catch (Exception e) {
            res.setCode(0);
            res.setMsg("系统错误，请稍后重试");
        }
        return res;
    }

}
