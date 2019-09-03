package com.sunshinehubery.gmall.service;

import com.sunshinehubery.gmall.bean.PmsBaseAttrInfo;
import com.sunshinehubery.gmall.bean.PmsBaseAttrValue;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/2 21:37
 * @Version: 1.0
 **/
public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    void saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);
}
