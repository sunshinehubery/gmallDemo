package com.sunshinehubery.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunshinehubery.gmall.bean.PmsSkuAttrValue;
import com.sunshinehubery.gmall.bean.PmsSkuImage;
import com.sunshinehubery.gmall.bean.PmsSkuInfo;
import com.sunshinehubery.gmall.bean.PmsSkuSaleAttrValue;
import com.sunshinehubery.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.sunshinehubery.gmall.manage.mapper.PmsSkuImageMapper;
import com.sunshinehubery.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.sunshinehubery.gmall.manage.mapper.SkuMapper;
import com.sunshinehubery.gmall.service.SkuService;
import com.sunshinehubery.gmall.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

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
    @Autowired
    RedisUtil redisUtil;

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

    public PmsSkuInfo getItemBySkuIdFromDB(String skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        pmsSkuInfo = skuMapper.selectOne(pmsSkuInfo);
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);
        pmsSkuInfo.setSkuImageList(pmsSkuImages);
        return pmsSkuInfo;
    }

    @Override
    public PmsSkuInfo getItemBySkuId(String skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        //连接缓存
        Jedis jedis = redisUtil.getJedis();
        String skuKey = "sku:" + skuId + ":info";
        //查询缓存
        String skuJson = jedis.get(skuKey);
        if(StringUtils.isNotBlank(skuKey)){  //skuJson!=null&&!skuJson.equals("")
            pmsSkuInfo = JSON.parseObject(skuJson,PmsSkuInfo.class);
        }else {
            //缓存中查询不到，从数据库中获取
            //设置分布式锁
            String OK = jedis.set("sku:" + skuId + ":lock", "1", "nx", "px", 10);
            if (StringUtils.isNotBlank(OK) && OK.equals("OK")) {
                //设置成功,有权在10秒的过期时间内访问数据库
                pmsSkuInfo = getItemBySkuIdFromDB(skuId);
                if(pmsSkuInfo!=null){
                    //将从数据库查询的结果放入redis
                    jedis.set("sku:"+skuId+":info",JSON.toJSONString(pmsSkuInfo));
                }else {
                    //表示数据库不存在该sku
                    //防止缓存穿透，需要将null或者空字符串设置给redis
                    jedis.setex("sku:"+skuId+":info",60*3,JSON.toJSONString(""));
                }
            }else {
                //如果设置失败，自旋（将该线程休眠几秒后，重新访问）
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return getItemBySkuId(skuId);
            }
        }
        jedis.close();
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuInfoList(String productId) {
        List<PmsSkuInfo> pmsSkuInfos = skuMapper.selectSkuInfoList(productId);
        return pmsSkuInfos;
    }
}
