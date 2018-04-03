package com.ru.ebooks.dao.mapper;

import com.ru.core.dao.BaseMapper;
import com.ru.core.pages.PageQuery;
import com.ru.ebooks.entity.Ebooks;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 书籍dao
 */
@Repository
public interface EbooksMapper extends BaseMapper<Ebooks>{

    /**
     * 查询符合条件的记录总数
     * @param pageQuery
     * @return
     */
    long selectCountByCondition(PageQuery pageQuery);

    /**
     * 分页查询符合条件的记录
     * @param pageQuery
     * @return
     */
    List<Ebooks> selectByPageQuery(PageQuery pageQuery);

}