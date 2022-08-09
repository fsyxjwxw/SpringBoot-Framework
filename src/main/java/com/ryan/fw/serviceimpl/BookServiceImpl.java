package com.ryan.fw.serviceimpl;

import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ryan.fw.easyes.mapper.BookEsMapper;
import com.ryan.fw.entity.db.BookDO;
import com.ryan.fw.entity.vo.BookVO;
import com.ryan.fw.mapper.BookMapper;
import com.ryan.fw.service.BookService;
import com.ryan.fw.utils.ObjUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Ryan
 * @date 2022/8/8 17:06
 * @description
 */
@Slf4j
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookDO> implements BookService {

    @Resource
    private BookEsMapper bookEsMapper;

    @Override
    public BookVO one(Long id) {
        BookDO bookDO = this.getById(id);
        ObjUtils.checkNull(bookDO, "当前id:" + id + "无法确认图书信息");
        return ObjUtils.convert(bookDO, BookVO.class);
    }

    @Override
    public List<BookVO> getAllBooks() {
        return ObjUtils.toList(super.list(), BookVO.class);
    }

    @Override
    public Boolean syncEsAllBooks() {
        List<BookDO> list = super.list();

        if (bookEsMapper.existsIndex("fw_book")) {
            for (BookDO bookDO : list) {
                BookDO es = bookEsMapper.selectById(bookDO.getId());
                if (Objects.nonNull(es)) {
                    bookEsMapper.updateById(bookDO);
                } else {
                    bookEsMapper.insert(bookDO);
                }
            }
        } else {
            list.forEach(item -> bookEsMapper.insert(item));
        }
        return true;
    }

    @Override
    public List<BookVO> getAllEsBooks() {
        try {
            List<BookDO> list = bookEsMapper.selectList(new LambdaEsQueryWrapper<>());
            return ObjUtils.toList(list, BookVO.class);
        } catch (UndeclaredThrowableException e) {
            throw new RuntimeException("【索引无效】fw_book不存在");
        }

    }

    @Override
    public Boolean deleteAllEsBooks() {
        return bookEsMapper.deleteIndex("fw_book");
    }

    @Override
    public Map<String, Object> contrastEsAndDb() {
        Map<String, Object> map = new HashMap<>(2);
        if (bookEsMapper.existsIndex("fw_book")) {
            Integer num = 500;
            long start = System.currentTimeMillis();
            for (int i = 0; i <= num; i++) {
                this.getAllBooks();
            }
            map.put("DB", "【查询对比】DB查询耗时：" + (System.currentTimeMillis() - start));
            for (int i = 0; i <= num; i++) {
                this.getAllEsBooks();
            }
            map.put("ES", "【查询对比】ES查询耗时：" + (System.currentTimeMillis() - start));
        }
        return map;
    }

}
