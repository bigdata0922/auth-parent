package com.qi.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qi.model.system.SysOperLog;
import com.qi.model.vo.SysOperLogQueryVo;
import com.qi.system.mapper.SysOperLogMapper;
import com.qi.system.service.AsyncOperLogService;
import com.qi.system.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @author admin
 */
@Service
public class AsyncOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements AsyncOperLogService, OperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public IPage<SysOperLog> selectPage(Page<SysOperLog> pageParam, SysOperLogQueryVo sysOperLogQueryVo) {
        return sysOperLogMapper.selectPage(pageParam,sysOperLogQueryVo);
    }

    @Async
    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
