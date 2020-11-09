package com.suha.service;

import com.suha.mapper.ProductYearInfoMapper;
import com.suha.pojo.ProductYearInfo;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductYearInfoService extends BaseService<ProductYearInfo>{

    @Autowired
    private ProductYearInfoMapper pyim;

    public Page<ProductYearInfo> getPageList(String name, Integer pageNum, Integer pageSize){
        return super.getPageList(pyim, name, pageNum, pageSize);
    }

    //通过酒的编号和年份获取信息
    public ProductYearInfo getInfo(Map<String, Object> map){
        return pyim.getInfo(map);
    }

}
