package com.suha.service;

import com.suha.mapper.MessageInfoMapper;
import com.suha.pojo.MessageInfo;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageInfoService extends BaseService<MessageInfo>{

    @Autowired
    private MessageInfoMapper mim;

    public Page<MessageInfo> getPageList(String name, Integer pageNum, Integer pageSize){
        return super.getPageList(mim, name, pageNum, pageSize);
    }
}
