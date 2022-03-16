package com.eternal.zjp.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eternal.zjp.core.pojo.entity.Consultant;
import com.eternal.zjp.core.pojo.query.ConsultantQuery;
import com.eternal.zjp.core.pojo.vo.ConsultantIndexVO;
import com.eternal.zjp.core.pojo.vo.LoginVO;
import com.eternal.zjp.core.pojo.vo.RegisterVO;
import com.eternal.zjp.core.pojo.vo.UserVO;

/**
 * <p>
 * 咨询师表 服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-14
 */
public interface ConsultantService extends IService<Consultant> {

    void lock(Integer id, Integer status);

    void register(RegisterVO registerVO);

    UserVO login(LoginVO loginVO, String ip);

    boolean checkMobile(String mobile);

    ConsultantIndexVO getIndexUserInfo(Integer userId);

    IPage<Consultant> listPage(Page<Consultant> pageParam, ConsultantQuery consultantQuery);
}
