package com.qi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qi.model.system.SysOperLog;
import com.qi.model.vo.SysOperLogQueryVo;

/**
 *
 * @author admin
 */
public interface AsyncOperLogService extends IService<SysOperLog> {
    /**
     * 获取操作日志列表
     * @param pageParam
     * @param sysOperLogQueryVo
     * @return
     */
    IPage<SysOperLog> selectPage(Page<SysOperLog> pageParam, SysOperLogQueryVo sysOperLogQueryVo);


}
