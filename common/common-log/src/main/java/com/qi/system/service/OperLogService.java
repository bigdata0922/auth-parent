package com.qi.system.service;

import com.qi.model.system.SysOperLog;

/**
 * 将操作日志保存到mysql数据库中
 */
public interface OperLogService {

    /**
     * 保存操作数据
     * @param sysOperLog
     */
    public void saveSysLog(SysOperLog sysOperLog);
}
