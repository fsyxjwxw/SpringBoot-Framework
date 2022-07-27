package com.ryan.fw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ryan.fw.entity.db.StudentDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ryan
 * @date 2022-07-27
 */
@Repository
public interface StudentMapper extends BaseMapper<StudentDO> {
}
