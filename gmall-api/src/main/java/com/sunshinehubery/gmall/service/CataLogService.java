package com.sunshinehubery.gmall.service;

import com.sunshinehubery.gmall.bean.PmsBaseCatalog1;
import com.sunshinehubery.gmall.bean.PmsBaseCatalog2;
import com.sunshinehubery.gmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/2 20:56
 * @Version: 1.0
 **/
public interface CataLogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
