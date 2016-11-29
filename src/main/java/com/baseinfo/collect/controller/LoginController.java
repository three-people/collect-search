package com.baseinfo.collect.controller;

import com.baseinfo.collect.beans.PeopleBean;
import com.baseinfo.collect.beans.UserBean;
import com.baseinfo.collect.client.PersonClient;
import com.baseinfo.collect.common.UrlPaths;
import com.baseinfo.collect.common.UserResStatus;
import com.baseinfo.collect.common.UserRole;
import com.baseinfo.collect.contract.BaseResponse;
import com.baseinfo.collect.dao.UserDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    @Qualifier("userdaoImpl")
    private UserDao userService;

    @Autowired
    @Qualifier("PersonClient")
    PersonClient service;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * 登陆接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public BaseResponse login(HttpServletRequest request, HttpServletResponse response){
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        BaseResponse res = new BaseResponse();
        res.setCode(UserResStatus.PARAM_ERROR);
        res.setMsg("参数错误");
        if(StringUtils.isBlank(uname)||StringUtils.isBlank(pwd))
            return res;
        try {
            UserBean bean = userService.selectUserByUnameAndPwd(uname,pwd);
            if(bean==null){
                res.setCode(UserResStatus.PASSWORDORNAMEERROR);
                res.setMsg("用户名或密码错误");
                return res;
            }
            request.getSession().setAttribute("loginId", String.valueOf(bean.getId()));
            request.getSession().setAttribute("uname", uname);
            request.getSession().setAttribute("userrole", bean.getRole());
            request.getSession().setAttribute("realName", bean.getRealName());
            res.setCode(UserResStatus.SUCESS);
            res.setMsg("登录成功");
            return res;
        } catch (Exception e) {
            logger.error("login error",e);
            res.setCode(UserResStatus.ERROR);
            res.setMsg("系统错误，请稍后重试");
            return res;
        }
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        try {
            response.sendRedirect("/loginpage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BaseResponse importPeople(HttpServletRequest request, HttpServletResponse response){
        PeopleBean people = new PeopleBean();
        people.setType("写字楼");
        people.setEmployee("阿斯达所多撒大所多撒多撒多撒多");
        people.setNumber(2);
        people.setExpend("");
        people.setHostid("露露露");
        people.setHostphone("1231243123");
        people.setHostname("露露啊");
        people.setLessee("阿发");
        people.setAddtime(new Date());
        service.insertAndIndex(people);
        return null;
    }



    /**
     * 管理员注册账号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("adduser")
    public BaseResponse addUser(HttpServletRequest request, HttpServletResponse response){
        BaseResponse res = new BaseResponse();
        res.setCode(UserResStatus.PARAM_ERROR);

        boolean auth = ifAuth(request);
        if(!auth){
            res.setCode(UserResStatus.ERROR);
            res.setMsg("您没有权限");
            return res;
        }
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String realName = request.getParameter("realName");
        String role = request.getParameter("role");
        String departmentName = request.getParameter("departmentName");

        if(StringUtils.isBlank(uname)){
            res.setMsg("用户名不为空");
            return res;
        }
        if(StringUtils.isBlank(pwd)){
            res.setMsg("密码不为空");
            return res;
        }
        if(pwd.length()<6){
            res.setMsg("密码必须大于6位");
            return res;
        }
        if(StringUtils.isNotBlank(realName)){
           if(realName.length()>15){
               res.setMsg("真实姓名不能超过15位");
               return res;
           }
        }
        if(StringUtils.isNotBlank(departmentName)){
            if(departmentName.length()>30){
                res.setMsg("部门名称不能超过30位");
                return res;
            }
        }

        try {
            UserBean exist = userService.selectUserByUname(uname);
            if(exist != null){
                res.setMsg("该账号已存在");
                return res;
            }

            UserBean bean = new UserBean();
            bean.setUname(uname);
            bean.setPwd(pwd);
            bean.setAddtime(new Date());
            bean.setDepartmentName(departmentName);
            bean.setRealName(realName);
            bean.setRole(Integer.parseInt(role));
            bean.setStatus(1);

            int flag= userService.insert(bean);
            if (flag>0) {
                res.setCode(UserResStatus.SUCESS);
                res.setMsg("添加成功");
            } else {
                res.setCode(UserResStatus.ERROR);
                res.setMsg("添加失败");
            }
            return res;
        } catch (Exception e) {
            res.setCode(UserResStatus.ERROR);
            res.setMsg("系统错误，请稍后重试");
            return res;
        }
    }

    /**
     * 删除相关的用户
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deluser")
    public BaseResponse delUser(HttpServletRequest request, HttpServletResponse response){
        BaseResponse res = new BaseResponse();
        res.setCode(UserResStatus.PARAM_ERROR);
        boolean auth = ifAuth(request);
        if(!auth){
            res.setCode(UserResStatus.ERROR);
            res.setMsg("您没有权限");
            return res;
        }
        String id = request.getParameter("id");
        long userId = Long.valueOf(id);
        if(userId<=0){
            res.setMsg("非法的用户ID");
            return res;
        }
        try {
            UserBean bean = userService.select(userId);
            if (bean==null){
                res.setMsg("目标用户不存在");
                return res;
            }
            int flag= userService.delete(userId);
            if (flag>0) {
                res.setCode(UserResStatus.SUCESS);
                res.setMsg("删除成功");
            }
            res.setCode(UserResStatus.ERROR);
            res.setMsg("删除失败");
            return res;
        } catch (Exception e) {
            res.setCode(UserResStatus.ERROR);
            res.setMsg("系统错误，请稍后重试");
            return res;
        }
    }

    /**
     * 管理员更新相关账户信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("admin/upuser")
    public BaseResponse adminUpdateUser(HttpServletRequest request, HttpServletResponse response){
        BaseResponse res = new BaseResponse();
        res.setCode(UserResStatus.PARAM_ERROR);
        boolean auth = ifAuth(request);
        if(!auth){
            res.setCode(UserResStatus.ERROR);
            res.setMsg("您没有权限");
            return res;
        }
        String id = request.getParameter("id");
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String realName = request.getParameter("realName");
        String role = request.getParameter("role");
        String departmentName = request.getParameter("departmentName");

        long userId = Long.valueOf(id);
        if(userId<=0){
            res.setMsg("非法的用户ID");
            return res;
        }
        if(StringUtils.isBlank(uname)){
            res.setMsg("用户名不为空");
            return res;
        }
        if(StringUtils.isBlank(pwd)){
            res.setMsg("密码不为空");
            return res;
        }
        if(pwd.length()<6){
            res.setMsg("密码必须大于6位");
            return res;
        }
        if(StringUtils.isNotBlank(realName)){
            if(realName.length()>15){
                res.setMsg("真实姓名不能超过15位");
                return res;
            }
        }
        if(StringUtils.isNotBlank(departmentName)){
            if(departmentName.length()>30){
                res.setMsg("部门名称不能超过30位");
                return res;
            }
        }
        try {
            UserBean userBean = userService.select(userId);
            if (userBean==null){
                res.setCode(UserResStatus.ERROR);
                res.setMsg("目标用户不存在");
                return res;
            }

            UserBean bean = new UserBean();
            bean.setId(userId);
            bean.setUname(uname);
            bean.setUpdatetime(new Date());
            bean.setDepartmentName(departmentName);
            bean.setRealName(realName);
            bean.setRole(Integer.parseInt(role));
            bean.setStatus(1);

            int flag= userService.update(bean);
            if (flag>0) {
                res.setCode(UserResStatus.SUCESS);
                res.setMsg("更新成功");
            }
            res.setCode(UserResStatus.ERROR);
            res.setMsg("添加失败");
            return res;
        } catch (Exception e) {
            res.setCode(UserResStatus.ERROR);
            res.setMsg("系统错误，请稍后重试");
            return res;
        }
    }

    /**
     * 账户自己更新账户信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("upuser")
    public BaseResponse updateUser(HttpServletRequest request, HttpServletResponse response){
        BaseResponse res = new BaseResponse();
        res.setCode(UserResStatus.PARAM_ERROR);
        String id = (String)request.getSession().getAttribute("loginId");
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String realName = request.getParameter("realName");
        String role = request.getParameter("role");
        String departmentName = request.getParameter("departmentName");

        if (StringUtils.isBlank(id)){
            try {
                response.sendRedirect(UrlPaths.loginUrl);
            } catch (IOException e) {
                res.setCode(UserResStatus.ERROR);
                res.setMsg("系统错误，请稍后重试");
                return res;
            }
        }
        long userId = Long.valueOf(id);
        if(userId<=0){
            res.setMsg("非法的用户ID");
            return res;
        }
        if(StringUtils.isBlank(uname)){
            res.setMsg("用户名不为空");
            return res;
        }
        if(StringUtils.isBlank(pwd)){
            res.setMsg("密码不为空");
            return res;
        }
        if(pwd.length()<6){
            res.setMsg("密码必须大于6位");
            return res;
        }
        if(StringUtils.isNotBlank(realName)){
            if(realName.length()>15){
                res.setMsg("真实姓名不能超过15位");
                return res;
            }
        }
        if(StringUtils.isNotBlank(departmentName)){
            if(departmentName.length()>30){
                res.setMsg("部门名称不能超过30位");
                return res;
            }
        }

        UserBean bean = new UserBean();
        bean.setId(userId);
        bean.setUname(uname);
        bean.setUpdatetime(new Date());
        bean.setDepartmentName(departmentName);
        bean.setRealName(realName);
        bean.setRole(Integer.parseInt(role));
        bean.setStatus(1);
        try {
            int flag= userService.update(bean);
            if (flag>0) {
                res.setCode(UserResStatus.SUCESS);
                res.setMsg("更新成功");
            }
            res.setCode(UserResStatus.ERROR);
            res.setMsg("添加失败");
            return res;
        } catch (Exception e) {
            res.setCode(UserResStatus.ERROR);
            res.setMsg("系统错误，请稍后重试");
            return res;
        }
    }


    public boolean ifAuth(HttpServletRequest request){
        String loginId = (String) request.getSession().getAttribute("loginId");
        if (StringUtils.isBlank(loginId))
            return false;
        try {
            long id = Long.valueOf(loginId);
            if (id<=0)
                return false;
            UserBean bean = userService.select(id);
            if(bean != null && bean.getRole() < UserRole.common){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }


/*    public static  void main(String args[]){
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("E:\\collect-search\\collect-search\\src\\main\\webapp\\WEB-INF\\applicationContext.xml");
        ESSearchService searchService = (ESSearchService)context.getBean("esSearchService");
        List<Map<String, Object>> list =  searchService.queryForObjectNotEq("qweqwe",0,10, IndexConstants.PERSONINDEXNAME);
        System.out.println("end...");
    }*/
}
