package com.sunshinehubery.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunshinehubery.gmall.bean.PmsBaseCatalog1;
import com.sunshinehubery.gmall.bean.PmsBaseCatalog2;
import com.sunshinehubery.gmall.bean.PmsBaseCatalog3;
import com.sunshinehubery.gmall.service.CataLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/2 20:50
 * @Version: 1.0
 **/
@Controller
@CrossOrigin
public class CataLogController {
    @Reference
    CataLogService cataLogService;

    @RequestMapping("/getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> pmsBaseCatalog1s =cataLogService.getCatalog1();
        return pmsBaseCatalog1s;
    }

    @RequestMapping("/getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){
        List<PmsBaseCatalog2> pmsBaseCatalog2s = cataLogService.getCatalog2(catalog1Id);
        return pmsBaseCatalog2s;
    }

    @RequestMapping("/getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id){
        List<PmsBaseCatalog3> pmsBaseCatalog3s = cataLogService.getCatalog3(catalog2Id);
        return pmsBaseCatalog3s;
    }
}
