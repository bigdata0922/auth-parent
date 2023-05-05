package com.qi.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qi.model.system.SysRole;
import com.qi.system.mapper.SysRoleMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void testSelectList() {
        System.out.println("------------selectAll method test----------");
        //userMapper中的selectList()方法的参数为MP内置的条件封装器wrapper
        //所以不填写就是无任何条件
        List<SysRole> sysRoleList = sysRoleMapper.selectList(null);
        for (SysRole sysRole : sysRoleList) {

            System.out.println("sysRole=" + sysRole);
        }
    }

    @Test
    public void testInsert() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("张三管理");
        sysRole.setRoleCode("zhangsan");
        sysRole.setDescription("系统管理员");
        int result = sysRoleMapper.insert(sysRole);
        System.out.println(result); //影响的行数
        System.out.println(sysRole.getId()); //id自动回填
    }

    @Test
    public void testUpdateById() {
        //根据id查询
        SysRole sysRole = sysRoleMapper.selectById(10);


        //设置修改值
        sysRole.setDescription("李四在管理");


        //调用修改方法
        sysRoleMapper.updateById(sysRole);

    }

    @Test
    public void testDeleteById() {
        int result = sysRoleMapper.deleteById(10);
        System.out.println(result);
    }

    @Test
    public void testDeleteBatchIds() {
        int result = sysRoleMapper.deleteBatchIds(Arrays.asList(9, 10));
        System.out.println(result);
    }

    /*
     * 条件查询
     * */
    @Test
    public void testSelectBy() {
        //创建条件构造器对象
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        //设置条件
        wrapper.eq("role_code", "zhangsan");
        wrapper.like("role_code", "zhangsan");

        //调用方法查询
        List<SysRole> sysRoles = sysRoleMapper.selectList(new LambdaQueryWrapper<>(SysRole.class).eq(SysRole::getRoleName,"张三管理"));
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }
    }






}
