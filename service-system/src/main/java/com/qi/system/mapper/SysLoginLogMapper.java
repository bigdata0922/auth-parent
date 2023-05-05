package com.qi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.model.system.SysLoginLog;
import com.qi.model.vo.SysLoginLogQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 */
@Repository
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
    IPage<SysLoginLog> selectPage(Page<SysLoginLog> page, @Param("vo") SysLoginLogQueryVo sysLoginLogQueryVo);
}
