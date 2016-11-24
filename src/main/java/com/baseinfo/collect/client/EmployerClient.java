package com.baseinfo.collect.client;

import com.baseinfo.collect.beans.CameraBean;
import com.baseinfo.collect.beans.EmployerBean;
import com.baseinfo.collect.beans.HouseBean;
import com.baseinfo.collect.dao.EmployerDao;
import com.baseinfo.collect.service.impl.EmployerElasticSearchCRUDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by 58 on 2016/11/21.
 */
@Service("EmployerClient")
public class EmployerClient {

    @Autowired
    @Qualifier("EmployerBeanMapper")
    private EmployerDao employerDao;

    @Autowired
    @Qualifier("employerService")
    private EmployerElasticSearchCRUDServiceImpl esService;

    public boolean insertAndIndex(EmployerBean employer){
        int result = employerDao.insert(employer);
        if(result == 1){
            long id = employer.getId();
            if (id>0) {
                employer.setId(id);
                boolean flag = esService.insertIndex(employer);
                if (!flag){
                    employerDao.deleteByPrimaryKey(id);
                    return flag;
                }
                return flag;
            }
        }
        return false;
    }

    public boolean updatePeople(EmployerBean employer){
        if(employer.getId()<=0)
            return false;
        EmployerBean employerOld = employerDao.selectByPrimaryKey(employer.getId());
        int result = employerDao.updateByPrimaryKey(employer);
        if(result == 1){
            boolean flag = esService.updateIndex(employer);
            if(!flag){
                employerDao.updateByPrimaryKey(employerOld);
            }
            return flag;
        }
        return false;
    }


    public boolean delete(long id){
        if(id<=0)
            return false;
        int result = employerDao.deleteByPrimaryKey(id);
        if(result == 1){
            boolean flag = esService.deleteById(String.valueOf(id),EmployerBean.class);
            return flag;
        }
        return false;
    }


}
