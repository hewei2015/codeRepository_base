package hw.learn.simple.io;

import java.io.*;
 
/**
 * 验证管道流：消息发送类
 * */
class Send_PipedStreamTest implements Runnable{
    private PipedOutputStream out=null;
    public Send_PipedStreamTest() {
        out=new PipedOutputStream();
    }
    public PipedOutputStream getOut(){
        return this.out;
    }
    public void run(){
        String message="hello , Rollen";
        try{
            out.write(message.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }try{
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}