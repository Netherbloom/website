package com.ru.ebooks.service;

import com.ru.core.pages.DataguidPageResult;
import com.ru.core.pages.PageQuery;
import com.ru.core.service.BaseService;
import com.ru.ebooks.dao.mapper.EbooksMapper;
import com.ru.ebooks.entity.Ebooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 * 书籍服务
 */
@Service
public class EbooksService extends BaseService<Ebooks>{
    @Autowired
    private EbooksMapper EbooksMapper;

    /**
     * 分页查询书籍
     * @param pageQuery
     * @return
     */
    public DataguidPageResult<Ebooks> findPageResult(PageQuery pageQuery){
        List<Ebooks> rows = this.EbooksMapper.selectByPageQuery(pageQuery);
        long total = this.EbooksMapper.selectCountByCondition(pageQuery);
        return new DataguidPageResult<Ebooks>(total, null, rows);
    }
}
