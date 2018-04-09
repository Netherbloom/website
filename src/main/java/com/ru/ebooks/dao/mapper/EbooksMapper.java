package com.ru.ebooks.dao.mapper;

import com.ru.core.dao.BaseMapper;
import com.ru.core.pages.PageQuery;
import com.ru.ebooks.entity.Ebooks;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EbooksMapper extends BaseMapper<Ebooks>{

    /**
     * 分页查询记录数
     * @param pageQuery
     * @return
     */
    List<Ebooks> selectByPageQuery(PageQuery pageQuery);

    /**
     * 分页查询记录总数
     * @param pageQuery
     * @return
     */
    long selectCountByCondition(PageQuery pageQuery);

    /**
     * 根据实体值查询
     * @param ebooks
     * @return
     */
    List<Ebooks> selectEbookByTrem(Ebooks ebooks);

    /**
     * 批量插入
     * @param list
     */
    public void insertBatch(@Param("list")List<Ebooks> list);
}