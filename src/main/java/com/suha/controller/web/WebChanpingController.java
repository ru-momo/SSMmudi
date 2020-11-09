package com.suha.controller.web;

import com.suha.pojo.ProductInfo;
import com.suha.pojo.TypeInfo;
import com.suha.service.ProductInfoService;
import com.suha.service.TypeInfoService;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class WebChanpingController {

    @Autowired
    private ProductInfoService pis;

    @Autowired
    private TypeInfoService tis;

    /**
     * 获取首页的十个产品
     * @return
     */
    @RequestMapping(value = "wine10",method = RequestMethod.POST)
    public Map<String, Object> wine(){
        List<ProductInfo> list = pis.getListInfo();
        return ResponseCode.ok(list.subList(0,10));
    }

    /**
     * 根据级别获取内容
     * @param parentId
     * @return
     */
    @RequestMapping(value = "type",method = RequestMethod.POST)
    public Map<String, Object> type(Integer parentId){
        TypeInfo record = new TypeInfo();
        record.setParentId(parentId);
        List<TypeInfo> list = tis.getListByRecord(record);
        return ResponseCode.ok(list);
    }

    /**
     * 获取展示的十种酒
     * @return
     */
    @RequestMapping(value = "getInfoList",method = RequestMethod.POST)
    public Map<String,Object> getInfoList(){
        List<ProductInfo> listInfo = pis.getListInfo();
        return ResponseCode.ok(listInfo.subList(0,12));
    }

    /**
     * 获取最新的数据
     * @return
     */
    @RequestMapping(value = "getInfoList2", method = RequestMethod.POST)
    public Map<String, Object> getInfoList2(){
        List<ProductInfo> infoList = pis.getListInfoOrderBy();
        return ResponseCode.ok(infoList);
    }




}
