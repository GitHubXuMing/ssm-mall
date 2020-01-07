package com.ssm.mall.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Person{
    private int pid;
    private String pname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return pid == person.pid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }

    @Override
    public String toString() {
        return "Person{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                '}';
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Person() {
    }

    public Person(int pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }
}

public class RecursionTest {
    public static List<Person> persons = Lists.newArrayList();
    static{
        persons.add(new Person(1001,"ZHAO"));
        persons.add(new Person(1002,"QIAN"));
        persons.add(new Person(1003,"SUN"));
        persons.add(new Person(1004,"LI"));
        //pid相同，pname不同
        persons.add(new Person(1002,"qian"));
        persons.add(new Person(1004,"li"));
    }

    @Test
    public void testMakeSet(){
        Set<Person> personSet = Sets.newHashSet();
        for(Person p:persons){
            personSet.add(p);
        }
        personSet.forEach(System.out::println);
        /*运行结果：
        Person{pid=1001, pname='ZHAO'}
        Person{pid=1002, pname='QIAN'}
        Person{pid=1003, pname='SUN'}
        Person{pid=1004, pname='LI'}
         */
    }
    //设计递归结构，获取PersonList中的节点，并去重
    private Set<Person> useRecursionSet(Person person,Set<Person> personSet){
        personSet.add(person);
        return personSet;
    }

    @Test
    public void testRecursion(){
        Set<Person> personSet = Sets.newHashSet();
        for (Person person : persons) {
            useRecursionSet(person,personSet);
        }
        personSet.forEach(System.out::println);
    }
}
