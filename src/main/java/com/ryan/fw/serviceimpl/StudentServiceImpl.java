package com.ryan.fw.serviceimpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.fw.annotations.NeedSetFiledValue;
import com.ryan.fw.entity.db.StudentDO;
import com.ryan.fw.entity.dto.StudentDTO;
import com.ryan.fw.entity.vo.StudentPageVO;
import com.ryan.fw.entity.vo.StudentVO;
import com.ryan.fw.mapper.StudentMapper;
import com.ryan.fw.service.StudentService;
import com.ryan.fw.utils.ObjUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Ryan
 * @date 2022/7/27 11:32
 * @description 学生实现类
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentDO> implements StudentService {

    @Override
    public StudentDO init() {
        return new StudentDO();
    }

    @Override
    public StudentDO copyProperties(Object o1, Object o2) {
        BeanUtils.copyProperties(o1, o2);
        return (StudentDO) o2;
    }

    @Override
    public void checkIsNull(Object o, String msg) {
        Optional.ofNullable(o).orElseThrow(() -> new RuntimeException(msg));
    }

    @Override
    public StudentDO checkAndReturn(Long id) {
        return Optional.ofNullable(super.getById(id)).orElseThrow(() -> new RuntimeException("数据不存在"));
    }

    @Override
    public StudentVO one(String id) {
        //数据查询
        StudentDO student = this.getById(id);
        //验证是否为null
        ObjUtils.checkNull(student, "当前id:" + id + "无法确认学生信息");
        return ObjUtils.convert(student, StudentVO.class);
    }

    @Override
    public StudentPageVO getPageList(Integer currentPage, Integer pageSize) {
        Page<StudentDO> page = this.page(new Page<>(currentPage, pageSize));
        //获取分页查询数据
        List<StudentDO> records = page.getRecords();
        //验证是否为null
        ObjUtils.checkNull(records, "分页查询无数据");
        //类型转换
        List<StudentVO> studentVO = ObjUtils.toList(records, StudentVO.class);
        //返回结果
        return StudentPageVO.builder().data(studentVO).count(page.getSize()).build();
    }

    @Override
    public Boolean insertOne(StudentDTO studentDTO) {
        StudentDO studentDO = ObjUtils.convert(studentDTO, StudentDO.class);
        studentDO.setIsExist(1);
        return this.save(studentDO);
    }

    @NeedSetFiledValue
    @Override
    public StudentVO testToBoolean(Long id) {
        StudentVO vo = new StudentVO();
        BeanUtils.copyProperties(checkAndReturn(id), vo);
        return vo;
    }

    @Override
    public String singleThread() {
        List<StudentDO> list = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            list = this.list();
        }
        long endTime = System.currentTimeMillis();
        return "总耗时" + String.valueOf(endTime - startTime) + "ms";
    }

    @Override
    public String multiThread() {
        return null;
    }

}
