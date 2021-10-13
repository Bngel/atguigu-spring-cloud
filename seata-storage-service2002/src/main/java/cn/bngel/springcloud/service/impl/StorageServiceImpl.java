package cn.bngel.springcloud.service.impl;

import cn.bngel.springcloud.dao.StorageDao;
import cn.bngel.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------> 微服务扣减库存开始");
        storageDao.decrease(productId, count);
        log.info("------> 微服务扣减库存结束");
    }
}
