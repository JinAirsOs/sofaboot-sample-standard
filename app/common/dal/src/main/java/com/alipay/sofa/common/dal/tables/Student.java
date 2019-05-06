package com.alipay.sofa.common.dal.tables;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    //自增ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="age")
    private Integer age;

    //需要声明无参数的构造函数
    public Student(){  }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}