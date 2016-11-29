package com.baseinfo.collect.dao;

import com.baseinfo.collect.beans.PeopleBean;

import java.util.List;
import java.util.Map;

public interface PeopleDao {
    int deleteByPrimaryKey(Long id);

    int insert(PeopleBean record);

    PeopleBean selectByPrimaryKey(Long id);

    int updateByPrimaryKey(PeopleBean record);

    int countTotal();

    int countTotalNumber();

    List<Map<String, Object>> countBySubtype();
}