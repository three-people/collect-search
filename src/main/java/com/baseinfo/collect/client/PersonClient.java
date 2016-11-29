package com.baseinfo.collect.client;

import com.baseinfo.collect.beans.PeopleBean;
import com.baseinfo.collect.dao.PeopleDao;
import com.baseinfo.collect.service.impl.PersonElasticSearchCRUDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 对人员数据进行存储和建索引
 */
@Service("PersonClient")
public class PersonClient {

    @Autowired
    @Qualifier("PeopleBeanMapper")
    private PeopleDao dao;//People的相关的Dao层Client

    @Autowired
    @Qualifier("peopleService")
    private PersonElasticSearchCRUDServiceImpl esService;

    public boolean insertAndIndex(PeopleBean people){
        int result = dao.insert(people);
        if(result == 1){
            long id = people.getId();
            if (id>0) {
                people.setId(id);
                boolean flag = esService.insertIndex(people);
                if (!flag){
                    dao.deleteByPrimaryKey(id);
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
        PeopleBean peopleOld = dao.selectByPrimaryKey(people.getId());
        int result = dao.updateByPrimaryKey(people);
        if(result == 1){
            boolean flag = esService.updateIndex(people);
            if(!flag){
                dao.updateByPrimaryKey(peopleOld);
            }
            return flag;
        }
        return false;
    }

    public boolean delete(long id){
        if(id<=0)
            return false;
        int result = dao.deleteByPrimaryKey(id);
        if(result == 1){
            boolean flag = esService.deleteById(String.valueOf(id),PeopleBean.class);
            return flag;
        }
        return false;
    }

    public int countTotal() {
        return dao.countTotal();
    }

    public int countTotalNumber() {
        return dao.countTotalNumber();
    }

    public List<Map<String, Object>> countBySubtype() {
        return dao.countBySubtype();
    }

}
