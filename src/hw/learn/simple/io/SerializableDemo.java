package hw.learn.simple.io;
import java.io.*;
/**
 * 实现具有序列化能力的类
 * */
public class SerializableDemo implements Serializable{
    public SerializableDemo(){
         
    }
    public SerializableDemo(String name, int age){
        this.name=name;
        this.age=age;
    }
    @Override
    public String toString(){
        return "姓名："+name+"  年龄："+age;
    }
    private String name;
    private int age;
}