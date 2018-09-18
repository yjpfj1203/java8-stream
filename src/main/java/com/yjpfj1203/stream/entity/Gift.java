package com.yjpfj1203.stream.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Gift {
    private Long id;
    private String name;
    private GiftTypeEnum giftType;
}
