package com.qi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qi.model.system.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单管理
 * @author admin
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户id查询用户菜单
     * @param userId
     * @return
     */
    List<SysMenu> findListByUserId(Long userId);
}
