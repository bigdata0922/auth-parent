package com.qi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qi.model.system.SysDept;

import java.util.List;

/**
 * @author admin
 * @create 2023/5/8
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 部门树型数据
     * @return
     */
    List<SysDept> findNodes();

    /**
     * 删除部门
     * @param id
     * @return
     */
    boolean removeDeptById(Long id);
}
