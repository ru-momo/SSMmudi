package com.suha.controller.web;

import com.suha.pojo.AreaInfo;
import com.suha.pojo.NewsInfo;
import com.suha.pojo.ProductInfo;
import com.suha.pojo.TypeInfo;
import com.suha.service.AreaInfoService;
import com.suha.service.NewsInfoService;
import com.suha.service.ProductInfoService;
import com.suha.service.TypeInfoService;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin    //处理跨域请求的注解
public class WebIndexController {

    @Autowired
    private AreaInfoService ais;

    @Autowired
    private ProductInfoService pis;

    @Autowired
    private TypeInfoService tis;

    @Autowired
    private NewsInfoService nis;

    /**
     * 查询所有的一级地区
     * @param parentId
     * @return
     */
    @RequestMapping("areas")
    public Map<String, Object> areaList(@RequestParam(defaultValue = "0") Integer parentId){
        AreaInfo record = new AreaInfo();
        record.setParentId(parentId);
        List<AreaInfo> list = ais.getListByRecord(record);
        return ResponseCode.ok(list);
    }

    /**
     * 获取指定地区的产品
     * @param areaId
     * @param typeId
     * @return
     */
    @RequestMapping("products")
    public Map<String, Object> productList(@RequestParam(defaultValue = "1") Integer areaId,
                                           @RequestParam(defaultValue = "0") Integer typeId){
        ProductInfo record = new ProductInfo();
        record.setAreaId(areaId);
        List<ProductInfo> list = pis.getListByRecord(record);
        int length = list.size();
        //获取指定地区的产品，最多五个
        list = list.subList(0, length > 5 ? 5 : length);

        //加载所有酒的种类
        TypeInfo typeRecord = new TypeInfo();
        typeRecord.setParentId(typeId);
        List<TypeInfo> typeInfoList = tis.getListByRecord(typeRecord);

        Map<String, Object> map = ResponseCode.ok();
        map.put("typeList",typeInfoList);
        map.put("productList", list);
        return map;
    }

    /**
     * 查询最新发布的信息
     * @return
     */
    @RequestMapping("news")
    public Map<String, Object> newsList(){
        List<NewsInfo> list = nis.getNewsInfoByDesc();
        return ResponseCode.ok(list.subList(0,4));
    }



}
