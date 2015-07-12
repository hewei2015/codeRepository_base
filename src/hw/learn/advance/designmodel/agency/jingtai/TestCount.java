package hw.learn.advance.designmodel.agency.jingtai;   

/**  
 *测试Count类  
 */   
public class TestCount {   
    public static void main(String[] args) {   
        CountImpl countImpl = new CountImpl();   
        CountProxy countProxy = new CountProxy(countImpl); 
        
        countProxy.updateCount();   
        countProxy.queryCount();   
    }   
}  