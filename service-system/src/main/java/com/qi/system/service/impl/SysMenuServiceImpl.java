package com.qi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qi.common.helper.RouterHelper;
import com.qi.model.system.SysMenu;
import com.qi.model.system.SysRoleMenu;
import com.qi.model.vo.AssginMenuVo;
import com.qi.model.vo.RouterVo;
import com.qi.system.execption.GuiguException;
import com.qi.system.mapper.SysMenuMapper;
import com.qi.system.mapper.SysRoleMenuMapper;
import com.qi.system.service.SysMenuService;
import com.qi.system.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理
 *
 * @author admin
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 菜单列表（树型结构显示）
     * 第一步：根据返回数据创建实体类
     * SysMenu{
     * ....
     * List children..
     * }
     * 第二步：查询所有菜单，转换要求数据格式
     * 菜单有多层，使用递归查询
     *
     * @return
     */
    @Override
    public List<SysMenu> findNodes() {
        //获取全部菜单
        List<SysMenu> sysMenuList = this.list();

        if (CollectionUtils.isEmpty(sysMenuList)) {
            return null;
        }
        //所有菜单数据转换要求数据格式
        List<SysMenu> result = MenuHelper.buildTree(sysMenuList);
        return result;
    }

    /**
     * 删除菜单
     *
     * @param id
     */
    @Override
    public void removeMenuById(Long id) {
        //查询当前删除菜单下面是否有子菜单
        //根据id和parentid进行查询
        int count = this.count(new QueryWrapper<SysMenu>().eq("parent_id", id));
        if (count > 0) {
            throw new GuiguException(201, "请先删除子菜单");
        }
        //调用删除
        baseMapper.deleteById(id);
    }

    /**
     * 根据角色id查询菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        //获取所有status为1的权限列表
        List<SysMenu> menuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        //根据角色id查询，角色分配过的菜单
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));

        //从第二步查询列表中，获取角色分配所有菜单id
        ArrayList<Long> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu roleMenu : roleMenus) {
            roleMenuIds.add(roleMenu.getMenuId());
        }
        //遍历所有权限列表
        for (SysMenu sysMenu : menuList) {
            if (roleMenuIds.contains(sysMenu.getId())) {
                //设置权限已被分配过
                sysMenu.setSelect(true);
            } else { //否则就是没有被分配
                sysMenu.setSelect(false);
            }
        }
        //将权限列表转换为权限树
        List<SysMenu> sysMenus = MenuHelper.buildTree(menuList);
        return sysMenus;
    }

    /**
     * 分配菜单
     *
     * @param assginMenuVo
     */
    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //删除已分配的菜单
        sysRoleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", assginMenuVo.getRoleId()));
        //遍历所有已选择的权限id
        for (Long menuId : assginMenuVo.getMenuIdList()) {
            if (menuId != null) {
                //创建SysRoleMenu对象
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
                //添加新权限
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

    /**
     * 根据用户id获取用户菜单权限
     * @param userId
     * @return
     */
    @Override
    public List<RouterVo> findUserMenuList(Long userId) {
        //超级管理员admin账号id为：1
        //封装数据
        List<SysMenu> sysMenuList = null;
        if (userId.longValue()==1){
            //管理员查询所有权限数据
            sysMenuList=sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1).orderByAsc("sort_value"));

        }else{ //其他类型用户，查询这个用户权限
            sysMenuList=sysMenuMapper.findListByUserId(userId);
        }
        //构建树型数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);

        //构建路由
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenuTreeList);
        return routerVoList;
    }

    /**
     * 根据用户id查询用户按钮权限
     * @param userId
     * @return
     */
    @Override
    public List<String> findUserPermsList(Long userId) {

        //超级管理员admin账号id为：1
        List<SysMenu> sysMenuList = null;
        if (userId.longValue() == 1) {
            sysMenuList = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(userId);
        }
        //创建返回的集合
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if(sysMenu.getType() == 2){
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }

}
