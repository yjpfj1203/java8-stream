package com.yjpfj1203.stream.demo;

import com.google.gson.Gson;
import com.yjpfj1203.stream.lambda.Converter;
import com.yjpfj1203.stream.lambda.GreetingService;
import com.yjpfj1203.stream.lambda.MathOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 如果有接口内有且只有一个方法时，可使用lambda表达式
 */
@RunWith(MockitoJUnitRunner.class)
public class LambdaTest {
    int i = 50;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testMathOperation(){
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 - 5 = " + operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));
    }

    @Test
    public void testGreetingService(){
        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    @Test
    public void testConverter(){
        int num = 1;  //如果被lambda表达式引用，默认为final
        Converter s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);  // 输出结果为 3
    }

    @Test
    public void testRunnable(){
        Runnable anonymous_runnable_run = new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymous_runnable_run");
            }
        };
        anonymous_runnable_run.run();

        Runnable lambda_runnable_run = () ->{
            System.out.println("lambda runnable run");
        };
        lambda_runnable_run.run();

        new Thread(() -> System.out.println("thread start")).start();
    }

    @Test
    public void testComparator(){
        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        // 1.2 使用 lambda expression 排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(players, sortByName);
        System.out.println(new Gson().toJson(players));

        // 1.3 也可以采用如下形式:
        Arrays.sort(players, (String s1, String s2) -> (s2.compareTo(s1)));
        System.out.println(new Gson().toJson(players));

    }

    @Test
    public void testPredicate(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "ppla");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.startsWith("J"));

        System.out.println("Languages which ends with a and contain l");
        Predicate<String> condition1 = (str)->str.endsWith("a");
        Predicate<String> condition2 = (str)->str.contains("l");
        filter(languages, condition1.and(condition2));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.length() > 4);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    private void filter(List<String> names, Predicate<String> condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
}
