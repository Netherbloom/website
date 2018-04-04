package com.ru.user.dao.mapper;

import com.ru.core.dao.BaseMapper;
import com.ru.core.pages.PageQuery;
import com.ru.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo>{

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    public UserInfo selectUserInfo(String username);

    /**
     * 根据手机号或邮箱查询用户
     * @param phone
     * @param email
     * @return
     */
    public UserInfo selectUserByPhoneOrEmail(@Param("phone")String phone,@Param("email")String email);

    /**
     * 查询符合条件的记录总数
     * @param PageQuery
     * @return
     */
    long selectCountByCondition(PageQuery PageQuery);

    /**
     * 分页查询符合条件的记录
     * @param PageQuery
     * @return
     */
    List<UserInfo> selectByPageQuery(PageQuery PageQuery);
}