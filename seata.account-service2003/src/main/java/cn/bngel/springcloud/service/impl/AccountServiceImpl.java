package cn.bngel.springcloud.service.impl;

import cn.bngel.springcloud.dao.AccountDao;
import cn.bngel.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------> 微服务扣减余额开始");
        accountDao.decrease(userId, money);
        log.info("------> 微服务扣减余额结束");
    }
}
