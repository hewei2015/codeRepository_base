网络编程部分:

1. URL请求的类别
	分为二类,GET与POST请求。二者的区别在于： 
	a:) get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet
	b:) post与get的不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。 

2. 客户端注意
	(1) HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求
	        无论是post还是get，http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去。

	(2) 在用POST方式发送URL请求时，URL请求参数的设定顺序是重中之重， 对connection对象的一切配置（那一堆set函数） 
    	都必须要在connect()函数执行之前完成。 connect()函数会根据HttpURLConnection对象的配置值生成http头部信息，
    	因此在调用connect函数之前， 就必须把所有的配置准备好。 而对outputStream的写操作，又必须要在inputStream的读操作之前。 
    	这些顺序实际上是由http请求的格式决定的。如果inputStream读操作在outputStream的写操作之前，会抛出异常： 
    	java.net.ProtocolException: Cannot write output after reading input.......
    	 
    (3) 在http头后面紧跟着的是http请求的正文，正文的内容是通过outputStream流写入的， 
    	实际上outputStream不是一个网络流，充其量是个字符串流，往里面写入的东西不会立即发送到网络， 
    	而是存在于内存缓冲区中，待outputStream流关闭时，根据输入的内容生成http正文。 
    	至此，http请求的东西已经全部准备就绪。在getInputStream()函数调用的时候，就会把准备好的http请求 
    	正式发送到服务器了，然后返回一个输入流，用于读取服务器对于此次http请求的返回信息
    
    (4) HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现。如果不设置超时（timeout），在网络异常的情况下，可能会导致程序僵死而不继续往下执行。可以通过以下两个语句来设置相应的超时：
		System.setProperty("sun.net.client.defaultConnectTimeout", 超时毫秒数字符串);
		System.setProperty("sun.net.client.defaultReadTimeout", 超时毫秒数字符串);
		    
3. 服务端Servlet注意
	(1) 对于客户端发送的POST类型的HTTP请求，Servlet必须实现doPost方法，而不能用doGet方法
	(2) 用HttpServletRequest的getInputStream()方法取得InputStream的对象
    	