package com.suha.controller.web;


import com.suha.pojo.ProductInfo;
import com.suha.pojo.ProductYearInfo;
import com.suha.service.ProductInfoService;
import com.suha.service.ProductYearInfoService;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin    //跨域请求
public class WebSpxqController {

    @Autowired
    private ProductInfoService pis;

    @Autowired
    private ProductYearInfoService pyis;

    /**
     * 获取商品详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "getInfo")
    public Map<String,Object> getInfo(Integer id){
        if (id == null || id <= 0) {
            return ResponseCode.error("id参数出错");
        }
        ProductInfo info = pis.getInfoById(id);
        if (info == null) {
            return ResponseCode.error("id不存在");
        }
        return ResponseCode.ok(info);
    }

    /**
     * 获取年份
     * @param wineId
     * @return
     */
    @RequestMapping(value = "getYear")
    public Map<String,Object> getYear(Integer wineId){
        if (wineId == null || wineId <= 0) {
            return ResponseCode.error("id参数出错");
        }
        ProductYearInfo record = new ProductYearInfo();
        record.setWineId(wineId);
        List<ProductYearInfo> infoList = pyis.getListByRecord(record);
        if(infoList.size() <= 0 ){
            return ResponseCode.error("库存不足");
        }
        return ResponseCode.ok(infoList);
    }

    /**
     * 通过酒的编号和年份获取信息
     * @param wineId
     * @param year
     * @return
     */
    @RequestMapping(value = "getPrice")
    public Map<String,Object> getPrice(Integer wineId, Integer year){
        Map<String, Object> map = new HashMap<>();
        map.put("wineId",wineId);
        map.put("year",year);
        ProductYearInfo info = pyis.getInfo(map);
        return ResponseCode.ok(info);
    }

}
