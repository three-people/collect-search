package com.baseinfo.collect.controller;


import com.baseinfo.collect.beans.PersonEsIndex;
import com.baseinfo.collect.service.ElasticSearchCRUDService;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class LoginController {

    public static  void main(String args[]){
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("C:\\Users\\58\\git\\collect-search\\src\\main\\webapp\\WEB-INF\\applicationContext.xml");
        ElasticSearchCRUDService service = (ElasticSearchCRUDService)context.getBean("personService");
        PersonEsIndex index = new PersonEsIndex();
        index.setAddrass("北京市朝阳区");
        index.setPersonID("1");
        index.setSex("male");
        index.setUname("张三");
        service.insertIndex(index);

    }
}
