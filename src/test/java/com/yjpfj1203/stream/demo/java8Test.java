package com.yjpfj1203.stream.demo; 

import com.google.gson.Gson;
import com.yjpfj1203.stream.entity.Gift;
import com.yjpfj1203.stream.entity.User;
import com.yjpfj1203.stream.model.UserModel;
import org.junit.Test;
import org.junit.Before; 
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.After; 
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static io.github.benas.randombeans.api.EnhancedRandom.randomListOf;
import static io.github.benas.randombeans.api.EnhancedRandom.randomStreamOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/** 
 * java8 Tester. 
 * 
 * @author liuhan 
 * @since Sep 18, 2018
 * @version 1.0 
 */ 
@RunWith(MockitoJUnitRunner.class)
public class java8Test { 
    @InjectMocks
    private java8 java8;

    private List<User> userList;
    

    @Before
    public void before() throws Exception {
        userList = randomListOf(50, User.class);
        Random random = new Random();
        for (User user: userList){
            user.setId(((long )(Math.random() * 100000000000l + 1)));
            user.setAge((int)(Math.random() * 100 + 1));
            user.setGifts(randomListOf((int) (Math.random() * 10), Gift.class));
            String year = "201"+((int )(Math.random() * 5));
            int monthInt = ((int )(Math.random() * 12 + 1));
            String month = monthInt < 10 ? ("0" + monthInt): ("" + monthInt);
            int dayInt = (int )(Math.random() * 28 + 1);
            String day = dayInt < 10? ("0" + dayInt): ("" + dayInt);

            user.setBirthday( year+"-" + month + "-" + day);
        }
    } 
    
    @After
    public void after() throws Exception { 
    } 
    
    /** 
     * @author liuhan 
     * @since Sep 18, 2018
     * Method: map(List<User> userList) 
     */ 
    @Test
    public void testMap() {
        List<UserModel> users = java8.map(userList);
        System.out.println(new Gson().toJson(users));
    } 
    
    /** 
     * @author liuhan 
     * @since Sep 18, 2018
     * Method: listToMap(List<User> userList) 
     */ 
    @Test
    public void testListToMap() {
        Map<Long, User> usermap = java8.listToMap(userList);
        System.out.println(new Gson().toJson(usermap));
    } 
    
    /** 
     * @author liuhan 
     * @since Sep 18, 2018
     * Method: groupByAge(List<User> userList) 
     */ 
    @Test
    public void testGroupByAge() {
        Map<Integer, List<User>> ageMap = java8.groupByAge(userList);
        System.out.println(new Gson().toJson(ageMap));
    } 
    
    /** 
     * @author liuhan 
     * @since Sep 18, 2018
     * Method: sortByAge(List<User> userList) 
     */ 
    @Test
    public void testSortByAge() {
        List<User> users = java8.sortByAge(userList);
        System.out.println(new Gson().toJson(users));
    } 
    
    /** 
     * @author liuhan 
     * @since Sep 18, 2018
     * Method: filter(List<User> userList) 
     */ 
    @Test
    public void testFilter() {
        List<User> users = java8.filter(userList);
        System.out.println(new Gson().toJson(users));
    } 
    
    /** 
     * @author liuhan 
     * @since Sep 18, 2018
     * Method: reduce(List<User> userList) 
     */ 
    @Test
    public void testReduce() {
        int average = java8.reduce(userList);
        System.out.println(average);
    } 
    
    /** 
     * @author liuhan 
     * @since Sep 18, 2018
     * Method: flatMap(List<User> userList) 
     */ 
    @Test
    public void testFlatMap() {
        List<Gift> gifts = java8.flatMap(userList);
        System.out.println(new Gson().toJson(gifts));
    } 
    
    /** 
     * @author liuhan 
     * @since Sep 18, 2018
     * Method: groupByYearAndMonth(List<User> userList) 
     */ 
    @Test
    public void testGroupByYearAndMonth() {
        Map map = java8.groupByYearAndMonth(userList);
        System.out.println(new Gson().toJson(map));
    } 
    
        
} 
