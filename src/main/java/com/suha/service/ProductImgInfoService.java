package com.suha.service;

import com.suha.mapper.ProductImgInfoMapper;
import com.suha.pojo.ProductImgInfo;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImgInfoService extends BaseService<ProductImgInfo>{

    @Autowired
    private ProductImgInfoMapper piim;

    public Page<ProductImgInfo> getPageList(String name, Integer pageNum, Integer pageSize){
        return super.getPageList(piim, name, pageNum, pageSize);
    }
}
