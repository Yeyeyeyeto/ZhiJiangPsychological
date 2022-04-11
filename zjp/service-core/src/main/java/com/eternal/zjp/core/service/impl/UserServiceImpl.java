package com.eternal.zjp.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.common.exception.Assert;
import com.eternal.common.result.ResponseEnum;
import com.eternal.common.utils.MD5;
import com.eternal.zjp.base.util.JwtUtils;
import com.eternal.zjp.core.mapper.AccountMapper;
import com.eternal.zjp.core.mapper.UserMapper;
import com.eternal.zjp.core.pojo.entity.Account;
import com.eternal.zjp.core.pojo.entity.User;
import com.eternal.zjp.core.pojo.query.UserQuery;
import com.eternal.zjp.core.pojo.vo.LoginVO;
import com.eternal.zjp.core.pojo.vo.RegisterVO;
import com.eternal.zjp.core.pojo.vo.UserIndexVO;
import com.eternal.zjp.core.pojo.vo.UserVO;
import com.eternal.zjp.core.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public IPage<User> listPage(Page<User> pageParam, UserQuery userQuery) {
        if (userQuery == null) {
            return baseMapper.selectPage(pageParam, null);
        }

        String mobile = userQuery.getMobile();

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq(StringUtils.isNotBlank(mobile), "mobile", mobile);

        return baseMapper.selectPage(pageParam, userQueryWrapper);
    }


    @Override
    public void lock(Integer id, Integer status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        baseMapper.updateById(user);
    }


    @Override
    public boolean checkMobile(String mobile) {
        QueryWrapper<User> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(userInfoQueryWrapper);
        return count > 0;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterVO registerVO) {
        // 判断用户是否已被注册
        QueryWrapper<User> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("mobile", registerVO.getMobile());
        Integer count = baseMapper.selectCount(userInfoQueryWrapper);
        Assert.isTrue(count == 0, ResponseEnum.MOBILE_EXIST_ERROR);

        // 插入用户信息记录 user
        User user = new User();
        user.setNickName(registerVO.getMobile());
        user.setMobile(registerVO.getMobile());
        user.setPassword(MD5.encrypt(registerVO.getPassword()));
        user.setStatus(User.STATUS_NORMAL);
        user.setHeadImg(User.USER_AVATAR);
        baseMapper.insert(user);

        // 插入用户账户记录 user_account
        Account account = new Account();
        account.setUserId(user.getId());
        accountMapper.insert(account);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserVO login(LoginVO loginVO, String ip) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();

        // 用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("mobile", mobile);
        User user = baseMapper.selectOne(userQueryWrapper);

        // 密码是否正确
        Assert.equals(MD5.encrypt(password), user.getPassword(), ResponseEnum.LOGIN_PASSWORD_ERROR);

        // 用户是否被禁用
        Assert.equals(user.getStatus(), user.STATUS_NORMAL, ResponseEnum.LOGIN_LOKED_ERROR);

        // 记录登录日志
//        UserLoginRecord userLoginRecord = new UserLoginRecord();
//        userLoginRecord.setUserId(userInfo.getId());
//        userLoginRecord.setIp(ip);
//        userLoginRecordMapper.insert(userLoginRecord);

        // 生成token
        String token = JwtUtils.createToken(user.getId(), user.getMobile());

        // 组装User
        UserVO userVO = new UserVO();
        userVO.setToken(token);
        userVO.setMobile(user.getMobile());
        userVO.setNickName(user.getNickName());
        userVO.setHeadImg(user.getHeadImg());
        userVO.setMobile(mobile);
        userVO.setType(user.getType());

        // 返回
        return userVO;
    }


    @Override
    public UserIndexVO getIndexUserInfo(Integer userId) {
        //用户信息
        User user = baseMapper.selectById(userId);

        //账户信息
        QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("id", userId);
        Account account = accountMapper.selectOne(accountQueryWrapper);

        //登录信息
//        QueryWrapper<UserLoginRecord> userLoginRecordQueryWrapper = new QueryWrapper<>();
//        userLoginRecordQueryWrapper
//                .eq("user_id", userId)
//                .orderByDesc("id")
//                .last("limit 1");
//        UserLoginRecord userLoginRecord = userLoginRecordMapper.selectOne(userLoginRecordQueryWrapper);

        //组装结果数据
        UserIndexVO userIndexVO = new UserIndexVO();
        userIndexVO.setUserId(user.getId());
        userIndexVO.setMobile(user.getMobile());
        userIndexVO.setNickName(user.getNickName());
        userIndexVO.setHeadImg(user.getHeadImg());
//        userIndexVO.setLastLoginTime(userLoginRecord.getCreateTime());

        return userIndexVO;
    }


}
