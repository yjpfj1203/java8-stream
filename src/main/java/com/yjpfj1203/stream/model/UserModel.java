package com.yjpfj1203.stream.model;

import com.yjpfj1203.stream.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String name;
    private Integer age;
    private String birthday;

    public UserModel(User user){
        setId(user.getId());
        setName(user.getName());
        setAge(user.getAge());
        setBirthday(user.getBirthday());
    }

}
