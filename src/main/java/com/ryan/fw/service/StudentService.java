package com.ryan.fw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.fw.entity.db.StudentDO;
import com.ryan.fw.entity.vo.StudentVO;

/**
 * @author Ryan
 * @date 2022-07-27
 */
public interface StudentService extends IService<StudentDO> {

    /**
     * 查询单条学生信息
     *
     * @param id
     * @return
     */
    public StudentVO one(String id);

}
