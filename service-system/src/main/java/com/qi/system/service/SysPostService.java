package com.qi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qi.model.system.SysPost;
import com.qi.model.vo.SysPostQueryVo;

/**
 * @author admin
 */
public interface SysPostService extends IService<SysPost> {

    /**
     * 条件分页查询
     * @param pageParam
     * @param sysPostQueryVo
     * @return
     */
    IPage<SysPost> selectPage(Page<SysPost> pageParam, SysPostQueryVo sysPostQueryVo);
}
