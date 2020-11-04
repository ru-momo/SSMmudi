package com.suha.service;

import com.suha.mapper.AreaInfoMapper;
import com.suha.pojo.AreaInfo;
import com.suha.pojo.NewsInfo;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaInfoService extends BaseService<AreaInfo>{

    @Autowired
    private AreaInfoMapper aim;

    public Page<AreaInfo> getPageList(String name, Integer pageNum, Integer pageSize){
        return super.getPageList(aim, name, pageNum, pageSize);
    }
}
