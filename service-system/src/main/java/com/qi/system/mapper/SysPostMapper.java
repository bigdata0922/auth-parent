package com.qi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.model.system.SysPost;
import com.qi.model.system.SysRole;
import com.qi.model.vo.SysPostQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @create 2023/5/8
 */
@Repository
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {

    /**
     * 条件分页插叙
     * @param pageParam
     * @param sysPostQueryVo
     * @return
     */
    IPage<SysPost> selectPage(@Param("pageParam") Page<SysPost> pageParam, @Param("vo")SysPostQueryVo sysPostQueryVo);
}
