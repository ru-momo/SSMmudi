package com.suha.controller;

import com.suha.pojo.ProductInfo;
import com.suha.service.ProductInfoService;
import com.suha.service.RedisService;
import com.suha.util.MyFile;
import com.suha.util.Page;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("admin/Product")
public class ProductInfoController {

    @Autowired
    private ProductInfoService pis;

    @Autowired
    private RedisService rs;

    /**
     * 分页查询
     *
     * @param name
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "index")
    public String index(String name, Model model,
                        @RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "5") Integer pageSize) {
        Page<ProductInfo> list = pis.getPageList(name, pageNum, pageSize);
        model.addAttribute("page", list);
        model.addAttribute("name", name);
        return "admin/Product/index";
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getInfoById",method = RequestMethod.GET)
    public Map<String,Object> getInfoById(Integer id){
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
     * 更新
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "change",method = RequestMethod.POST)
    public Map<String,Object> change(ProductInfo record, MultipartFile file) throws Exception {
        if (record.getTypeId() < 1 || record.getTypeId() > 5 || record.getAreaId() < 1 || record.getAreaId() >5) {
            return ResponseCode.error("参数出错");
        }

        String origname = file.getOriginalFilename();
        if (origname != null && origname.length()>0) {
            String newFilename = MyFile.newFile(origname, file);
            record.setBigImg(newFilename);
        }

        try {
            pis.updInfo(record);
        }catch (Exception e){
            return ResponseCode.error("更新出错");
        }
        return ResponseCode.ok("更新成功");
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Map<String,Object> del(Integer id){
        if (id == null || id <= 0) {
            return ResponseCode.error("id参数错误");
        }
        ProductInfo info = pis.getInfoById(id);
        if (info == null) {
            return ResponseCode.error("id不存在");
        }
        try {
            pis.deleteByPrimaryKey(id);
        }catch (Exception e){
            return ResponseCode.error("删除失败");
        }
        MyFile.delFile(info.getBigImg());
        return ResponseCode.ok("删除成功");
    }

    /**
     * 添加
     * @param record
     * @param file
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Map<String,Object> add(ProductInfo record, MultipartFile file) throws Exception{
        if (record.getName() == null || record.getInfo() == null || record.getIntroduce() == null
                || record.getTypeId() == null || record.getAlcoholicStrength() == null || record.getAreaId() == null) {
            return ResponseCode.error("参数出错");
        }

        String origname = file.getOriginalFilename();
        if (origname != null && origname.length()>0) {
            String newFilename = MyFile.newFile(origname, file);
            record.setBigImg(newFilename);
        }
        if (record.getBigImg() == null) {
            return ResponseCode.error("参数出错");
        }
        record.setId(null);
        pis.addInfo(record);
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
