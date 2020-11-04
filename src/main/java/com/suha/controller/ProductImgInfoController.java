package com.suha.controller;

import com.suha.pojo.MessageInfo;
import com.suha.pojo.ProductImgInfo;
import com.suha.service.MessageInfoService;
import com.suha.service.ProductImgInfoService;
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
@RequestMapping("admin/ProductImg")
public class ProductImgInfoController {

    Map<String, Object> map = new HashMap<>();

    @Autowired
    private ProductImgInfoService piis;

    /**
     * 分页查询
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pageList",method = RequestMethod.GET)
    public Page<ProductImgInfo> getPageList(String name,
                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                            @RequestParam(defaultValue = "3") Integer pageSize){
        return piis.getPageList(name,pageNum,pageSize);
    }
}
