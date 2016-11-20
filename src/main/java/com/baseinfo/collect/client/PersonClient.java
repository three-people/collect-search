package com.baseinfo.collect.client;

import com.baseinfo.collect.beans.PeopleBean;
import com.baseinfo.collect.dao.PeopleDao;
import com.baseinfo.collect.service.impl.PersonElasticSearchCRUDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 对人员数据进行存储和建索引
 */
@Service("PersonClient")
public class PersonClient {

    @Autowired
    @Qualifier("PeopleBeanMapper")
    private PeopleDao userMapper;//People的相关的Dao层Client

    @Autowired
    @Qualifier("peopleService")
    private PersonElasticSearchCRUDServiceImpl esService;

    public boolean insertAndIndex(PeopleBean people){
        long id = userMapper.insert(people);
        if (id>0) {
            people.setId(id);
            boolean flag = esService.insertIndex(people);
            return flag;
        }else {
            return false;
        }
    }

}
