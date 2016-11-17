package com.baseinfo.collect.dao;

import com.baseinfo.collect.beans.HouseBean;

public interface HouseBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseBean record);

    HouseBean selectByPrimaryKey(Long id);

    int updateByPrimaryKey(HouseBean record);
}