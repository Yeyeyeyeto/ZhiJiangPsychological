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
import com.eternal.zjp.core.mapper.ConsultantMapper;
import com.eternal.zjp.core.pojo.entity.Account;
import com.eternal.zjp.core.pojo.entity.Consultant;
import com.eternal.zjp.core.pojo.query.ConsultantQuery;
import com.eternal.zjp.core.pojo.vo.ConsultantIndexVO;
import com.eternal.zjp.core.pojo.vo.LoginVO;
import com.eternal.zjp.core.pojo.vo.RegisterVO;
import com.eternal.zjp.core.pojo.vo.UserVO;
import com.eternal.zjp.core.service.ConsultantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 咨询师表 服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-14
 */
@Service
public class ConsultantServiceImpl extends ServiceImpl<ConsultantMapper, Consultant> implements ConsultantService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public IPage<Consultant> listPage(Page<Consultant> pageParam, ConsultantQuery consultantQuery) {
        if (consultantQuery == null) {
            return baseMapper.selectPage(pageParam, null);
        }

        String mobile = consultantQuery.getMobile();

        QueryWrapper<Consultant> consultantQueryWrapper = new QueryWrapper<>();
        consultantQueryWrapper.eq(StringUtils.isNotBlank(mobile), "mobile", mobile);

        return baseMapper.selectPage(pageParam, consultantQueryWrapper);
    }


    @Override
    public void lock(Integer id, Integer status) {
        Consultant consultant = new Consultant();
        consultant.setId(id);
        consultant.setStatus(status);
        baseMapper.updateById(consultant);
    }


    @Override
    public boolean checkMobile(String mobile) {
        QueryWrapper<Consultant> consultantQueryWrapper = new QueryWrapper<>();
        consultantQueryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(consultantQueryWrapper);
        return count > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterVO registerVO) {
        // 判断用户是否已被注册
        QueryWrapper<Consultant> consultantQueryWrapper = new QueryWrapper<>();
        consultantQueryWrapper.eq("mobile", registerVO.getMobile());
        Integer count = baseMapper.selectCount(consultantQueryWrapper);
        Assert.isTrue(count == 0, ResponseEnum.MOBILE_EXIST_ERROR);

        // 插入用户信息记录 user
        Consultant consultant = new Consultant();
        consultant.setNickName(registerVO.getMobile());
        consultant.setMobile(registerVO.getMobile());
        consultant.setPassword(MD5.encrypt(registerVO.getPassword()));
        consultant.setStatus(Consultant.STATUS_NORMAL);
        consultant.setHeadImg(Consultant.USER_AVATAR);
        baseMapper.insert(consultant);

        // 插入用户账户记录 user_account
        Account account = new Account();
        account.setUserId(consultant.getId());
        accountMapper.insert(account);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserVO login(LoginVO loginVO, String ip) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();

        // 用户是否存在
        QueryWrapper<Consultant> consultantQueryWrapper = new QueryWrapper<>();
        consultantQueryWrapper.eq("mobile", mobile);
        Consultant consultant = baseMapper.selectOne(consultantQueryWrapper);

        // 密码是否正确
        Assert.equals(MD5.encrypt(password), consultant.getPassword(), ResponseEnum.LOGIN_PASSWORD_ERROR);

        // 用户是否被禁用
        Assert.equals(consultant.getStatus(), consultant.STATUS_NORMAL, ResponseEnum.LOGIN_LOKED_ERROR);

        // 记录登录日志
//        UserLoginRecord userLoginRecord = new UserLoginRecord();
//        userLoginRecord.setUserId(userInfo.getId());
//        userLoginRecord.setIp(ip);
//        userLoginRecordMapper.insert(userLoginRecord);

        // 生成token
        String token = JwtUtils.createToken(consultant.getId(), consultant.getMobile());

        // 组装User
        UserVO userVO = new UserVO();
        userVO.setToken(token);
        userVO.setMobile(consultant.getMobile());
        userVO.setNickName(consultant.getNickName());
        userVO.setHeadImg(consultant.getHeadImg());
        userVO.setMobile(mobile);

        // 返回
        return userVO;
    }

    @Override
    public ConsultantIndexVO getIndexUserInfo(Integer userId) {
        //用户信息
        Consultant consultant = baseMapper.selectById(userId);

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
        ConsultantIndexVO consultantIndexVO = new ConsultantIndexVO();
        consultantIndexVO.setUserId(consultant.getId());
        consultantIndexVO.setMobile(consultant.getMobile());
        consultantIndexVO.setNickName(consultant.getNickName());
        consultantIndexVO.setHeadImg(consultant.getHeadImg());
//        consultantIndexVO.setLastLoginTime(userLoginRecord.getCreateTime());

        return consultantIndexVO;
    }


}
