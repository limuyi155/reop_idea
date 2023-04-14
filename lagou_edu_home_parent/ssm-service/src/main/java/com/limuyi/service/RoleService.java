package com.limuyi.service;

import com.limuyi.domain.Role;
import com.limuyi.domain.RoleMenuVo;
import com.limuyi.domain.Role_menu_relation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface RoleService {
    /*
        查询所有角色&条件进行查询
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色ID查询角色关联的菜单信息ID{1，2，3，5}
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
        为角色分配菜单信息
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);


    /*
        删除角色
     */
    public void deleteRole(Integer roleid);
}
