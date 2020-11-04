package com.suha.service;

import com.suha.mapper.NewsInfoMapper;
import com.suha.pojo.NewsInfo;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsInfoService extends BaseService<NewsInfo>{

    @Autowired
    private NewsInfoMapper nim;

    /**
     * 分页查询
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<NewsInfo> getPageList(String name, Integer pageNum, Integer pageSize){
        return super.getPageList(nim, name, pageNum, pageSize);
    }

    public List<NewsInfo> getNewsInfoByDesc(){
        return nim.getNewsInfoByDesc();
    }

}
