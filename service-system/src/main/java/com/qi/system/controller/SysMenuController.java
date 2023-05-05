package com.qi.system.controller;

import com.qi.common.result.Result;
import com.qi.model.system.SysMenu;
import com.qi.model.vo.AssginMenuVo;
import com.qi.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author admin
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    /**  重点掌握
     * 获取菜单(树型结构显示)
     *
     * @return
     */
    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

    /**
     * 根据id进行菜单查询
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询菜单")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }

    /**
     * 添加菜单
     *
     * @param permission
     * @return
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu permission) {
        sysMenuService.save(permission);
        return Result.ok();
    }

    /**
     * 修改菜单
     *
     * @param permission
     * @return
     */
    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody SysMenu permission) {
        sysMenuService.updateById(permission);
        return Result.ok();
    }

    /**
     * 删除菜单
     * 删除菜单的时候出现的小问题：
     *      1）在删除之前先做判断，判断一下当前节点是否有子节点
     *          如果有子节点就不能删，需要先将子节点删除。
     *          删除子节点之后才能删除该节点
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysMenuService.removeMenuById(id);
        return Result.ok();
    }

    //TODO 给角色分配权限

    /**
     * 接口分析
     * 1）进入分配页面：获取全部菜单及按钮，选中已选复选框，进行页面展示
     * 2）保存分配权限：删除之前分配的权限和保存现在分配的权限
     *
     *
     *
     * @param roleId
     * @return
     */
    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<SysMenu> list = sysMenuService.findSysMenuByRoleId(roleId);
        return Result.ok(list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }

}
