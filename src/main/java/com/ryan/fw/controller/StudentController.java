package com.ryan.fw.controller;

import com.ryan.fw.entity.co.Result;
import com.ryan.fw.entity.vo.StudentVO;
import com.ryan.fw.entity.dto.StudentDTO;
import com.ryan.fw.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

    @ApiOperation(value = "测试0-->false，1-->true的功能", notes = "返回对象的是否数据值需要转换为对应的Boolean类型，便于前端使用", response = StudentVO.class)
    @GetMapping("/test/to/boolean")
    public Result testToBoolean(@ApiParam(name = "id", value = "主键ID", required = true) @RequestParam(name = "id") @NotNull(message = "【主键ID】为必填参数") Long id) {
        return Result.success("查询成功", studentService.testToBoolean(id));
    }

    @ApiOperation("单线程查询")
    @GetMapping("/singleThread")
    public Result singleThread(){
        return Result.success("查询成功",studentService.singleThread());
    }

    @ApiOperation("多线程查询")
    @GetMapping("/multiThread")
    public Result multiThread(){
        return Result.success("查询成功",studentService.multiThread());
    }

}
