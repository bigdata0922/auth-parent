package com.qi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qi.model.system.SysRole;
import com.qi.model.system.SysUserRole;
import com.qi.model.vo.AssginRoleVo;
import com.qi.model.vo.SysRoleQueryVo;
import com.qi.system.mapper.SysRoleMapper;
import com.qi.system.mapper.SysUserRoleMapper;
import com.qi.system.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 条件分页查询
     *
     * @param pageParam
     * @param sysRoleQueryVo
     * @return
     */
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    /**
     * 获取用户角色数据
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        //1）先获取所有角色
        List<SysRole> roleList = baseMapper.selectList(null);

        //2)根据用户id查询，已经分配角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<SysUserRole> userRolesList = sysUserRoleMapper.selectList(wrapper);
        //从集合里面获取所有角色id
        ArrayList<Long> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole : userRolesList) {
            Long roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        //封装到map中
        HashMap<String, Object> returnMap = new HashMap<>();
        //所有角色
        returnMap.put("allRoles", roleList);
        //用户里面已经分配的角色id集合
        returnMap.put("userRoleIds", userRoleIds);

        return returnMap;
    }

    /**
     * 为用户分配角色
     *
     * @param assginRoleVo
     */
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除原来分配的角色
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", assginRoleVo.getUserId());
        sysUserRoleMapper.delete(queryWrapper);

        //获取所有的角色id
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (roleId != null) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(assginRoleVo.getUserId());
                sysUserRole.setRoleId(roleId);

                //保存
                sysUserRoleMapper.insert(sysUserRole);
            }
        }


    }
}
