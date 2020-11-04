package com.suha.mapper;

import com.suha.pojo.NewsInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NewsInfoMapper extends Mapper<NewsInfo> , PageMapper<NewsInfo>{

    /**
     * 降序排列
     * @return
     */
    List<NewsInfo> getNewsInfoByDesc();
}
