package com.baseinfo.collect.dao;

import com.baseinfo.collect.beans.PeopleBean;

public interface PeopleDao {
    int deleteByPrimaryKey(Long id);

    int insert(PeopleBean record);

    PeopleBean selectByPrimaryKey(Long id);

    int updateByPrimaryKey(PeopleBean record);
}