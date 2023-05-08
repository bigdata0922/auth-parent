package com.qi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qi.model.system.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @create 2023/5/8
 */
@Repository
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
    /**
     * 根据用户id查询用户部门
     * @param userId
     * @return
     */
    List<SysDept> findListByUserId(Long userId);
}
