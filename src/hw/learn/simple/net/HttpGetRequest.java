package hw.learn.simple.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 给定地址，进行get方式传递数据测试
 */
public class HttpGetRequest {
    public static void main(String[] args) throws Exception {
        System.out.println(doGet());
    }
    public static String doGet() throws Exception {
    	// URL localURL = new URL("http://www.baidu.com/");//可以成功
    	URL localURL = new URL("http://19.106.0.141:8101/Authentication/GatewayServlet?xmlstr=PENPU1BOPjxIRUFEPjxCVVNJVFlQRT4wMDAwMjwvQlVTSVRZUEU+PC9IRUFEPjxRVUVTVD48VElNRVNUQU1QPjIwMTUwNDI3MTYyNTM5PC9USU1FU1RBTVA+PElETlVNQkVSPjQ0MDMwNjIwMTMwNjI4OTk5OTwvSUROVU1CRVI+PE5BTUU+5byg5LiJPC9OQU1FPjxET0NTTj4xMjM0NTY3ODkwMTIzNDU2PC9ET0NTTj48U1RSQ0lMRU5UU0lHTkVEREFUQT5udWxsPC9TVFJDSUxFTlRTSUdORUREQVRBPjxDRVJURU5USVRZPk1JSUNqekNDQWZpZ0F3SUJBZ0lFQk1wYUREQU5CZ2txaGtpRzl3MEJBUVFGQURCSE1SWXdGQVlEVlFRREV3MTZhR0Z1WjNsaGJpQjBaWE4wTVE4d0RRWURWUVFLRXdaMFpYTjBJRzh4RHpBTkJnTlZCQXNUQmxSbGMzUlBkVEVMTUFrR0ExVUVCaE1DUTA0d0hoY05NVFF3TVRBNE1qTTBOVEV6V2hjTk1Ua3dNVEE1TURNME5URXpXakJCTVJJd0VBWURWUVFERXdsS01EQXhNamN3TVRNeEVEQU9CZ05WQkFzVEIwNUZWRUpCVGtzeEREQUtCZ05WQkFvVEEwTkRRakVMTUFrR0ExVUVCaE1DUTA0d2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFOMmdsZU1neURLSHFxVVA4a1FJOTF1cTNrR0VjNDBTM2d5VkFWVE9NVThBRGRpMzBNaW15V3RteEsvK1VEWDVVNDkxN2lla2RRMUxVbGJnVEV6bjkxZ0xkc2dCZU5rVnpwL1RTU2hsOG1YREsvaTRCTGo2RTJ4cTFac1h1ZWRxUGNSc3J6SHpmVVZrOXlOakk0cXVaZi9aQkkvMUFpcE9wRnQ4MTlyVlZ6cVBBZ01CQUFHamdZMHdnWW93RVFZSllJWklBWWI0UWdFQkJBUURBZ1dnTUI4R0ExVWRJd1FZTUJhQUZGYWNUbTBwaTNJMWhjYm8wV0FzMG5yVmN6QnpNQWtHQTFVZEV3UUNNQUF3Q3dZRFZSMFBCQVFEQWdiQU1CMEdBMVVkRGdRV0JCUWNxQXRrbDk0U2lMK2pSUnMrdFhINllMWTJhakFkQmdOVkhTVUVGakFVQmdnckJnRUZCUWNEQWdZSUt3WUJCUVVIQXdRd0RRWUpLb1pJaHZjTkFRRUVCUUFEZ1lFQUppbXNIUFN0WkJUOXdQV1NHeG9SUWlFWVhOTGQ0T0YveUZVRmFMQUwxNlN5VEU5TVNTK1pBcDNNeDg4Z2ZRcVdSbXZSVStOMGd1VVlyTDlvbHErejBYdjhYNHRZcThWNXhUU1VqMzYreE9Yb2ZrbFBiZ2xFQ2c3WmRMYkFOYTQvMktPQm9FWU1jR2ZXNFh0YmwyVE82Ry9ENGF2MzNiKzBBQVVpYTh0eDE1dz08L0NFUlRFTlRJVFk+PC9RVUVTVD48L0NPU1BOPg==");
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
        
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }
        
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return resultBuffer.toString();
    }
}