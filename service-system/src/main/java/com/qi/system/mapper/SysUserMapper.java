package com.qi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.model.system.SysUser;
import com.qi.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户管理
 * @author admin
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 条件分页查询
     * @param page
     * @param userQueryVo
     * @return
     */
    IPage<SysUser> selectPage(Page<SysUser> page, @Param("vo")SysUserQueryVo userQueryVo);
}
