package com.suha.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "product_info")
public class ProductInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String info;
    private String introduce;
    private Integer typeId;
    private Integer alcoholicStrength;
    private Integer areaId;
    private Integer netWeight;
    private Integer saleCount;
    private String bigImg;

}
