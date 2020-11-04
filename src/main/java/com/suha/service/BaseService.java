package com.suha.service;

import com.mysql.cj.util.StringUtils;
import com.suha.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    @Autowired
    private RedisService rs;

    /**
     * 添加
     * @param record
     */
    public void addInfo(T record){
        rs.delAll();
        mapper.insert(record);
    }

    /**
     * 删除
     * @param record
     */
    public void delInfo(T record){
        rs.delAll();
        mapper.delete(record);
    }

    /**
     * 根据主键删除
     * @param id
     */
    public void deleteByPrimaryKey(Integer id){
        rs.delAll();
        mapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新
     * @param record
     */
    public void updInfo(T record){
        rs.delAll();
        mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public T getInfoById(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据条件查询
     * @param record
     * @return
     */
    public List<T> getListByRecord(T record){
        return mapper.select(record);
    }

    /**
     * 查询所有
     * @return
     */
    public List<T> getListInfo(){
        return mapper.selectAll();
    }

    /**
     * 获取总条数
     * @param record
     * @return
     */
    public Integer getInfoCount(T record){
        return mapper.selectCount(record);
    }

    /**
     * 分页查询
     * @param obj
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<T> getPageList(Object obj, String name, Integer pageNum, Integer pageSize){
        String key = obj.toString();
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isNullOrEmpty(name)) {
            map.put("name",name);
            key += "_" + name;
        }
        if (pageNum != null && pageSize != null) {
            map.put("startIndex", (pageNum-1)*pageSize);
            map.put("pageSize", pageSize);
            key += "_" + pageNum + "_" + pageSize;
        }
        Page<T> page = null;
        if (rs.hasKey(key)) {
            System.out.println("redis读取");
            page = (Page<T>)rs.get(key);
        }else {
            try {
                System.out.println("数据库读取");
                page = new Page<T>();
                //定义方法名
                String methodName = "getListInfoByPage";
                //获取对应的方法
                Method method = obj.getClass().getMethod(methodName, Map.class);
                //调用方法，获取结果集
                List<T> list = (List<T>) method.invoke(obj,map);

                methodName = "getListCountByPage";
                method = obj.getClass().getMethod(methodName, Map.class);
                Integer totalCount = (Integer)method.invoke(obj,map);

                //计算总页数
                int total = (int)Math.ceil(totalCount * 1.0 / pageSize);
                page.setPage(pageNum);
                page.setSize(pageSize);
                page.setRows(list);
                page.setTotal(total);
                page.setSum(totalCount);
                rs.set(key, page, 60*60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return page;
    }

}
