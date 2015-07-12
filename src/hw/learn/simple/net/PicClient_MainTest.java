package hw.learn.simple.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class  PicClient_MainTest{
    public static void main(String[] args)throws Exception {
        if(args.length!=1){//main的参数是一个，文件个数限制
            System.out.println("请选择一个jpg格式的图片");
            return ;//程序结束
        }
        File file = new File(args[0]);  
        if(!(file.exists() && file.isFile())){
            System.out.println("该文件有问题，要么补存在，要么不是文件");
            return ;
        }
        if(!file.getName().endsWith(".jpg")){//格式限制
            System.out.println("图片格式错误,请重新选择");
            return ;
        }
        if(file.length()>1024*1024*5){//文件大小限制
            System.out.println("文件过大，没安好心");
            return ;
        }
        Socket s = new Socket("192.168.1.254",10007);
        FileInputStream fis = new FileInputStream(file);
        OutputStream out = s.getOutputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while((len=fis.read(buf))!=-1){
            out.write(buf,0,len);
        }
        s.shutdownOutput();//告诉服务端数据已写完
        InputStream in = s.getInputStream();
        byte[] bufIn = new byte[1024];
        int num = in.read(bufIn);
        System.out.println(new String(bufIn,0,num));
        fis.close();
        s.close();
    }
}
