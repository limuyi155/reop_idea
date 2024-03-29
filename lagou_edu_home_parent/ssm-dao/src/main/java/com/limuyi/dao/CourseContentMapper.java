package com.limuyi.dao;

import com.limuyi.domain.Course;
import com.limuyi.domain.CourseLesson;
import com.limuyi.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /*
        根据课程ID查询关联的章节信息及章节信息关联的课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /*
       回显章节对应的课程信息
    */
    public Course findCourseByCourseId(Integer courseId);

    /*
        新增章节信息
     */
    public void saveSection(CourseSection courseSection);

    /*
        更新章节信息
     */
    void updateSection(CourseSection courseSection);

    /*
        修改章节状态
     */
    public void updateSectionStatus(CourseSection courseSection);

    /*
        保存课时
     */
    public void saveLesson(CourseLesson lesson);
}
