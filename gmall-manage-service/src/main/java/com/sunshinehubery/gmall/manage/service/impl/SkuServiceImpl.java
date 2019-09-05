package com.sunshinehubery.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.sunshinehubery.gmall.bean.PmsSkuAttrValue;
import com.sunshinehubery.gmall.bean.PmsSkuImage;
import com.sunshinehubery.gmall.bean.PmsSkuInfo;
import com.sunshinehubery.gmall.bean.PmsSkuSaleAttrValue;
import com.sunshinehubery.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.sunshinehubery.gmall.manage.mapper.PmsSkuImageMapper;
import com.sunshinehubery.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.sunshinehubery.gmall.manage.mapper.SkuMapper;
import com.sunshinehubery.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/4 14:20
 * @Version: 1.0
 **/
@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    SkuMapper skuMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        //保存pmsSkuInfo
        skuMapper.insertSelective(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();
        //保存pmsSkuAttrValue
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }
        //保存pmsSkuSaleAttrValue
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }
        //保存pmsSkuImage
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }
    }
}
