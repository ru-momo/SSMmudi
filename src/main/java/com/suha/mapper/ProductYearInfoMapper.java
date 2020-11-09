package com.suha.mapper;

import com.suha.pojo.ProductYearInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface ProductYearInfoMapper extends Mapper<ProductYearInfo>,PageMapper<ProductYearInfo> {

    ProductYearInfo getInfo(Map<String,Object> map);
}
