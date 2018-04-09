package com.ru.ebooks.service;

import com.ru.core.pages.DataguidPageResult;
import com.ru.core.pages.PageQuery;
import com.ru.core.service.BaseService;
import com.ru.ebooks.dao.mapper.EbookChapterMapper;
import com.ru.ebooks.dao.mapper.EbooksMapper;
import com.ru.ebooks.entity.Ebooks;
import com.ru.util.grab.GrabEbook;
import org.apache.log4j.Logger;
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
    @Autowired
    private EbookChapterMapper EbookChapterMapper;

    private static Logger _log=Logger.getLogger(EbooksService.class);

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

    /**
     * 同步书籍
     */
    public void saveEbooks(){
        try {
            List<Ebooks> newEbooks= GrabEbook.getEbooks();
            List<Ebooks> list=EbooksMapper.selectEbookByTrem(new Ebooks());
            if(newEbooks!=null && newEbooks.size()>0){
                if(list!=null && list.size()>0){
                    for (Ebooks ebooks:newEbooks) {
                        if(!list.contains(ebooks)){
                            EbooksMapper.insertSelective(ebooks);
                        }
                    }
                }else{
                    EbooksMapper.insertBatch(newEbooks);
                }
            }
        }catch (Exception e){

        }

    }
}
