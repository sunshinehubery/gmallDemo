package com.sunshinehubery.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunshinehubery.gmall.bean.*;
import com.sunshinehubery.gmall.manage.mapper.*;
import com.sunshinehubery.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/3 21:06
 * @Version: 1.0
 **/
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    SpuMapper spuMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return spuMapper.select(pmsProductInfo);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }

    @Override
    public void saveSpuInfo(PmsProductInfo pmsProductInfo) {
        //保存pmsProductInfo
        spuMapper.insertSelective(pmsProductInfo);
        //获取pmsProductInfo对象的主键
        String id = pmsProductInfo.getId();
        //保存pmsProductImage
        List<PmsProductImage> pmsProductImageList = pmsProductInfo.getSpuImageList();
        for (PmsProductImage pmsProductImage : pmsProductImageList) {
            pmsProductImage.setProductId(id);
            pmsProductImageMapper.insertSelective(pmsProductImage);
        }
        //保存pmsProductSaleAttr
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrList) {
            pmsProductSaleAttr.setProductId(id);
            pmsProductSaleAttrMapper.insertSelective(pmsProductSaleAttr);
            //保存pmsProductSaleAttrValue
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : pmsProductSaleAttrValueList) {
                pmsProductSaleAttrValue.setProductId(id);
                pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getId());
                pmsProductSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }

        }
    }
}
