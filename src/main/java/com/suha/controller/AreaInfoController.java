package com.suha.controller;

import com.mysql.cj.util.StringUtils;
import com.suha.pojo.AreaInfo;
import com.suha.service.AreaInfoService;
import com.suha.service.RedisService;
import com.suha.util.Page;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/Area")
public class AreaInfoController {

    @Autowired
    private AreaInfoService ais;

    @Autowired
    private RedisService rs;

    @RequestMapping("{path}")
    public String index(@PathVariable String path){
        return "admin/Area/index";
    }

    /**
     * 加载所有的一级地区
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public Map<String, Object> getList(@RequestParam(defaultValue = "0") Integer parentId){
        AreaInfo record = new AreaInfo();
        record.setParentId(parentId);
        List<AreaInfo> list = ais.getListByRecord(record);
        return ResponseCode.ok(list);
    }

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
                                      @RequestParam(defaultValue = "7") Integer pageSize){
        Page<AreaInfo> pageList = ais.getPageList(name, pageNum, pageSize);
        model.addAttribute("page", pageList);
        model.addAttribute("name", name);
        return "admin/Area/index";
    }

    /**
     * 添加
     * @param name
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addInfo", method = RequestMethod.POST)
    public Map<String, Object> add(String name, Integer parentId){
        if (StringUtils.isNullOrEmpty(name)) {
            return ResponseCode.error("地区名不能为空");
        }
        AreaInfo record = new AreaInfo();
        record.setName(name);
        record.setParentId(parentId);
        ais.addInfo(record);
        return ResponseCode.ok("添加成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delInfo", method = RequestMethod.GET)
    public Map<String, Object> del(Integer id){
        if (id == null) {
            return ResponseCode.error("编号不存在");
        }
        AreaInfo info = ais.getInfoById(id);
        if (info == null) {
            return ResponseCode.error(id + "不存在");
        }
        ais.deleteByPrimaryKey(id);
        return ResponseCode.ok("删除成功！");
    }

    /**
     * 根据id进行查询
     * @param id
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public Map<String,Object> get(Integer id){
        if (id == null) {
            return ResponseCode.error("编号不存在");
        }
        AreaInfo info = ais.getInfoById(id);
        if (info == null) {
            return ResponseCode.error(id + "不存在");
        }
        return ResponseCode.ok(info);
    }

    /**
     * 更新
     * @param id
     * @param name
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updInfo", method = RequestMethod.POST)
    public Map<String, Object> upd(Integer id, String name,Integer parentId){
        if (StringUtils.isNullOrEmpty(name)) {
            return ResponseCode.error("地区不能为空");
        }
        AreaInfo info = ais.getInfoById(id);
        if (info == null) {
            return ResponseCode.error(id + "不存在");
        }
        info.setName(name);
        info.setParentId(parentId);
        ais.updInfo(info);
        return ResponseCode.ok("编辑成功!");
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
