package com.ru.user.service;

import com.ru.core.pages.DataguidPageResult;
import com.ru.core.pages.PageQuery;
import com.ru.core.service.BaseService;
import com.ru.user.dao.mapper.UserInfoMapper;
import com.ru.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */
@Service
public class UserInfoService extends BaseService<UserInfo>{
    @Autowired
    private UserInfoMapper UserInfoMapper;

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    public UserInfo getUserInfo(String username){
        return  UserInfoMapper.selectUserInfo(username);
    }

    /**
     * 分页查询用户列表
     * @param PageQuery
     * @return
     */
    public DataguidPageResult<UserInfo> findPageResult(PageQuery PageQuery){
        List<UserInfo> rows = this.UserInfoMapper.selectByPageQuery(PageQuery);
        long total = this.UserInfoMapper.selectCountByCondition(PageQuery);
        return new DataguidPageResult<UserInfo>(total, null, rows);
    }

    /**
     * 根据手机号或邮箱查询用户
     * @param phone
     * @param email
     * @return
     */
    public UserInfo getUserByPhoneOrEmail(String phone,String email){
        return UserInfoMapper.selectUserByPhoneOrEmail(phone,email);
    }
}
