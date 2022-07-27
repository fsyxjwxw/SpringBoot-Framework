package com.ryan.fw.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.fw.entity.db.StudentDO;
import com.ryan.fw.entity.vo.StudentVO;
import com.ryan.fw.mapper.StudentMapper;
import com.ryan.fw.service.StudentService;
import com.ryan.fw.utils.ObjUtils;
import org.springframework.stereotype.Service;

/**
 * @author Ryan
 * @date 2022/7/27 11:32
 * @description 学生实现类
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentDO> implements StudentService {

    @Override
    public StudentVO one(String id) {
        StudentDO student = this.getById(id);
        ObjUtils.checkNull(student,"当前id:"+id+"无法确认学生信息");
        return ObjUtils.convert(student,StudentVO.class);
    }
}
