package com.limuyi.dao;

import com.limuyi.domain.*;

import java.util.List;

public interface UserMapper {
    /*
        用户分页&多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /*
      修改用户状态
     */
    public void updateUserStatus(User user);

    /*
        用户登录（根据用户名查询具体的用户信息）
     */
    public User login(User user);

    /*
        注册
     */
    void register(User user);

    /*
        根据用户ID查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
        根据用户ID删除中间表的关联关系
     */
    public void deleteUserContextRole(Integer userId);

    /*
        分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /**
     * 获取用户拥有的菜单权限（包括资源信息）
     */

    /*
        1.根据用户ID查询关联的角色信息(上面已经有了)
     */

    /*
        2.根据角色ID，查询角色所拥有的顶级菜单信息
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
        3.根据PID，查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
        4.获得用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
