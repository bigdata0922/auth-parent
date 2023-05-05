package com.qi.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qi.model.system.SysUser;
import com.qi.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author qi
 * @since 2023-05-04
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 条件分页查询
     * @param pageParam
     * @param userQueryVo
     * @return
     */
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo);

    /**
     * 根据用户名称进行查询
     * @param username
     * @return
     */
    SysUser getByUsername(String username);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);
}
