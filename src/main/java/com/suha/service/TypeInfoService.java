package com.suha.service;

import com.suha.mapper.TypeInfoMapper;
import com.suha.pojo.TypeInfo;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeInfoService extends BaseService<TypeInfo>{

    @Autowired
    private TypeInfoMapper tim;

    public Page<TypeInfo> getPageList(String name, Integer pageNum, Integer pageSize){
        return super.getPageList(tim, name, pageNum, pageSize);
    }
}
