package com.qi.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qi.model.system.SysRole;
import com.qi.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {


    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void testSelectList() {
        System.out.println("----------selectAll method test-----------");
        //UserMapper中的selectList()方法的参数为mp内置的条件封装器wrapper
        //所以不填写就是无任何条件
        List<SysRole> roleList = sysRoleService.list();
        for (SysRole sysRole : roleList) {
            System.out.println("sysRole=" + sysRole);
        }
    }

    @Test
    public void testInsert() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("李四管理");
        sysRole.setRoleCode("lisi");
        sysRole.setDescription("角色管理员");

        boolean result = sysRoleService.save(sysRole);
        System.out.println(result); //成功还是失败
    }

    /*
     * 修改
     * */
    public void update() {
        SysRole sysRole = sysRoleService.getById(11);
        sysRole.setDescription("testUpdate");
        sysRoleService.updateById(sysRole);
    }

    @Test
    public void testQueryWrapper() {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code", "role");
        List<SysRole> users = sysRoleService.list(queryWrapper);
        System.out.println(users);
    }
}
