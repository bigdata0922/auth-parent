package com.qi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.model.system.SysOperLog;
import com.qi.model.vo.SysOperLogQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**操作日志信息
 * @author admin
 */
@Mapper
@Repository
public interface SysOperLogMapper  extends BaseMapper<SysOperLog> {
    /**
     * 分页查询操作日志信息
     * @param page
     * @param sysOperLogQueryVo
     * @return
     */
    IPage<SysOperLog> selectPage(Page<SysOperLog> page, @Param("vo") SysOperLogQueryVo sysOperLogQueryVo);

}
