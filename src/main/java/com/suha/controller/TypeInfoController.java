package com.suha.controller;

import com.suha.pojo.ProductInfo;
import com.suha.pojo.TypeInfo;
import com.suha.service.ProductInfoService;
import com.suha.service.RedisService;
import com.suha.service.TypeInfoService;
import com.suha.util.Page;
import com.suha.util.ResponseCode;
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
@RequestMapping("admin/Type")
public class TypeInfoController {

    @Autowired
    private TypeInfoService tis;

    @Autowired
    private RedisService rs;

    /**
     * 分页查询
     *
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(String name, Model model,
                        @RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "5") Integer pageSize) {
        Page<TypeInfo> pageList = tis.getPageList(name, pageNum, pageSize);
        model.addAttribute("page", pageList);
        model.addAttribute("name", name);
        return "admin/Type/index";
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getInfoById", method = RequestMethod.GET)
    public Map<String, Object> getInfoById(Integer id){
        if (id <= 0 || id == null) {
            return ResponseCode.error("id参数不正确");
        }
        TypeInfo info = tis.getInfoById(id);
        if (info == null) {
            return ResponseCode.error("id不存在");
        }
        return ResponseCode.ok(info);
    }

    /**
     * 更新
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "change", method = RequestMethod.POST)
    public Map<String,Object> change(TypeInfo record){
        if (record.getId() == null || record.getCname() == null
                || record.getEname() == null || record.getParentId() == null) {
            return ResponseCode.error("参数不全");
        }
        try {
            tis.updInfo(record);
        }catch (Exception e){
            return ResponseCode.error("编制失败！请检查后台代码");
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
    public Map<String,Object> add(TypeInfo record){
        if (record.getCname() == null || record.getEname() == null || record.getParentId() == null) {
            return ResponseCode.error("参数不全");
        }
        try {
            record.setId(null);
            tis.addInfo(record);
        }catch (Exception e){
            return ResponseCode.error("添加失败！请检查后台代码");
        }
        return ResponseCode.ok("添加成功");
    }


    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Map<String,Object> del(Integer id){
        if (id == null || id <= 0) {
            return ResponseCode.error("id参数出错");
        }
        TypeInfo info = tis.getInfoById(id);
        if (info == null) {
            return ResponseCode.error("id不存在");
        }
        try {
            tis.deleteByPrimaryKey(id);
        }catch (Exception e){
            return ResponseCode.error("删除失败，请检查后台代码");
        }
        return ResponseCode.ok("删除成功");
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
