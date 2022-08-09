package com.ryan.fw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ryan.fw.entity.db.BookDO;
import com.ryan.fw.entity.vo.BookVO;

import java.util.List;
import java.util.Map;

/**
 * @author Ryan
 * @date 2022/8/8 17:04
 * @description
 */
public interface BookService extends IService<BookDO> {

    /**
     * 查询单条图书信息
     *
     * @param id 图书Id
     * @return
     */
    public BookVO one(Long id);

    /**
     * 查询全部图书信息
     *
     * @return
     */
    public List<BookVO> getAllBooks();

    /**
     * 同步全部图书信息至ES
     *
     * @return
     */
    public Boolean syncEsAllBooks();

    /**
     * 查询全部ES图书信息
     *
     * @return
     */
    public List<BookVO> getAllEsBooks();

    /**
     * 删除全部ES图书信息
     *
     * @return
     */
    public Boolean deleteAllEsBooks();

    /**
     * 对比Es与Mysql
     *
     * @return 响应信息
     */
    public Map<String, Object> contrastEsAndDb();

}
