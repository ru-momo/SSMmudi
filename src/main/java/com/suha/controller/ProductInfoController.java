package com.suha.controller;

import com.suha.pojo.ProductInfo;
import com.suha.service.ProductInfoService;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin/Product")
public class ProductInfoController {

    Map<String, Object> map = new HashMap<>();

    @Autowired
    private ProductInfoService pis;

    /**
     * 分页查询
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pageList",method = RequestMethod.GET)
    public Page<ProductInfo> getPageList(String name,
                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "3") Integer pageSize){
        return pis.getPageList(name,pageNum,pageSize);
    }
}
