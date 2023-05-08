package com.qi.system.utils;

import com.qi.model.system.SysDept;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据部门数据构建部门树的工具类
 * 1）获取所有部门数据list
 * 2)找到部门根节点，顶层数据，pareantid=0
 * 公司名称 id=1 parent_id=0
 * 3）拿着id=1的值，对比菜单中所有数据
 *      谁的parent_id=1
 *  parent_id=1的数据封装到id=1里面
 * 4)依次类推
 *
 * @author admin
 * @create 2023/5/8
 */
public class DeptHelper {
    /**
     *  使用递归方法建菜单
     * @param sysDeptList
     * @return
     */
    public static List<SysDept> buildTree(List<SysDept> sysDeptList){
        //创建集合封装最终数据
        ArrayList<SysDept> trees = new ArrayList<>();
        //遍历所有部门集合
        for (SysDept sysDept : sysDeptList) {
            //递归入口 parendid=0
            if (sysDept.getParentId().longValue()==0){
                trees.add(findChildren(sysDept,sysDeptList));
            }
        }
       return trees;
    }

    /**
     * 递归遍历子节点
     * 从根节点进行递归遍历，查询子节点
     * 判断id==parentid 是否相同，如果相同是子节点，进行数据封装
     * @param sysDept
     * @param treeNodes
     * @return
     */
    private static SysDept findChildren(SysDept sysDept, List<SysDept> treeNodes) {
        //数据初始化
        sysDept.setChildren(new ArrayList<>());
        for (SysDept it : treeNodes) {
            //获取当前部门的id
            Long id = sysDept.getId();
            //获取所有部门的parentid
            Long parentId = it.getParentId();
            //比对
            if (id.longValue()==parentId.longValue()){
                if (sysDept.getChildren()==null){
                    sysDept.setChildren(new ArrayList<>());
                }
                sysDept.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysDept;
    }
}
