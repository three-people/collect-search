package com.baseinfo.collect.dao;

import com.baseinfo.collect.beans.PlaceBean;

public interface PlaceDao {
    int deleteByPrimaryKey(Long id);

    int insert(PlaceBean record);

    PlaceBean selectByPrimaryKey(Long id);

    int updateByPrimaryKey(PlaceBean record);

    int countTotal();
}