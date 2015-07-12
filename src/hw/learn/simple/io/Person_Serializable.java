package hw.learn.simple.io;
import java.io.Serializable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
 
/**
 * 实现具有序列化能力的类
 * */
public class Person_Serializable implements Serializable{
    public Person_Serializable(){
 
    }
 
    public Person_Serializable(String name, int age){
        this.name = name;
        this.age = age;
    }
 
    @Override
    public String toString(){
        return "姓名：" + name + "  年龄：" + age;
    }
 
    private String name;
    private int age;
}
