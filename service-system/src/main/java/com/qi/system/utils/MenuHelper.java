package com.qi.system.utils;

import com.qi.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * 根据菜单数据构建菜单树的工具类
 * 1)获取所有菜单集合list
 * 2)找到菜单根节点，顶层数据，parentid=0  系统管理 id=2 parentid=0
 * 3)拿着id=2的值，比对菜单中所有数据
 *      谁的parentid=2
 * parentid=2的数据封装到id=2里面
 *4）以此类推
 *
 * </p>
 *
 */
public class MenuHelper {


    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建集合封装最终数据
        ArrayList<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单集合
        for (SysMenu sysMenu : sysMenuList) {
            //递归入口  parentId=0
            if (sysMenu.getParentId().longValue()==0){

                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归遍历子节点
     * 从根节点进行递归查询，查询子节点
     * 判断 id=parentId 是否相同，如果相同是子节点，进行数据封装
     *
     * @param sysMenu
     * @param treeNodes
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        //数据初始化
        sysMenu.setChildren(new ArrayList<>());

        for (SysMenu it : treeNodes) {
            //获取当前菜单的id
            Long id = sysMenu.getId();
            //获取所有菜单parentid
            Long parentId = it.getParentId();
            //比对
            if (id.longValue()==parentId.longValue()){
                if (sysMenu.getChildren()==null){
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
