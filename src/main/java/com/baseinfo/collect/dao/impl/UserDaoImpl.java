package com.baseinfo.collect.dao.impl;

import com.baseinfo.collect.beans.UserBean;
import com.baseinfo.collect.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User的实现类
 */
@Repository("userdaoImpl")
public class UserDaoImpl implements UserDao{

    @Autowired
    @Qualifier("sqlSessionFactory")
    private SqlSessionFactory factory;

    @Override
    public int insert(UserBean user) throws Exception {
        SqlSession session = factory.openSession(true);
        int flag = 0;
        try {
            flag = session.insert("userOpreation.insert",user);
        }finally {
            session.commit();
            session.close();
        }
        return flag;
    }

    @Override
    public int update(UserBean user) throws Exception {
        SqlSession session = factory.openSession(true);
        int flag = 0;
        try {
            flag = session.update("userOpreation.updateById",user);
        }finally {
            session.commit();
            session.close();
        }
        return flag;
    }

    @Override
    public UserBean select(long id) throws Exception {
        Map idMap = new HashMap();
        idMap.put("id",id);
        SqlSession session = factory.openSession(true);
        List<UserBean> beanList;
        try {
            beanList = session.selectList("userOpreation.selectUserById",idMap);
        }finally {
            session.commit();
            session.close();
        }
        return beanList.get(0);
    }

    @Override
    public int delete(long id) throws Exception {
        Map idMap = new HashMap();
        idMap.put("id",id);
        SqlSession session = factory.openSession(true);
        int flag = 0;
        try {
            flag = session.delete("userOpreation.deleteUserById",idMap);
        }finally {
            session.commit();
            session.close();
        }
        return flag;
    }

    @Override
    public UserBean selectUserByUnameAndPwd(String uname, String id) throws Exception {
        Map idMap = new HashMap();
        idMap.put("id",id);
        idMap.put("uname",uname);
        SqlSession session = factory.openSession(true);
        List<UserBean> beanList;
        try {
            beanList = session.selectList("userOpreation.selectUserByUnameAndPwd",idMap);
        }finally {
            session.commit();
            session.close();
        }
        return beanList.get(0);
    }
}
