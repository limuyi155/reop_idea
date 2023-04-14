package com.limuyi.controller;

import com.github.pagehelper.PageInfo;
import com.limuyi.domain.ResponseResult;
import com.limuyi.domain.Role;
import com.limuyi.domain.User;
import com.limuyi.domain.UserVo;
import com.limuyi.service.UserService;
import com.limuyi.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
        用户分页&条件查询查询方法
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "用户分页&条件查询成功", pageInfo);
        return responseResult;
    }

    /*
     修改用户状态
     ENABLE能登录，DISABLE不能登录
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else{
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改用户状态成功", status);
        return responseResult;
    }

     /*
        用户登录（根据用户名查询具体的用户信息）
     */
    @RequestMapping("/login")
    public ResponseResult login(User user,HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1 != null){
            //保存用户ID及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);  //access_token可随机生成，只要不重复就好
            session.setAttribute("user_id",user1.getId());

            //将查出来的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());

            return new ResponseResult(true,200,"登录成功",map);

        }else {
            return new ResponseResult(true,400,"用户名错误或密码错误",null);
        }
    }

    /*
        注册用户
     */
    @RequestMapping("/register")
    public ResponseResult register(@RequestBody User user) throws Exception {
        userService.register(user);
        return new ResponseResult(true,200,"注册成功",null);
    }
     /*
        分配角色回显(根据用户ID查询关联的角色信息)
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"分配角色回显成功",roleList);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,200,"分配角色成功",null);
    }

    /*
        获取用户拥有的权限,进行动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //1.获取请求头中的token
        String header_token = request.getHeader("Authorization");

        //2.获取session中的token
        String session_token = (String) request.getSession().getAttribute("access_token");

        //3.判断token是否一致
        if (header_token.equals(session_token)){
            //1.获取用户id
            Integer user_id = (Integer) request.getSession().getAttribute("user_id");
            //2.调用service，进行菜单信息查询
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        }else {
            return new ResponseResult(false,400,"获取菜单信息失败",null);
        }
    }

}
