package hw.learn.simple.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class PicThread_MainTest implements Runnable{//所有的服务器都是这个原理封装的
    private Socket s;
    PicThread_MainTest(Socket s){
        this.s = s;
    }
    public void run(){
        int count = 1;
        String ip  = s.getInetAddress().getHostAddress();
        try{
            System.out.println(ip+"....connected");
            InputStream in = s.getInputStream();
            File dir =  new File("d:\\pic");
            File file = new File(dir,ip+"("+(count)+")"+".jpg");//ip地址+次数命名防覆盖
            while(file.exists())
                file = new File(dir,ip+"("+(count++)+")"+".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = 0;
            while((len=in.read(buf))!=-1){
                fos.write(buf,0,len);
            }
            OutputStream out = s.getOutputStream();
            out.write("上传成功".getBytes());
            fos.close();
            s.close();
        }
        catch (Exception e){
            throw new RuntimeException(ip+"上传失败");
        }
    }
}
class  PicServer{
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(10007);
        while(true){
            Socket s = ss.accept();//▲拿到客户端，如果没有信息，主线程在等待。
            new Thread(new PicThread_MainTest(s)).start();//★建立一个线程
        }
        //ss.close();
    }
}
