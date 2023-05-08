package com.qi.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qi.common.result.Result;
import com.qi.model.system.SysPost;
import com.qi.model.vo.SysPostQueryVo;
import com.qi.system.service.SysPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位管理后端接口实现
 * 功能：查看、根据id进行查看、添加、修改、删除
 *
 *
 * @author admin
 */
@Api(tags = "岗位管理")
@RestController
@RequestMapping("admin/system/sysPost")
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;

    /**
     * 1.查询所有记录
     *
     * @return
     */
    @ApiOperation(value = "获取全部角色列表")
    @GetMapping("/findAll")
    public Result findAll() {
        List<SysPost> postList = sysPostService.list();
        return Result.ok(postList);
    }

    /**
     * 条件分页查询
     *
     * @param page
     * @param limit
     * @param sysPostQueryVo
     * @return
     */
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "postQueryVo", value = "查询对象", required = false)
                    SysPostQueryVo sysPostQueryVo
    ) {
        Page<SysPost> pageParam = new Page<>(page, limit);
        IPage<SysPost> pageModel = sysPostService.selectPage(pageParam, sysPostQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * 添加岗位
     *
     * @param sysPost
     * @return
     */
    @ApiOperation("添加岗位")
    @PostMapping("save")
    public Result savePost(@RequestBody SysPost sysPost) {
        boolean isSucceed = sysPostService.save(sysPost);
        if (isSucceed) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 根据id进行查看岗位信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取岗位")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable long id) {
        SysPost post = sysPostService.getById(id);
        return Result.ok(post);
    }


    /**
     * 修改岗位信息
     *
     * @param sysPost
     * @return
     */
    @ApiOperation(value = "修改岗位信息")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysPost sysPost) {
        if (sysPost == null) {
            return Result.fail().message("数据不能为空");
        }
        sysPostService.updateById(sysPost);
        return Result.ok();
    }

    /**
     * 根据id进行删除操作
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除岗位信息")
    @DeleteMapping("/remove/{id}")
    public Result RemoveRole(@PathVariable Long id){
        boolean isSucceed = sysPostService.removeById(id);
        if (isSucceed==true){
            return Result.ok();
        }else{
            return Result.fail().message("删除失败");
        }
    }

    /**
     * 根据id列表做批量删除
     * @param idList
     * @return
     */
    @ApiOperation(value = "根据id列表进行删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        boolean isSucceed = sysPostService.removeByIds(idList);
        if (isSucceed==true){
            return Result.ok();
        }else{
            return Result.fail().message("删除失败");
        }
    }






}
