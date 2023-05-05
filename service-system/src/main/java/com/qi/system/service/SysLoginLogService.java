package com.qi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qi.model.system.SysLoginLog;
import com.qi.model.vo.SysLoginLogQueryVo;

/**
 * @author admin
 */
public interface SysLoginLogService extends IService<SysLoginLog> {
    /**
     * 列表显示
     * @param pageParam
     * @param sysLoginLogQueryVo
     * @return
     */
    IPage<SysLoginLog> selectPage(Page<SysLoginLog> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo);
}
