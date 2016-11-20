package com.baseinfo.collect.client;

import com.baseinfo.collect.beans.HouseBean;
import com.baseinfo.collect.dao.HouseDao;
import com.baseinfo.collect.service.impl.HouseElasticSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 房屋相关的Client
 */
@Service("HouseClient")
public class HouseClient {
    @Autowired
    @Qualifier("HouseDao")
    private HouseDao houseDao;//People的相关的Dao层Client

    @Autowired
    @Qualifier("houseService")
    private HouseElasticSearchServiceImpl esService;

    public int insertAndIndex(HouseBean house){
        houseDao.insert(house);
        esService.insertIndex(house);
        return 0;
    }
}
