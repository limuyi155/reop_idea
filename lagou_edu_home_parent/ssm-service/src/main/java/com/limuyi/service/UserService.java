package com.limuyi.service;

import com.github.pagehelper.PageInfo;
import com.limuyi.domain.*;

import java.util.List;

public interface UserService {
    /*
        用户分页&多条件组合查询
     */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
      修改用户状态
     */
    public void updateUserStatus(int id, String status);

    /*
        用户登录（根据用户名查询具体的用户信息）
     */
    public User login(User user) throws Exception;

    /*
        注册(用户插入数据库)
     */
    void register(User user) throws Exception;

    /*
        根据用户ID查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
        用户关联角色
     */
    public void userContextRole(UserVo userVo);


    /*
        获取用户权限，进行菜单动态显示
     */
    public ResponseResult getUserPermissions(Integer userid);


}
