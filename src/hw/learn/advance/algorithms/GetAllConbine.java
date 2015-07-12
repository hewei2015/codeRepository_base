package hw.learn.advance.algorithms;
import java.util.ArrayList;   
import java.util.List;   
  public class GetAllConbine {   
      public static void main(String[] args) {   
        String s="ABCD";//原字符串   
        List<String> result = list(s, "");//static的list方法，所以在main方法中可以直接调用 
        System.out.println(result.size());;   
        System.out.println(result);   
    }   
    /**
     * ★要求：列出基础字符串(base)的所有组合 （用递归的方法）
     * @param base 以该字符串作为基础字符串，进行选择性组合。  
     * @param buff 所求字符串的临时结果  
     * @param result 存放所求结果  
     * 方法解析：
     * StringBuffer.deleteCharAt(i):删除这个序列中的指定位置的字符
     * String.charAt(i)
     */  
    public static List<String> list(String base,String buff){   
        List<String> result = new ArrayList<String>();//存放结果信息。   
        if(base.length()<=0){   
            result.add(buff);   
        }
        for(int i=0;i<base.length();i++){   
            List<String> temp = list(new StringBuilder(base).deleteCharAt(i).toString(),buff+base.charAt(i));   
            result.addAll(temp);   
        }   
        return result;   
    }   
}
