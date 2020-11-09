package com.suha.mapper;

import com.suha.pojo.ProductInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductInfoMapper extends Mapper<ProductInfo>,PageMapper<ProductInfo> {

    //获取最新的12条数据
    List<ProductInfo> getListInfoOrderBy();
}
