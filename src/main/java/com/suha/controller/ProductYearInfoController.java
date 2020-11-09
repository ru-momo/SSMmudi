package com.suha.controller;

import com.suha.pojo.ProductYearInfo;
import com.suha.service.ProductYearInfoService;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin/ProductYear")
public class ProductYearInfoController {

    Map<String, Object> map = new HashMap<>();

    @Autowired
    private ProductYearInfoService pyis;

    /**
     * 分页查询
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(String name, Model model,
                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "5") Integer pageSize){
        Page<ProductYearInfo> pageList = pyis.getPageList(name, pageNum, pageSize);
        model.addAttribute("page", pageList);
        model.addAttribute("name", name);
        return "admin/ProductYear/index";
    }


}
