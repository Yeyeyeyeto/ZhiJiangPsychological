package com.eternal.zjp.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eternal.zjp.core.pojo.entity.User;
import com.eternal.zjp.core.pojo.query.UserQuery;
import com.eternal.zjp.core.pojo.vo.LoginVO;
import com.eternal.zjp.core.pojo.vo.RegisterVO;
import com.eternal.zjp.core.pojo.vo.UserIndexVO;
import com.eternal.zjp.core.pojo.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-14
 */
public interface UserService extends IService<User> {
    void register(RegisterVO registerVO);

    UserVO login(LoginVO loginVO, String ip);

    IPage<User> listPage(Page<User> pageParam, UserQuery userInfoQuery);

    void lock(Integer id, Integer status);

    boolean checkMobile(String mobile);

    UserIndexVO getIndexUserInfo(Integer userId);
}
