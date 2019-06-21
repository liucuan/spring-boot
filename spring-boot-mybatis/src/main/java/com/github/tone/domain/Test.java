package com.github.tone.domain;

/**
 * @author zhaoxiang.liu
 * @date 2018/9/24
 */
public class Test {

    public static void main(String[] args) {
        User user = User.builder().id(1L).age(2).name("hha ").build();
        System.out.println(user);
    }
}
