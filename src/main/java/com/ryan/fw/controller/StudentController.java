package com.ryan.fw.controller;

import com.ryan.fw.entity.co.Result;
import com.ryan.fw.entity.dto.StudentDTO;
import com.ryan.fw.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Ryan
 * @date 2022/7/27 12:02
 * @description
 */
@Api(tags = "【学生操作】")
@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    @ApiOperation("根据id查询学生信息")
    @GetMapping("/getStudentById")
    public Result getStudentById(@RequestParam("id") String id) {
        return Result.success("查询成功", studentService.one(id));
    }

    @ApiOperation("分页查询学生信息")
    @GetMapping("/getPageList")
    public Result getPageList(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize) {
        return Result.success("查询成功", studentService.getPageList(currentPage, pageSize));
    }

    @ApiOperation("新增学生信息")
    @PostMapping("/insertStudent")
    public Result insertStudent(@RequestBody @Valid StudentDTO studentDTO){
        return studentService.insertOne(studentDTO) ? Result.success("新增成功",1):Result.err("新增失败");
    }

}
