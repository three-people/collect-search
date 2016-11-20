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
    @Qualifier("PeopleDao")
    private PeopleDao userMapper;//People的相关的Dao层Client

    @Autowired
    @Qualifier("peopleService")
    private PersonElasticSearchCRUDServiceImpl esService;

    public int insertAndIndex(PeopleBean people){
        userMapper.insert(people);
        esService.insertIndex(people);
        return 0;
    }

}
