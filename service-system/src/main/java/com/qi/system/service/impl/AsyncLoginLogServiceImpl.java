package com.qi.system.service.impl;

import com.qi.model.system.SysLoginLog;
import com.qi.system.mapper.SysLoginLogMapper;
import com.qi.system.service.AsyncLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**记录登录日志信息
 * @author admin
 */
@Service
public class AsyncLoginLogServiceImpl implements AsyncLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    /**
     * 记录登录信息
     * @param username 用户名
     * @param status 状态
     * @param ipaddr ip
     * @param message 消息内容
     */
    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
        //日志状态
        sysLoginLog.setStatus(status);
        sysLoginLogMapper.insert(sysLoginLog);
    }
}
