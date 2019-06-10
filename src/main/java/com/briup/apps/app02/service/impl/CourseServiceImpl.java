package com.briup.apps.app02.service.impl;

import com.briup.apps.app02.bean.Course;
import com.briup.apps.app02.bean.CourseExample;
import com.briup.apps.app02.bean.extend.CourseExtend;
import com.briup.apps.app02.dao.CourseMapper;
import com.briup.apps.app02.dao.extend.CourseExtendMapper;
import com.briup.apps.app02.service.ICourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public void saveOrUpdate(Course course) throws Exception {
        if(course.getId()==null){
            courseMapper.insert(course);
        }
        else {
            courseMapper.updateByPrimaryKey(course);
        }
    }

    @Override
    public void deletePrimaryKey(long id) throws  Exception{
           Course course=courseMapper.selectByPrimaryKey(id);
           if (course.getId()==null){
               throw new Exception("要删除的用户不存在");
           }else {
               courseMapper.deleteByPrimaryKey(id);
           }

    }

    @Override
    public List<Course> query(Course course) {
        CourseExample example=new CourseExample();
        if(course.getName()!=null){
            example.createCriteria().andNameLike("%"+course.getName()+"%");
        }
        if(course.getDescription()!=null){
            example.createCriteria().andNameLike("%"+course.getDescription()+"%");
        }
        if(course.getCredit()!=null){
            example.createCriteria().andNameLike("%"+course.getCredit()+"%");
        }
        if(course.getTeacherId()!=null){
            example.createCriteria().andNameLike("%"+course.getTeacherId()+"%");
        }
        return courseMapper.selectByExample(example);
    }

    @Resource
    private CourseExtendMapper courseExtendMapper;

    @Override
    public List<Course> findAll() {
        CourseExample example=new CourseExample();
        return courseMapper.selectByExample(example);
    }

    @Override
    public Course findById(long id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CourseExtend> findAllWithTeacher() {
        return courseExtendMapper.selectAll();
    }
}
