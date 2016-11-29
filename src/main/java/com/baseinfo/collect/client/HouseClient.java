package com.baseinfo.collect.client;

import com.baseinfo.collect.beans.HouseBean;
import com.baseinfo.collect.beans.PeopleBean;
import com.baseinfo.collect.dao.HouseDao;
import com.baseinfo.collect.service.impl.HouseElasticSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 房屋相关的Client
 */
@Service("HouseClient")
public class HouseClient {
    @Autowired
    @Qualifier("HouseBeanMapper")
    private HouseDao houseDao;//People的相关的Dao层Client

    @Autowired
    @Qualifier("houseService")
    private HouseElasticSearchServiceImpl esService;

    public boolean insertAndIndex(HouseBean house){
        long houseId = houseDao.insert(house);
        if(houseId>0){
            boolean flag = esService.insertIndex(house);
            if(flag) {
                return true;
            }else {
                houseDao.deleteByPrimaryKey(house.getId());
                return false;
            }
        }
        return false;
    }

    public boolean updatePeople(HouseBean house){
        if(house.getId()<=0)
            return false;
        HouseBean houseOld = houseDao.selectByPrimaryKey(house.getId());
        house.setUpdatetime(new Date());
        int result = houseDao.updateByPrimaryKey(house);
        if(result == 1){
            HouseBean houseNew = houseDao.selectByPrimaryKey(house.getId());
            boolean flag = esService.updateIndex(houseNew);
            if(!flag){
                houseDao.updateByPrimaryKey(houseOld);
            }
            return flag;
        }
        return false;
    }

    public boolean delete(long id){
        if(id<=0)
            return false;
        int result = houseDao.deleteByPrimaryKey(id);
        if(result == 1){
            boolean flag = esService.deleteById(String.valueOf(id),HouseBean.class);
            return flag;
        }
        return false;
    }

    public int countTotal() {
        return houseDao.countTotal();
    }

    public int countTotalLocation() {
        return houseDao.countTotalLocation();
    }

}
