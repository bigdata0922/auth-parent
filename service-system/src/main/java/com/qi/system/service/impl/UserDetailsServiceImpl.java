package com.qi.system.service.impl;

import com.qi.model.system.SysUser;
import com.qi.system.custom.CustomUser;
import com.qi.system.service.SysMenuService;
import com.qi.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admin
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    /**
     *根据用户名称进行查询
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if(null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if(sysUser.getStatus().intValue() == 0) {
            throw new RuntimeException("账号已停用");
        }
        //根据userid查询操作权限
        List<String> userPermsList = sysMenuService.findUserPermsList(sysUser.getId());
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (String perm : userPermsList) {
            authorities.add(new SimpleGrantedAuthority(perm.trim()));

        }
        return new CustomUser(sysUser, authorities);
    }
}
