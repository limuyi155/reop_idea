package com.limuyi.dao;

import com.limuyi.domain.Course;
import com.limuyi.domain.CourseVO;
import com.limuyi.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    /*
        多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
        新增课程信息
     */
    public void saveCourse(Course course);

    /*
        新增讲师信息
     */
    public void saveTeacher(Teacher teacher);

    /*
        回显课程信息(根据ID查询对应的课程和信息及其关联的讲师信息)
     */
    public CourseVO findCourseById(Integer id);

    /*
        更新课程信息
     */
    public void updateCourse(Course course);

    /*
        更新讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /*
        课程状态管理
     */
    public void updateCourseStatus(Course course);
}
