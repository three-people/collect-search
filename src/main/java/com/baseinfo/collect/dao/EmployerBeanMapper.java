package com.baseinfo.collect.dao;

import com.baseinfo.collect.beans.EmployerBean;

public interface EmployerBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EmployerBean record);

    EmployerBean selectByPrimaryKey(Long id);

    int updateByPrimaryKey(EmployerBean record);
}