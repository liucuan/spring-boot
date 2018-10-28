package com.tone.batch;

import javax.validation.constraints.Size;

/**
 * @author zhaoxiang.liu
 * @date 2018/8/28
 */
public class Person {

    @Size(max = 20, min = 2)
    private String name;
    private int age;
    private String nation;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
