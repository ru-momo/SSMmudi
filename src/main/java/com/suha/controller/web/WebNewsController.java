package com.suha.controller.web;

import com.suha.pojo.NewsInfo;
import com.suha.service.NewsInfoService;
import com.suha.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class WebNewsController {

    @Autowired
    private NewsInfoService nis;

    /**
     *获取所有的新闻
     * @return
     */
    @RequestMapping(value = "getAllNews", method = RequestMethod.GET)
    public Map<String, Object> getAllNews(){
        List<NewsInfo> listInfo = nis.getNewsInfoByDesc();
        return ResponseCode.ok(listInfo);
    }



}
