package com.qi.system.execption;

import com.qi.common.result.Result;
import com.qi.common.result.ResultCodeEnum;
import com.qi.system.execption.GuiguException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 全局异常处理
 * @author admin
 */
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理");
    }

    @ExceptionHandler(GuiguException.class)
    @ResponseBody
    public Result error(GuiguException e){
        e.printStackTrace();
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }

    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException {

        return Result.fail().code(ResultCodeEnum.PERMISSION.getCode()).message("没有当前功能操作权限");
        //return Result.build(null, ResultCodeEnum.PERMISSION);
    }
}
