package com.suha.controller;

import com.mysql.cj.util.StringUtils;
import com.suha.pojo.MessageInfo;
import com.suha.service.MessageInfoService;
import com.suha.util.Page;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("admin/Message")
public class MessageInfoController {

    @Autowired
    private MessageInfoService mis;

    @RequestMapping("{path}")
    public String index(@PathVariable String path){
        return "admin/Message/index";
    }

    /**
     * 分页查询
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pageList",method = RequestMethod.GET)
    public Page<MessageInfo> getPageList(String name,
                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "3") Integer pageSize){
        return mis.getPageList(name,pageNum,pageSize);
    }

    /**
     * 添加
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addInfo",method = RequestMethod.POST)
    public Map<String, Object> add(MessageInfo record){
        if (record.getName() == null) {
            return ResponseCode.error("名字不能为空");
        }
        mis.addInfo(record);
        return ResponseCode.ok();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delInfo", method = RequestMethod.GET)
    public Map<String, Object> del(Integer id){
        if(id == null){
            return ResponseCode.error("编号不存在");
        }
        MessageInfo info = mis.getInfoById(id);
        if(info == null){
            return ResponseCode.error(id + "不存在");
        }
        mis.deleteByPrimaryKey(id);
        return ResponseCode.ok();
    }

    /**
     * 更新
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updInfo", method = RequestMethod.POST)
    public Map<String, Object> upd(MessageInfo record){
        if (StringUtils.isNullOrEmpty(record.getName())) {
            return ResponseCode.error("名字不能为空");
        }
        MessageInfo info = mis.getInfoById(record.getId());
        if (info == null) {
            return ResponseCode.error("id不存在");
        }
        mis.addInfo(record);
        return ResponseCode.ok();
    }

}
