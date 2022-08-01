package com.ryan.fw.serviceimpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.fw.entity.db.StudentDO;
import com.ryan.fw.entity.vo.StudentPageVO;
import com.ryan.fw.entity.vo.StudentVO;
import com.ryan.fw.mapper.StudentMapper;
import com.ryan.fw.service.StudentService;
import com.ryan.fw.utils.ObjUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ryan
 * @date 2022/7/27 11:32
 * @description 学生实现类
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentDO> implements StudentService {

    @Override
    public StudentVO one(String id) {
        //数据查询
        StudentDO student = this.getById(id);
        //验证是否为null
        ObjUtils.checkNull(student,"当前id:"+id+"无法确认学生信息");
        return ObjUtils.convert(student,StudentVO.class);
    }

    @Override
    public StudentPageVO getPageList(Integer currentPage, Integer pageSize) {
        Page<StudentDO> page = this.page(new Page<>(currentPage, pageSize));
        //获取分页查询数据
        List<StudentDO> records = page.getRecords();
        //验证是否为null
        ObjUtils.checkNull(records,"分页查询无数据");
        //类型转换
        List<StudentVO> studentVO = ObjUtils.toList(records, StudentVO.class);
        //返回结果
        return StudentPageVO.builder().data(studentVO).count(page.getSize()).build();
    }

}
