package com.qi.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.common.result.Result;
import com.qi.common.result.ResultCodeEnum;
import com.qi.model.system.SysLoginLog;
import com.qi.model.vo.SysLoginLogQueryVo;
import com.qi.system.service.SysLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录日志显示
 *
 * @author admin
 */
@Api(tags = "登录日志管理")
@RestController
@RequestMapping(value = "/admin/system/sysLoginLog")
public class SysLoginLogController {
    @Autowired
    private SysLoginLogService sysLoginLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "sysLoginLogVo", value = "查询对象", required = false)
                    SysLoginLogQueryVo sysLoginLogQueryVo
    ) {
        Page<SysLoginLog> pageParam = new Page<>();
        IPage<SysLoginLog> pageModel = sysLoginLogService.selectPage(pageParam, sysLoginLogQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysLoginLog sysLoginLog = sysLoginLogService.getById(id);
        return Result.ok(sysLoginLog);
    }
}
