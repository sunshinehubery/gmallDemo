package com.sunshinehubery.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunshinehubery.gmall.bean.PmsBaseAttrInfo;
import com.sunshinehubery.gmall.bean.PmsBaseAttrValue;
import com.sunshinehubery.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/2 21:34
 * @Version: 1.0
 **/
@Controller
@CrossOrigin
public class AttrController {
    @Reference
    AttrService attrService;

    @RequestMapping("/attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfos;
    }

    @RequestMapping("/saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        attrService.saveAttrInfo(pmsBaseAttrInfo);
        return "redirect:attrInfoList?"+pmsBaseAttrInfo.getCatalog3Id();
    }

    @RequestMapping("/getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValues = attrService.getAttrValueList(attrId);
        return pmsBaseAttrValues;
    }
}
