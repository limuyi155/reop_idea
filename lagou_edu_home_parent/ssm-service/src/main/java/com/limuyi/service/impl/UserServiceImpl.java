package com.limuyi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.limuyi.dao.UserMapper;
import com.limuyi.domain.*;
import com.limuyi.service.UserService;
import com.limuyi.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        //封装数据
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        //调用Mapper
        userMapper.updateUserStatus(user);
    }

    /*
        用户登录（根据用户名查询具体的用户信息）
     */
    @Override
    public User login(User user) throws Exception {
        //1.调用mapper方法 user2：包含了密文密码
        User user2 = userMapper.login(user);
        if (user2 != null && Md5.verify(user.getPassword(),"limuyi",user2.getPassword())){
            return user2;
        }else {
            return null;
        }
    }

    /*
        注册
     */
    @Override
    public void register(User user) throws Exception {
        String password1 = Md5.md5(user.getPassword(), "limuyi");
        user.setPassword(password1);
        Date date = new Date();
        user.setCreate_time(date);
        user.setUpdate_time(date);
        userMapper.register(user);
    }

    /*
        根据用户ID查询关联的角色信息
     */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);
        return list;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //1.根据用户ID清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());

        //2.再从新建立关联关系
        for (Integer roleid : userVo.getRoleIdList()) {
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleid);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);

            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }

    }

    /*
        获取用户权限，进行菜单动态显示
     */
    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userid);

        //2.获取角色ID，保存到List集合中
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        //3.根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        //4.封装父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        System.out.println( "list ===============>" + roleIds);
        //5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        //6.封装数据并返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }


}
