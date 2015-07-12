package hw.learn.simple.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * Java获得某个关键词在百度搜索中的推荐内容：http://www.blogjava.net/marchalex/archive/2015/03/07/423268.aspx
 */
public class BaiduSearch_00_FindRelate {
    
    public static String[] getRelate(String word) throws Exception {
        String urlString = "http://www.baidu.com/s?wd=" + word;
        String ans = "";
        String s_span = "<span title=";
        int len_span = s_span.length();
        String s_rsv = "rsv_re_ename";
        int len_rsv = s_rsv.length();
        
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
        String line;
        boolean ok = false;
        while ((line = reader.readLine()) != null){
            int len = line.length();
             for(int i=0;i+len_span<=len;i++) {
                 if(line.substring(i, i+len_span).equals(s_span)) {
                     if(ok == false) ok = true;
                     else {
                         StringTokenizer st = new StringTokenizer(ans);
                         int n = st.countTokens();
                         String[] res = new String[n];
                         for(int j=0;j<n;j++) {
                             res[j] = st.nextToken();
                         }
                         return res;
                     }
                 }
             }
             if(ok == false) continue;
             for(int i=0;i+len_rsv<=len;i++) {
                 if(line.substring(i, i+len_rsv).equals(s_rsv)) {
                     for(int j=i+len_rsv+3;j<len && line.charAt(j)!='\'';j++) {
                         ans += line.charAt(j);
                     }
                     ans += " ";
                 }
             }
        }
        String[] null_res = new String[1];
        null_res[0] = null;
        return null_res;
    }
    public static void main(String[] args) throws Exception {
        String[] res = getRelate("中华");
        int len = res.length;
        for(int i=0;i<len;i++)
            System.out.println(res[i]);
    }
}