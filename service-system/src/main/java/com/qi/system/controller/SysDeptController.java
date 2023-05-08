package com.qi.system.controller;

import com.qi.common.result.Result;
import com.qi.model.system.SysDept;
import com.qi.system.annotation.Log;
import com.qi.system.enums.BusinessType;
import com.qi.system.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理实现
 * 功能描述：查看、添加、修改、删除
 *
 * @author admin
 * @create 2023/5/8
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/admin/system/sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 获取部门（树状结构显示）
     *
     * @return
     */
    @Log(title = "部门管理",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取部门信息")
    @GetMapping("findNodes")
    public Result findNodes() {
        List<SysDept> list = sysDeptService.findNodes();
        return Result.ok(list);
    }

    /**
     * 根据id进行部门查询
     *
     * @param id
     * @return
     */
    @Log(title = "部门管理",businessType = BusinessType.SELECT)
    @ApiOperation(value = "根据id查询部门")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        SysDept sysDept = sysDeptService.getById(id);
        return Result.ok(sysDept);
    }

    /**
     * 新增部门
     *
     * @param permission
     * @return
     */
    @Log(title = "部门管理",businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增部门")
    @PostMapping("save")
    public Result save(@RequestBody SysDept permission) {
        boolean isSucceed = sysDeptService.save(permission);
        if (isSucceed) {
            return Result.ok();
        } else {
            return Result.fail().message("添加失败");
        }
    }

    /**
     * 修改部门
     * @param permission
     * @return
     */
    @Log(title = "部门管理",businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改部门")
    @PutMapping("update")
    public Result updateById(@RequestBody SysDept permission) {
        boolean isSucceed = sysDeptService.updateById(permission);
        if (isSucceed) {
            return Result.ok();
        } else {
            return Result.fail().message("修改失败");
        }
    }

    /**
     * 删除部门
     *    删除部门出现的小问题：
     *      1）在删除之前需要先做判断，判断一下当前部门是否有子节点
     *      如果有子节点就不能删除，需要现将子节点删除
     *      删除子节点之后才能删除该节点
     * @param id
     * @return
     */
    @Log(title = "部门管理",businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除部门")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean isSucceed = sysDeptService.removeDeptById(id);
        if (isSucceed){
            return Result.ok();
        }else{
            return Result.fail().message("删除失败");
        }
    }


}
