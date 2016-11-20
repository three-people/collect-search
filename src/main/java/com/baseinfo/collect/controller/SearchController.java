package com.baseinfo.collect.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baseinfo.collect.dao.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baseinfo.collect.common.IndexConstants;
import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.service.ESSearchService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by luzhaohui on 2016/11/17.
 */
@Controller
public class SearchController {

	@Autowired
    @Qualifier("esSearchService")
    private ESSearchService esSearchService;

    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "/search")
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("/searchlist");
        try {
            String searchkey = request.getParameter("searchkey");
        	String type = request.getParameter("type").trim();
        	String pageindexStr = request.getParameter("pageindex");
            model.addObject("searchkey", searchkey);
            model.addObject("type", type);
            model.addObject("pageindex", pageindexStr);
        	int pageIndex = 1;
            try{
                pageIndex = Integer.parseInt(pageindexStr);
            } catch(NumberFormatException e){}
            if(pageIndex < 1)
                pageIndex = 1;
            pageIndex--;
        	String indexType = IndexConstants.getIndexByType(type);
            List<Map<String, Object>> resultList = esSearchService.queryForObjectNotEq(searchkey, pageIndex*20, 20, indexType);
            model.addObject(type+"list", resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{type}/{id}")
    public BaseResponse delete(@PathVariable("type") String type, @PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        BaseResponse res = new BaseResponse();
        res.setMsg(type+id);
//        int result = 0;
//        switch (type){
//            case "house" :
//                HouseDao houseDao = sqlSession.getMapper(HouseDao.class);
//                result = houseDao.deleteByPrimaryKey(id);
//                break;
//            case "people" :
//                PeopleDao peopleDao = sqlSession.getMapper(PeopleDao.class);
//                result = peopleDao.deleteByPrimaryKey(id);
//                break;
//            case "place" :
//                PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);
//                result = placeDao.deleteByPrimaryKey(id);
//                break;
//            case "employer" :
//                EmployerDao employerDao = sqlSession.getMapper(EmployerDao.class);
//                result = employerDao.deleteByPrimaryKey(id);
//                break;
//            case "camera" :
//                CameraDao cameraDao = sqlSession.getMapper(CameraDao.class);
//                result = cameraDao.deleteByPrimaryKey(id);
//                break;
//        }
//        sqlSession.commit();
//        if(result > 0) {
//            res.setCode(1);
//        } else {
//            res.setCode(0);
//        }
        return res;
    }

}
