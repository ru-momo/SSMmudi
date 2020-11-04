package com.suha.mapper;

import java.util.List;
import java.util.Map;

public interface PageMapper<T> {

    /**
     * 分页查询
     * @param map
     * @return
     */
    List<T> getListInfoByPage(Map<String, Object> map);

    /**
     * 根据条件，查询总页数
     * @param map
     * @return
     */
    Integer getListCountByPage(Map<String, Object> map);

}
