package com.qi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qi.model.system.SysMenu;
import com.qi.model.vo.AssginMenuVo;
import com.qi.model.vo.RouterVo;

import java.util.List;

/**
 * 菜单管理
 * @author admin
 */

public interface SysMenuService extends IService<SysMenu> {
    /**
     * 菜单树型数据
     * @return
     */
    List<SysMenu> findNodes();

    /**
     * 删除菜单
     * @param id
     */
    void removeMenuById(Long id);

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<SysMenu> findSysMenuByRoleId(Long roleId);

    /**
     * 分配权限
     * @param assginMenuVo
     */
    void doAssign(AssginMenuVo assginMenuVo);

    /**
     * 获取用户菜单权限
     * @param userId
     * @return
     */
    List<RouterVo> findUserMenuList(Long userId);

    /**
     * 获取用户按钮权限
     * @param userId
     * @return
     */
    List<String> findUserPermsList(Long userId);
}
