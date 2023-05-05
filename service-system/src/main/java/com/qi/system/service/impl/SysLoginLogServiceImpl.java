package com.qi.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qi.model.system.SysLoginLog;
import com.qi.model.vo.SysLoginLogQueryVo;
import com.qi.system.mapper.SysLoginLogMapper;
import com.qi.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**分页显示列表
 * @author admin
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public IPage<SysLoginLog> selectPage(Page<SysLoginLog> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo) {
        return sysLoginLogMapper.selectPage(pageParam,sysLoginLogQueryVo);
    }
}
