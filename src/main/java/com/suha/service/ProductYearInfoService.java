package com.suha.service;

import com.suha.mapper.ProductYearInfoMapper;
import com.suha.pojo.ProductYearInfo;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductYearInfoService extends BaseService<ProductYearInfo>{

    @Autowired
    private ProductYearInfoMapper pyim;

    public Page<ProductYearInfo> getPageList(String name, Integer pageNum, Integer pageSize){
        return super.getPageList(pyim, name, pageNum, pageSize);
    }
}
