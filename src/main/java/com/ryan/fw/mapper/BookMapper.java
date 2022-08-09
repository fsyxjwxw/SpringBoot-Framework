package com.ryan.fw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ryan.fw.entity.db.BookDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ryan
 * @date 2022-08-08
 */
@Repository
public interface BookMapper extends BaseMapper<BookDO> {
}
