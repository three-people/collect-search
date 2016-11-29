package com.baseinfo.collect.client;

import com.baseinfo.collect.beans.CameraBean;
import com.baseinfo.collect.beans.EmployerBean;
import com.baseinfo.collect.dao.CameraDao;
import com.baseinfo.collect.service.impl.CameraElasticSearchCRUDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 监控相关的Client
 */
@Service("CameraClient")
public class CameraClient {

    @Autowired
    @Qualifier("CameraBeanMapper")
    private CameraDao CameraDao;//People的相关的Dao层Client

    @Autowired
    @Qualifier("cameraService")
    private CameraElasticSearchCRUDServiceImpl esService;

    public boolean insertAndIndex(CameraBean camera){
        int result = CameraDao.insert(camera);
        if(result == 1){
            long id = camera.getId();
            if (id>0) {
                camera.setId(id);
                boolean flag = esService.insertIndex(camera);
                if (!flag){
                    CameraDao.deleteByPrimaryKey(id);
                    return flag;
                }
                return flag;
            }
        }
        return false;
    }

    public boolean updatePeople(CameraBean camera){
        if(camera.getId()<=0)
            return false;
        CameraBean cameraOld = CameraDao.selectByPrimaryKey(camera.getId());
        int result = CameraDao.updateByPrimaryKey(camera);
        if(result == 1){
            boolean flag = esService.updateIndex(camera);
            if(!flag){
                CameraDao.updateByPrimaryKey(cameraOld);
            }
            return flag;
        }
        return false;
    }

    public boolean delete(long id){
        if(id<=0)
            return false;
        int result = CameraDao.deleteByPrimaryKey(id);
        if(result == 1){
            boolean flag = esService.deleteById(String.valueOf(id),CameraBean.class);
            return flag;
        }
        return false;
    }

    public int countTotal() {
        return CameraDao.countTotal();
    }

}
