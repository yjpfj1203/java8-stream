package com.yjpfj1203.stream.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String birthday;//格式：yyyy-MM-dd

    private List<Gift> gifts;
}
