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
import com.eternal.zjp.core.enums.ConsultantStatusEnum;
import com.eternal.zjp.core.mapper.AccountMapper;
import com.eternal.zjp.core.mapper.ConsultantAttachMapper;
import com.eternal.zjp.core.mapper.ConsultantMapper;
import com.eternal.zjp.core.pojo.entity.Account;
import com.eternal.zjp.core.pojo.entity.Consultant;
import com.eternal.zjp.core.pojo.entity.ConsultantAttach;
import com.eternal.zjp.core.pojo.query.ConsultantQuery;
import com.eternal.zjp.core.pojo.vo.*;
import com.eternal.zjp.core.service.ConsultantAttachService;
import com.eternal.zjp.core.service.ConsultantService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.List;

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

    @Resource
    private ConsultantMapper consultantMapper;

    @Resource
    private ConsultantAttachMapper consultantAttachMapper;

    @Resource
    private ConsultantAttachService consultantAttachService;

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
    public Integer getAuthStatusById(Integer userId) {
        QueryWrapper<Consultant> consultantQueryWrapper = new QueryWrapper<>();
        consultantQueryWrapper.select("consultant_auth_status").eq("id", userId);
        List<Object> objects = baseMapper.selectObjs(consultantQueryWrapper);
        if (objects.size() == 0) {
            return ConsultantStatusEnum.NO_AUTH.getStatus();
        }

        Integer status = (Integer) objects.get(0);

        return status;
    }

    @Override
    public void saveConsultantVOById(ConsultantVO consultantVO, Integer userId) {
        Consultant consultant = consultantMapper.selectById(userId);

        //保存信息
        consultant.setNickName(consultantVO.getNickName());
        consultant.setConsultantGrade(consultantVO.getConsultantGrade());
        consultant.setConsultantLocation(consultantVO.getConsultantLocation());
        consultant.setConsultantDirection(consultantVO.getConsultantDirection());
        consultant.setConsultantMotto(consultantVO.getConsultantMotto());
        consultant.setConsultantCost(consultantVO.getConsultantCost());
        consultant.setSex(consultantVO.getSex());
        consultant.setAge(consultantVO.getAge());

        //保存附件
        List<ConsultantAttach> consultantAttachList = consultantVO.getConsultantAttachList();
        consultantAttachList.forEach(consultantAttach -> {
            consultantAttach.setConsultantId(consultant.getId());
            consultantAttachMapper.insert(consultantAttach);
        });

        //更新会员状态，更新为认证中
        consultant.setConsultantAuthStatus(ConsultantStatusEnum.AUTH_RUN.getStatus());

        consultantMapper.updateById(consultant);
    }

    @Override
    public ConsultantDetailVO getConsultantDetailVOById(Integer id) {
        Consultant consultant = baseMapper.selectById(id);

        ConsultantDetailVO consultantDetailVO = new ConsultantDetailVO();
        BeanUtils.copyProperties(consultant, consultantDetailVO);

        consultantDetailVO.setSex(consultant.getSex()==1?"男":"女");

        System.out.println("-----------------");
        System.out.println(consultantDetailVO.toString());

        String status = ConsultantStatusEnum.getMsgByStatus(consultant.getStatus());
        consultantDetailVO.setStatus(status);

        List<ConsultantAttachVO> consultantAttachVOList =  consultantAttachService.selectConsultantAttachVOList(id);
        consultantDetailVO.setConsultantAttachVOList(consultantAttachVOList);

        return consultantDetailVO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void approval(Integer id, Integer status) {
        Consultant consultant = baseMapper.selectById(id);
        consultant.setConsultantAuthStatus(status);
        baseMapper.updateById(consultant);
    }

    @SneakyThrows
    @Override
    public void updateImgById(Integer userId, String imgUrl) {
        Consultant consultant = baseMapper.selectById(userId);

        System.out.println("----------------");
//        System.out.println(imgUrl);

        imgUrl = imgUrl.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
        String urlStr = URLDecoder.decode(imgUrl, "UTF-8");
        urlStr = urlStr.substring(0, urlStr.length() - 1);
        System.out.println(urlStr);


        consultant.setHeadImg(urlStr);
        baseMapper.updateById(consultant);
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
        userVO.setType(consultant.getType());

        // 返回
        return userVO;
    }

    @Override
    public ConsultantIndexVO getIndexUserInfo(Integer userId) {
        //用户信息
        Consultant consultant = baseMapper.selectById(userId);

        //组装结果数据
        ConsultantIndexVO consultantIndexVO = new ConsultantIndexVO();
        BeanUtils.copyProperties(consultant, consultantIndexVO);
        consultantIndexVO.setUserId(consultant.getId());
        consultantIndexVO.setStatus(consultant.getConsultantAuthStatus());

        return consultantIndexVO;
    }


}
