package com.sunshinehubery.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sunshinehubery.gmall.bean.PmsBaseSaleAttr;
import com.sunshinehubery.gmall.bean.PmsProductInfo;
import com.sunshinehubery.gmall.manage.utils.PmsUploadUtil;
import com.sunshinehubery.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2019/9/3 20:56
 * @Version: 1.0
 **/
@Controller
@CrossOrigin   //解决跨域问题
public class SpuController {
    @Reference
    SpuService spuService;

    @RequestMapping("/spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    @RequestMapping("/baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> pmsBaseSaleAttrs = spuService.baseSaleAttrList();
        return pmsBaseSaleAttrs;
    }

    @RequestMapping("/saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.saveSpuInfo(pmsProductInfo);
        return "redirect:spuList?" + pmsProductInfo.getCatalog3Id();
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file")MultipartFile multipartFile){
        //通过PmsUploadUtil将图片上传到fastfds
        String url = PmsUploadUtil.uploadImage(multipartFile);
        return url;
    }
}
