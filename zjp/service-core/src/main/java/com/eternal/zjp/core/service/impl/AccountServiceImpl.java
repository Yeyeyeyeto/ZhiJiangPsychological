package com.eternal.zjp.core.service.impl;

import com.eternal.zjp.core.pojo.entity.Account;
import com.eternal.zjp.core.mapper.AccountMapper;
import com.eternal.zjp.core.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
