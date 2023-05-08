package com.qi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qi.model.system.SysDept;
import com.qi.system.execption.GuiguException;
import com.qi.system.mapper.SysDeptMapper;
import com.qi.system.service.SysDeptService;
import com.qi.system.utils.DeptHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 部门管理
 *
 * @author admin
 * @create 2023/5/8
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    /**
     * 部门列表（树状结构显示）
     * 第一步：根据返回数据创建实体类
     * SysDept{
     * .....
     * List children
     * }
     * 第二步：查询所有部门，转换要求数据格式
     * 部门有多层，使用递归查询
     *
     * @return
     */
    @Override
    public List<SysDept> findNodes() {
        //1.获取全部部门信息
        List<SysDept> sysDeptList = this.list();
        if (CollectionUtils.isEmpty(sysDeptList)) {
            return null;
        }
        //所有部门数据转换要求数据格式
        List<SysDept> result = DeptHelper.buildTree(sysDeptList);
        return result;
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public boolean removeDeptById(Long id) {
        //查询当前部门下面是否有子部门
        //根据id和parendid进行查询
        int count = this.count(new QueryWrapper<SysDept>().eq("parent_id", id));
        if (count>0){
            throw  new GuiguException(201,"请先删除子部门");
        }
        //调用删除
        baseMapper.deleteById(id);


        return false;
    }
}
