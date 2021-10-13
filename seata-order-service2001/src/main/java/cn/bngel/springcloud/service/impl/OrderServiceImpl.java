package cn.bngel.springcloud.service.impl;

import cn.bngel.springcloud.dao.OrderDao;
import cn.bngel.springcloud.domain.Order;
import cn.bngel.springcloud.service.AccountService;
import cn.bngel.springcloud.service.OrderService;
import cn.bngel.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "bngel-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------> 开始新建订单");
        orderDao.create(order);
        log.info("------> 微服务正在调用库存");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------> 微服务正在修改账户金额");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------> 微服务正在修改订单状态");
        orderDao.update(order.getUserId(), 0);
        log.info("------> 微服务修改订单状态结束");
    }
}
