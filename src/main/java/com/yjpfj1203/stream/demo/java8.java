package com.yjpfj1203.stream.demo;

import com.yjpfj1203.stream.entity.Gift;
import com.yjpfj1203.stream.entity.User;
import com.yjpfj1203.stream.model.UserModel;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * java8 stream常见操作
 */
public class java8 {
    /**
     * 将一个userList转换为userModelList
     * @param userList
     * @return
     */
    public List<UserModel> map(List<User> userList){
        return userList
                .stream()
                .map(UserModel::new)
                //等价于下面的写法
//                .map(user -> {
//                    return new UserModel(user)
//                })
                .collect(Collectors.toList());
    }

    /**
     * 将userList变成id->user的键值对
     * @param userList
     * @return
     */
    public Map<Long, User> listToMap(List<User> userList){
        return userList
                .stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
                //.collect(Collectors.toMap(u1 -> u1.getId(), u2 -> u2));  //u1, u2都是user结构
    }

    /**
     * 按年龄分组
     * @param userList
     * @return
     */
    public Map<Integer, List<User>> groupByAge(List<User> userList){
        return userList
                .stream()
                .collect(Collectors.groupingBy(User::getAge));
                //.collect(Collectors.groupingBy(u -> u.getAge()));//等价于上面一句
    }

    /**
     * 按年龄排序
     * @param userList
     * @return
     */
    public List<User> sortByAge(List<User> userList){
        return userList
                .stream()
                .sorted((a, b) -> {
                    if (a.getAge() == null || b.getAge() == null){
                        return 1;
                    }
                    return a.getAge().compareTo(b.getAge());
                })
                .collect(Collectors.toList());
    }

    /**
     * 条件过滤
     * @param userList
     * @return
     */
    public List<User> filter(List<User> userList){
        return userList
                .stream()
                .filter(u -> u.getAge() != null)
                .collect(Collectors.toList());
    }

    /**
     * 计算平均年龄，如果年龄没有填写的忽略
     * reduce数据整合
     * @param userList
     * @return
     */
    public Integer reduce(List<User> userList){
        if (CollectionUtils.isEmpty(userList)){
            return 0;
        }
        List<User> ageUserList = userList
                .stream()
                .filter(u -> u.getAge() != null)
                .collect(Collectors.toList());
        return ageUserList
                .stream()
                .map(u -> u.getAge())
                .reduce((age1, age2) -> age1 + age2)
                .orElse(0)                          //以上是算总年龄总合
                /ageUserList.size();
    }

    /**
     * 查询所有人的礼物
     * @param userList
     * @return
     */
    public List<Gift> flatMap(List<User> userList){
        return userList
                .stream()
                .flatMap(u -> u.getGifts().stream()) //u.getGifts().stream()，必须转换为stream类型再进行stream的操作
                .collect(Collectors.toList());
    }

    /**
     * birthday先按年分组，再按月分组
     * birthday格式：yyyy-MM-dd
     */
    public Map<String, Map<String, List<UserModel>>> groupByYearAndMonth(List<User> userList){
        return map(userList)
                .stream()
                .collect(Collectors.groupingBy(u -> {
                    return u.getBirthday().substring(0, 4);
                }))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(k -> k.getKey(), v ->{
                    return v.getValue().stream().collect(Collectors.groupingBy(u -> {
                        return u.getBirthday().substring(5, 7);
                    }));
                }));
    }
}
