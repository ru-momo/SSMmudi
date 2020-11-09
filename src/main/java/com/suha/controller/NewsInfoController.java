package com.suha.controller;

import com.suha.pojo.NewsInfo;
import com.suha.service.NewsInfoService;
import com.suha.service.RedisService;
import com.suha.util.MyFile;
import com.suha.util.Page;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@Controller
@RequestMapping("admin/News")
public class NewsInfoController {

    @Autowired
    private NewsInfoService nis;

    @Autowired
    private RedisService rs;

    @RequestMapping("{path}")
    public String index(@PathVariable String path) {
        return "admin/News/index";
    }

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
                        @RequestParam(defaultValue = "3") Integer pageSize) {
        Page<NewsInfo> pageList = nis.getPageList(name, pageNum, pageSize);
        model.addAttribute("page", pageList);
        model.addAttribute("name", name);
        return "admin/News/index";
    }

    /**
     * 模糊查询
     * @param name
     * @return
     */
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public String query(String name){
        System.out.println(name);
        return "admin/News/index";
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "findById", method = RequestMethod.GET)
    public Map<String, Object> findById(Integer id) {
        if (id == null || id <= 0) {
            return ResponseCode.error("id为空或出错");
        }
        NewsInfo info = nis.getInfoById(id);
        if (info == null) {
            return ResponseCode.error("id不存在");
        }
        return ResponseCode.ok(info);
    }

    /**
     * 添加新闻
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> add(NewsInfo record, MultipartFile[] files) throws Exception {
        if (record.getTitle() == null || record.getContent() == null ||
                record.getPubdate() == null || record.getType() == null) {
            return ResponseCode.error("参数不全");
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String s = file.getOriginalFilename();
            String newFile = MyFile.newFile(s, file);
            if (i == 0)
                record.setImg1(newFile);
            else
                record.setImg2(newFile);
        }
        record.setId(null);
        nis.addInfo(record);
        return ResponseCode.ok("添加成功！");
    }

    /**
     * 修改新闻
     * @param record
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "change", method = RequestMethod.POST)
    public Map<String, Object> change(NewsInfo record, MultipartFile[] files) throws Exception{
        if (record.getId() == null || record.getTitle() == null || record.getContent() == null ||
                record.getPubdate() == null || record.getType() == null) {
            return ResponseCode.error("参数不全");
        }
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String s = file.getOriginalFilename();
            String newFile = MyFile.newFile(s, file);
            if (i == 0)
                record.setImg1(newFile);
            else
                record.setImg2(newFile);
        }
        nis.updInfo(record);
        return ResponseCode.ok("修改成功！");
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "del", method = RequestMethod.GET)
    public Map<String, Object> del(Integer id){
        if (id == null || id <= 0) {
            return ResponseCode.error("id参数出错");
        }
        NewsInfo info = nis.getInfoById(id);
        if (info == null){
            return ResponseCode.error("id不存在");
        }
        nis.deleteByPrimaryKey(id);
        MyFile.delFile(info.getImg1());
        MyFile.delFile(info.getImg2());
        return ResponseCode.ok("删除成功！");
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
