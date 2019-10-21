package com.firecode.springcloud_alibaba.user.biz.domain.user;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "user")
public class User {
    /**
     * 我的id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 我的名称
     */
    private String name;
}