package com.baseinfo.collect.service;


import java.util.List;

public interface ElasticSearchCRUDService<T> {

    public boolean InsertOrUpdate(T info);

    public boolean InsertOrUpdate(List<T> infoList);

    public boolean insertOrUpdateTaskInfo(List<T> infoList);

    public boolean insertOrUpdateNewsInfo(T info);

    public  boolean deleteById(String id);

}
