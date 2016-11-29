package com.baseinfo.collect.controller;


import com.baseinfo.collect.beans.HouseBean;
import com.baseinfo.collect.beans.TotalHits;
import com.baseinfo.collect.client.*;
import com.baseinfo.collect.common.IndexConstants;
import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.service.ESSearchService;
import org.apache.commons.lang.StringUtils;
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
 * 搜索Controller
 */
@Controller
public class StatisticsController {

    @Autowired
    @Qualifier("HouseClient")
    private HouseClient houseClient;
    @Autowired
    @Qualifier("PersonClient")
    private PersonClient personClient;
    @Autowired
    @Qualifier("PlaceClient")
    private PlaceClient placeClient;
    @Autowired
    @Qualifier("EmployerClient")
    private EmployerClient employerClient;
    @Autowired
    @Qualifier("CameraClient")
    private CameraClient cameraClient;

    @ResponseBody
    @RequestMapping(value = "/statistics/all")
    public ModelAndView statisticsAll(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("/statisticsall");
        int totalPeople = personClient.countTotal();
        int totalHouse = houseClient.countTotal();
        int totalPlace = placeClient.countTotal();
        int totalCamera = cameraClient.countTotal();
        int totalEmployer = employerClient.countTotal();
        int totalLocation = houseClient.countTotalLocation();
        int totalPeopleNumber = personClient.countTotalNumber();

        model.addObject("totalPeople", totalPeople);
        model.addObject("totalHouse", totalHouse);
        model.addObject("totalPlace", totalPlace);
        model.addObject("totalCamera", totalCamera);
        model.addObject("totalEmployer", totalEmployer);
        model.addObject("totalLocation", totalLocation);
        model.addObject("totalPeopleNumber", totalPeopleNumber);
        model.addObject("countPeopleBySubtype", personClient.countBySubtype());
        return model;
    }

}
