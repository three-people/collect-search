package com.baseinfo.collect.dao;

import com.baseinfo.collect.beans.CameraBean;

public interface CameraBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CameraBean record);

    CameraBean selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CameraBean record);
}