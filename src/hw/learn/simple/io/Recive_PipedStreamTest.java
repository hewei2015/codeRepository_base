package hw.learn.simple.io;

import java.io.PipedInputStream;

/**
 * 验证管道流：接受消息类
 * */
class Recive_PipedStreamTest implements Runnable{
    private PipedInputStream input=null;
    public Recive_PipedStreamTest(){
        this.input=new PipedInputStream();
    }
    public PipedInputStream getInput(){
        return this.input;
    }
    public void run(){
        byte[] b=new byte[1000];
        int len=0;
        try{
            len=this.input.read(b);
        }catch (Exception e) {
            e.printStackTrace();
        }try{
            input.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("接受的内容为 "+(new String(b,0,len)));
    }
}