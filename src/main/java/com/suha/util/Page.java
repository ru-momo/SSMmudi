package com.suha.util;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int total;      //总页数
    private int page;       //当前页
    private int size;       //每页数
    private List<T> rows;   //结果集
    private int sum;        //总条数

}
