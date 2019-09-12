package com.sunshinehubery.gmall.service;

import com.sunshinehubery.gmall.bean.PmsBaseSaleAttr;
import com.sunshinehubery.gmall.bean.PmsProductImage;
import com.sunshinehubery.gmall.bean.PmsProductInfo;
import com.sunshinehubery.gmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/3 21:02
 * @Version: 1.0
 **/
public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId, String skuId);
}
