package com.qi.system.controller;

import com.qi.common.result.Result;
import com.qi.common.result.ResultCodeEnum;
import com.qi.common.utils.JwtHelper;
import com.qi.common.utils.MD5;
import com.qi.model.system.SysUser;
import com.qi.model.vo.LoginVo;
import com.qi.system.annotation.Log;
import com.qi.system.enums.BusinessType;
import com.qi.system.execption.GuiguException;
import com.qi.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 后台登录登出
 * </p>
 *
 * @author admin
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    /**
     * 登录
     * @return
     */
    @ApiOperation(value ="登录" )
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        //根据用户名称进行查询
        SysUser sysUser = sysUserService.getByUsername(loginVo.getUsername());
        //空值校验
        if(null == sysUser) {
            throw new GuiguException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        //判断密码是否一致
        if(!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())) {
            throw new GuiguException(ResultCodeEnum.PASSWORD_ERROR);
        }
        //判断用户是否可用
        if(sysUser.getStatus().intValue() == 0) {
            throw new GuiguException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //根据userid和username生成token字符串，通过map返回
        Map<String, Object> map = new HashMap<>(8);
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUsername()));
        return Result.ok(map);
    }
    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation(value ="获取用户信息" )
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        //获取请求头token字符串
        //从token字符串获取用户名称（id）
        String username = JwtHelper.getUsername(request.getHeader("token"));

        //根据用户名称获取用户信息（基本信息和菜单权限和按钮权限数据）
        Map<String, Object> map = sysUserService.getUserInfo(username);

        return Result.ok(map);
    }
    /**
     * 退出
     * @return
     */
    @ApiOperation(value ="退出登录" )
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }


}
