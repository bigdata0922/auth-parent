package com.qi.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.common.result.Result;
import com.qi.model.system.SysOperLog;
import com.qi.model.vo.SysOperLogQueryVo;
import com.qi.system.annotation.Log;
import com.qi.system.enums.BusinessType;
import com.qi.system.service.AsyncOperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author admin
 */
@Api(tags = "操作日志管理")
@RestController
@RequestMapping(value="/admin/system/sysOperLog")
public class SysOperLogController {
    @Resource
    private AsyncOperLogService sysOperLogService;

    /**
     * 条件分页查询
     * @param page
     * @param limit
     * @param sysOperLogQueryVo
     * @return
     */
    @Log(title = "操作日志管理",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "sysOperLogVo", value = "查询对象", required = false)
                    SysOperLogQueryVo sysOperLogQueryVo) {
        Page<SysOperLog> pageParam = new Page<>(page, limit);
        IPage<SysOperLog> pageModel = sysOperLogService.selectPage(pageParam, sysOperLogQueryVo);
        return Result.ok(pageModel);
    }

    @Log(title = "操作日志管理",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysOperLog sysOperLog = sysOperLogService.getById(id);
        return Result.ok(sysOperLog);
    }
}
