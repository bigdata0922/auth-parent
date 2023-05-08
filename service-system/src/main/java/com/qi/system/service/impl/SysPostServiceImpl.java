package com.qi.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qi.model.system.SysPost;
import com.qi.model.vo.SysPostQueryVo;
import com.qi.system.mapper.SysPostMapper;
import com.qi.system.service.SysPostService;
import org.springframework.stereotype.Service;

/** 岗位管理
 * @author admin
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {
    @Override
    public IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo) {
        IPage<SysPost> pageModel = baseMapper.selectPage(pageParam, sysPostQueryVo);
        return pageModel;
    }
}
