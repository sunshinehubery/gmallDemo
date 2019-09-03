package com.sunshinehubery.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunshinehubery.gmall.bean.PmsBaseCatalog1;
import com.sunshinehubery.gmall.bean.PmsBaseCatalog2;
import com.sunshinehubery.gmall.bean.PmsBaseCatalog3;
import com.sunshinehubery.gmall.manage.mapper.CataLog1Mapper;
import com.sunshinehubery.gmall.manage.mapper.CataLog2Mapper;
import com.sunshinehubery.gmall.manage.mapper.CataLog3Mapper;
import com.sunshinehubery.gmall.service.CataLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/2 20:57
 * @Version: 1.0
 **/
@Service
public class CataLogServiceImpl implements CataLogService {
    @Autowired
    CataLog1Mapper cataLog1Mapper;
    @Autowired
    CataLog2Mapper cataLog2Mapper;
    @Autowired
    CataLog3Mapper cataLog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return cataLog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        return cataLog2Mapper.select(pmsBaseCatalog2);
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        return cataLog3Mapper.select(pmsBaseCatalog3);
    }
}
