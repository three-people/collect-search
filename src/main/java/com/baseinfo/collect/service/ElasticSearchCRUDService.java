package com.baseinfo.collect.service;


import java.util.List;

public interface ElasticSearchCRUDService<T> {

    public boolean insertIndex(T info);

    public boolean insertIndexBatch(List<T> infoList);

    public boolean updateIndex(T info);

    public boolean updateIndexBatch(List<T> infoList);

    public  boolean deleteById(String id,Class<T> clazz);

}
