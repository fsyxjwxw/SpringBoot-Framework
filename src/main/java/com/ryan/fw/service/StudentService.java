package com.ryan.fw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.fw.entity.db.StudentDO;
import com.ryan.fw.entity.dto.StudentDTO;
import com.ryan.fw.entity.vo.StudentPageVO;
import com.ryan.fw.entity.vo.StudentVO;

/**
 * @author Ryan
 * @date 2022-07-27
 */
public interface StudentService extends IService<StudentDO>, BaseService {

    /**
     * 查询单条学生信息
     *
     * @param id
     * @return StudentVO
     */
    public StudentVO one(String id);

    /**
     * 分页查询
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return StudentPageVO
     */
    public StudentPageVO getPageList(Integer currentPage, Integer pageSize);

    /**
     * 插入单条数据
     *
     * @param studentDTO
     * @return Boolean
     */
    public Boolean insertOne(StudentDTO studentDTO);

    /**
     * 测试0-->false，1-->true的功能
     *
     * @param id 主键ID
     * @return StudentVO
     */
    StudentVO testToBoolean(Long id);

}
