package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 服务器HTTP响应 {
//正如在前面的章节中讨论的一样，当一个 Web 服务器对浏览器响应一个 HTTP 请求时，
// 响应通常包括一个状态行、一些响应头信息、一个空行和文档。一个典型的响应如下所示：

//HTTP/1.1 200 OK
//Content-Type: text/html
//Header2: ...
//...
//HeaderN: ...
//  (Blank Line)
//<!doctype ...>
//<html>
//<head>...</head>
//<body>
//...
//</body>
//</html>


//状态行包括 HTTP 版本（例子中的 HTTP/1.1）、一个状态码（例子中的 200）和一个对应于状态码的短消息（例子中的 OK）。

//下面是从 web 服务器端返回到浏览器的最有用的 HTTP 1.1 响应头信息的总结，且在 web 编程中你会频繁地使用它们：

//头信息	                    描述
//Allow	                这个头信息指定服务器支持的请求方法（GET、POST 等）。
//Cache-Control	        这个头信息指定响应文档在何种情况下可以安全地缓存。可能的值有：public, private 或 no-cache 等。Public 意味着文档是可缓存，Private 意味着文档是单个用户私用文档，且只能存储在私有（非共享）缓存中，no-cache 意味着文档不应被缓存。
//Connection	        这个头信息指示浏览器是否使用持久 HTTP 连接。值 close 指示浏览器不使用持久 HTTP 连接，值 keep-alive 意味着使用持久连接。
//Content-Disposition	这个头信息可以让您请求浏览器要求用户以给定名称的文件把响应保存到磁盘。
//Content-Encoding	    在传输过程中，这个头信息指定页面的编码方式。
//Content-Language	    这个头信息表示文档编写所使用的语言。例如，en、en-us、ru 等。
//Content-Length	    这个头信息指示响应中的字节数。只有当浏览器使用持久（keep-alive）HTTP 连接时才需要这些信息。
//Content-Type	        这个头信息提供了响应文档的 MIME（Multipurpose Internet Mail Extension）类型。
//Expires	            这个头信息指定内容过期的时间，在这之后内容不再被缓存。
//Last-Modified	        这个头信息指示文档的最后修改时间。然后，客户端可以缓存文件，并在以后的请求中通过 If-Modified-Since 请求头信息提供一个日期。
//Location	            这个头信息应被包含在所有的带有状态码的响应中。在 300 s 内，这会通知浏览器文档的地址。浏览器会自动重新连接到这个位置，并获取新的文档。
//Refresh	            这个头信息指定浏览器应该如何尽快请求更新的页面。您可以指定页面刷新的秒数。
//Retry-After	        这个头信息可以与 503（服务不可用）响应配合使用，这会告诉客户端多久就可以重复它的请求。
//Set-Cookie	        这个头信息指定一个与页面关联的 cookie。


//设置 HTTP 响应头信息的方法
//下面的方法可用于在 servlet 程序中设置 HTTP 响应头信息。通过 HttpServletResponse 对象这些方法是可用的。

//序号	    方法 & 描述
//1	String encodeRedirectURL(String url)
//为 sendRedirect 方法中使用的指定的 URL 进行编码，或者如果编码不是必需的，则返回 URL 未改变。

//2	String encodeURL(String url)
//对包含 session 会话 ID 的指定 URL 进行编码，或者如果编码不是必需的，则返回 URL 未改变。

//3	boolean containsHeader(String name)
//返回一个布尔值，指示是否已经设置已命名的响应头信息。

//4	boolean isCommitted()
//返回一个布尔值，指示响应是否已经提交。

//5	void addCookie(Cookie cookie)
//把指定的 cookie 添加到响应。

//6	void addDateHeader(String name, long date)
//添加一个带有给定的名称和日期值的响应头信息。

//7	void addHeader(String name, String value)
//添加一个带有给定的名称和值的响应头信息。

//8	void addIntHeader(String name, int value)
//添加一个带有给定的名称和整数值的响应头信息。

//9	void flushBuffer()
//强制任何在缓冲区中的内容被写入到客户端。

//10	void reset()
//清除缓冲区中存在的任何数据，包括状态码和头信息。

//11	void resetBuffer()
//清除响应中基础缓冲区的内容，不清除状态码和头信息。

//12	void sendError(int sc)
//使用指定的状态码发送错误响应到客户端，并清除缓冲区。

//13	void sendError(int sc, String msg)
//使用指定的状态发送错误响应到客户端。

//14	void sendRedirect(String location)
//使用指定的重定向位置 URL 发送临时重定向响应到客户端。

//15	void setBufferSize(int size)
//为响应主体设置首选的缓冲区大小。

//16	void setCharacterEncoding(String charset)
//设置被发送到客户端的响应的字符编码（MIME 字符集）例如，UTF-8。

//17	void setContentLength(int len)
//设置在 HTTP Servlet 响应中的内容主体的长度，该方法设置 HTTP Content-Length 头信息。

//18	void setContentType(String type)
//如果响应还未被提交，设置被发送到客户端的响应的内容类型。

//19	void setDateHeader(String name, long date)
//设置一个带有给定的名称和日期值的响应头信息。

//20	void setHeader(String name, String value)
//设置一个带有给定的名称和值的响应头信息。

//21	void setIntHeader(String name, int value)
//设置一个带有给定的名称和整数值的响应头信息。

//22	void setLocale(Locale loc)
//如果响应还未被提交，设置响应的区域。

//23	void setStatus(int sc)
//为该响应设置状态码。

//HTTP 头信息响应实例
//在前面的实例中你已经了解了 setContentType() 方法的工作方式，下面的实例也会用到同样的方法，
// 此外，我们会用 setIntHeader() 方法来设置 Refresh 头信息。
//
//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.utils.*;
//// Extend HttpServlet class
//public class Refresh extends HttpServlet {
//  // Method to handle GET method request.
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set refresh, autoload time as 5 seconds
//      response.setIntHeader("Refresh", 5);
//      // Set response content type
//      response.setContentType("text/html");
//      // Get current time
//      Calendar calendar = new GregorianCalendar();
//      String am_pm;
//      int hour = calendar.get(Calendar.HOUR);
//      int minute = calendar.get(Calendar.MINUTE);
//      int second = calendar.get(Calendar.SECOND);
//      if(calendar.get(Calendar.AM_PM) == 0)
//        am_pm = "AM";
//      else
//        am_pm = "PM";
//      String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
//      PrintWriter out = response.getWriter();
//      String title = "Auto Refresh Header Setting";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//        "<html>\n" +
//        "<head><title>" + title + "</title></head>\n"+
//        "<body bgcolor=\"#f0f0f0\">\n" +
//        "<h1 align=\"center\">" + title + "</h1>\n" +
//        "<p>Current Time is: " + CT + "</p>\n");
//  }
//  // Method to handle POST method request.
//  public void doPost(HttpServletRequest request,
//                     HttpServletResponse response)
//      throws ServletException, IOException {
//     doGet(request, response);
//  }
//}
//现在调用上述 servlet，每 5 秒后就会显示当前的系统时间，如下所示。运行 servlet 并等着看结果：

//Auto Refresh Header Setting

//Current Time is: 9:44:50 PM
}
