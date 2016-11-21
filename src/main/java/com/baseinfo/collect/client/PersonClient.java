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
        int result = userMapper.insert(people);
        if(result == 1){
            long id = people.getId();
            if (id>0) {
                people.setId(id);
                boolean flag = esService.insertIndex(people);
                if (!flag){
                    userMapper.deleteByPrimaryKey(id);
                    return flag;
                }
                return flag;
            }
        }
        return false;
    }


    public boolean updatePeople(PeopleBean people){
        if(people.getId()<=0)
            return false;
        PeopleBean peopleOld = userMapper.selectByPrimaryKey(people.getId());
        int result = userMapper.updateByPrimaryKey(people);
        if(result == 1){
            boolean flag = esService.updateIndex(people);
            if(!flag){
                userMapper.updateByPrimaryKey(peopleOld);
            }
            return flag;
        }
        return false;
    }

    public boolean deletePeople(PeopleBean people){
        if(people.getId()<=0)
            return false;
        int result = userMapper.deleteByPrimaryKey(people.getId());
        if(result == 1){
            boolean flag = esService.deleteById(String.valueOf(people.getId()),PeopleBean.class);
            return flag;
        }
        return false;
    }



}
