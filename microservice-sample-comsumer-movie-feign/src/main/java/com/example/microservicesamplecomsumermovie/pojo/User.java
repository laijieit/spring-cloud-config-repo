package com.example.microservicesamplecomsumermovie.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhanglaijie
 * @since 2019-08-22
 */
@Data
public class User {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;
}
