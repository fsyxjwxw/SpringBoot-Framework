package com.ryan.fw.controller;

import com.ryan.fw.entity.co.Result;
import com.ryan.fw.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ryan
 * @date 2022/8/8 17:22
 * @description
 */
@Api(tags = "【图书操作】")
@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @ApiOperation("查询全部图书信息")
    @GetMapping("/getAllBooks")
    public Result getAllBooks() {
        return Result.success("查询成功", bookService.getAllBooks());
    }

    @ApiOperation("同步全部图书信息至ES")
    @GetMapping("/syncEsAllBooks")
    public Result syncEsAllBooks() {
        return bookService.syncEsAllBooks() ? Result.success("同步成功", 1) : Result.err("同步失败");
    }

    @ApiOperation("查询全部ES图书信息")
    @GetMapping("/getAllEsBooks")
    public Result getAllEsBooks() {
        return Result.success("查询成功", bookService.getAllEsBooks());
    }

    @ApiOperation("删除全部ES图书信息")
    @GetMapping("/deleteAllEsBooks")
    public Result deleteAllEsBooks() {
        return bookService.deleteAllEsBooks() ? Result.success("删除成功", 1) : Result.err("删除失败");
    }

    @ApiOperation("对比Es与Mysql数据查询")
    @GetMapping("/contrastEsAndDb")
    public Result contrastEsAndDb() {
        return Result.success("查询成功", bookService.contrastEsAndDb());
    }


}
