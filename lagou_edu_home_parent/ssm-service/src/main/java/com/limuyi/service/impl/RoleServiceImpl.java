package com.limuyi.service.impl;

import com.limuyi.dao.RoleMapper;
import com.limuyi.domain.Role;
import com.limuyi.domain.RoleMenuVo;
import com.limuyi.domain.Role_menu_relation;
import com.limuyi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    /*
        根据角色ID查询角色关联的菜单信息ID{1，2，3，5}
     */
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleId);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo){

        //1.清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2.为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(mid);

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            System.out.println(role_menu_relation);

            roleMapper.roleContextMenu(role_menu_relation);
    }


    }

    @Override
    public void deleteRole(Integer roleid) {
        //调用根据roleid清空质检部关联的关系
        roleMapper.deleteRoleContextMenu(roleid);
        roleMapper.deleteRole(roleid);
    }
}
