package com.sunshinehubery.gmall.manage.mapper;

import com.sunshinehubery.gmall.bean.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/3 22:45
 * @Version: 1.0
 **/
public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId,@Param("skuId") String skuId);
}
