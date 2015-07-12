package hw.learn.simple.net;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * URL类用法示例：输出html页面源码；参见URLConnectionDemo
 */
public class URLDemo {  
    public static void main(String[] args) {  
        try {  
            URL url = new URL("http://www.baidu.com");  
            //openStream()是对连接进行了一些封装：先通过openConnection()方法获取URLConnection对象，然后调用getInputStream()方法 
            Scanner in = new Scanner(url.openStream());  
            while (in.hasNextLine())//如果还有下一行，则输出下一行
                System.out.println(in.nextLine());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
} 