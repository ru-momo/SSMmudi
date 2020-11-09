package com.suha.controller;

import com.suha.pojo.ProductYearInfo;
import com.suha.service.ProductYearInfoService;
import com.suha.service.RedisService;
import com.suha.util.Page;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin/ProductYear")
public class ProductYearInfoController {

    @Autowired
    private ProductYearInfoService pyis;

    @Autowired
    private RedisService rs;

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

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getInfoById",method = RequestMethod.GET)
    public Map<String, Object> getInfoById(Integer id){
        if (id == null || id <= 0) {
            return ResponseCode.error("id参数出错");
        }
        ProductYearInfo info = pyis.getInfoById(id);
        if (info == null) {
            return ResponseCode.error("id不存在");
        }
        return ResponseCode.ok(info);
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public Map<String, Object> del(Integer id){
        if (id == null || id <= 0) {
            return ResponseCode.error("id参数出错");
        }
        ProductYearInfo info = pyis.getInfoById(id);
        if (info == null) {
            return ResponseCode.error("id不存在");
        }
        return ResponseCode.ok("删除成功");
    }

    /**
     * 更新
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "change", method = RequestMethod.POST)
    public Map<String, Object> change(ProductYearInfo record){
        if (record.getId() == null) {
            return ResponseCode.error("id参数出错");
        }
        try {
            pyis.updInfo(record);
        }catch (Exception e){
            return ResponseCode.error("编辑出错，请查看后台");
        }
        return ResponseCode.ok("编辑成功");
    }

    /**
     * 添加
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(ProductYearInfo record){
        if (record.getYear() == null || record.getPrice() == null ||
                record.getWineId() == null ||record.getStock() == null) {
            return ResponseCode.error("参数出错");
        }
        record.setId(null);
        try {
            pyis.addInfo(record);
        }catch (Exception e){
            return ResponseCode.error("添加出错，请查看后台");
        }
        return ResponseCode.ok("添加成功");
    }

    /**
     *清除缓存
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "reload")
    public Map<String,Object> reload(){
        rs.delAll();
        return ResponseCode.ok();
    }


}
