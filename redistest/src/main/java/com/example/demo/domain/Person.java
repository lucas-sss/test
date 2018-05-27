package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/5/25 0025
 */
@Data
public class Person {

    @JsonIgnore
    private String name;

    private String sex;

    private Integer age;

    @JsonIgnore
    private List<String> list = new ArrayList<>();

}
