package com.briup.apps.app02.web.controller;

import com.briup.apps.app02.bean.Course;
import com.briup.apps.app02.bean.extend.CourseExtend;
import com.briup.apps.app02.service.ICourseService;
import com.briup.apps.app02.utils.Message;
import com.briup.apps.app02.utils.MessageUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @ApiOperation("这是一个更新或者插入操作")
    @GetMapping("/saveOrUpdate")
    public  Message saveOrUpdate(Course course){
        try {
            courseService.saveOrUpdate(course);
            return MessageUtil.success("保存成功");
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error(e.getMessage());
        }
    }

    @ApiOperation("这是一个删除操作")
    @GetMapping("/deletePrimaryKey")
    public Message deletePrimaryKey(@ApiParam(value = "主键",required = true) @RequestParam(value = "id") long id){
        try {
            courseService.deletePrimaryKey(id);
            return MessageUtil.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return MessageUtil.error(e.getMessage());
        }
    }

    @ApiOperation("这个是搜索查询")
    @GetMapping("/query")
    public Message query(Course course){
        List<Course> list=courseService.query(course);
        return MessageUtil.success("success",list);
    }

    @ApiOperation("查询所有,课程中携带任课老师的信息")
    @GetMapping("findAllWithTeacher")
    public Message findAllWithTeacher(){
        List<CourseExtend> list = courseService.findAllWithTeacher();
        return MessageUtil.success("success",list);
    }

    @ApiOperation("查询所有")
    @GetMapping("findAll")
    public Message findAll(){
        List<Course> list = courseService.findAll();
        return MessageUtil.success("success",list);
    }

    @ApiOperation("通过id查询")
    @GetMapping("findById")
    public Message findById(@ApiParam(required = true) @RequestParam(value = "id") long id){
        Course course = courseService.findById(id);
        return MessageUtil.success("success",course);
    }
}
