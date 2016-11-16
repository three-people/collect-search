package com.baseinfo.collect.service;


import java.util.List;

public interface ElasticSearchCRUDService<T> {

    public boolean update(List<T> taskInfoList);

    public boolean insertOrUpdateTaskInfo(List<T> taskInfoList);

    public boolean insertOrUpdateNewsInfo(T newsInfo);

    public  boolean deleteById(String id);

}
