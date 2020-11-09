package com.suha.service;

import com.suha.mapper.ProductInfoMapper;
import com.suha.pojo.ProductInfo;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoService extends BaseService<ProductInfo>{

    @Autowired
    private ProductInfoMapper pim;

    public Page<ProductInfo> getPageList(String name, Integer pageNum, Integer pageSize){
        return super.getPageList(pim, name, pageNum, pageSize);
    }

    //获取最新的数据
    public List<ProductInfo> getListInfoOrderBy(){
        return pim.getListInfoOrderBy();
    }

}
