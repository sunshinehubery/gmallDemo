package com.sunshinehubery.gmall.service;

import com.sunshinehubery.gmall.bean.PmsSkuInfo;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/4 14:20
 * @Version: 1.0
 **/
public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getItemBySkuId(String skuId);

    List<PmsSkuInfo> getSkuInfoList(String productId);
}
