package com.ryan.fw.controller;

import com.ryan.fw.entity.co.Result;
import com.ryan.fw.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Ryan
 * @date 2022/7/27 12:02
 * @description
 */

@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/getStudentById")
    public Result getStudentById(@RequestParam("id") String id) {
        return Result.success("查询成功", studentService.one(id));
    }

}
