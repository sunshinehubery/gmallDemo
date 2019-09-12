package com.sunshinehubery.gmall.manage.mapper;

import com.sunshinehubery.gmall.bean.PmsSkuInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/4 14:21
 * @Version: 1.0
 **/
public interface SkuMapper extends Mapper<PmsSkuInfo> {
    List<PmsSkuInfo> selectSkuInfoList(@Param("productId") String productId);
}
