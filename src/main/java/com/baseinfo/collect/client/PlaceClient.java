package com.baseinfo.collect.client;

import com.baseinfo.collect.beans.PlaceBean;
import com.baseinfo.collect.dao.PlaceDao;
import com.baseinfo.collect.service.impl.PlaceElasticSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by 58 on 2016/11/21.
 */
@Service("PlaceClient")
public class PlaceClient {

    @Autowired
    @Qualifier("PlaceBeanMapper")
    private PlaceDao placeDao;

    @Autowired
    @Qualifier("PlaceService")
    private PlaceElasticSearchServiceImpl esService;

    public boolean insertAndIndex(PlaceBean place){
        int result = placeDao.insert(place);
        if(result == 1){
            long id = place.getId();
            if (id>0) {
                place.setId(id);
                boolean flag = esService.insertIndex(place);
                if (!flag){
                    placeDao.deleteByPrimaryKey(id);
                    return flag;
                }
                return flag;
            }
        }
        return false;
    }

    public boolean updatePeople(PlaceBean place){
        if(place.getId()<=0)
            return false;
        PlaceBean placeOld = placeDao.selectByPrimaryKey(place.getId());
        int result = placeDao.updateByPrimaryKey(place);
        if(result == 1){
            boolean flag = esService.updateIndex(place);
            if(!flag){
                placeDao.updateByPrimaryKey(placeOld);
            }
            return flag;
        }
        return false;
    }

    public boolean deletePeople(PlaceBean place){
        if(place.getId()<=0)
            return false;
        int result = placeDao.deleteByPrimaryKey(place.getId());
        if(result == 1){
            boolean flag = esService.deleteById(String.valueOf(place.getId()),PlaceBean.class);
            return flag;
        }
        return false;
    }
}
