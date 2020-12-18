package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 客户端HTTP请求 {
//当浏览器请求网页时，它会向 web 服务器发送大量信息，这些信息不能被直接读取，因为这些信息是作为 HTTP 请求头的一部分行进的。

//以下是来自浏览器端的重要的头信息，你会在 web 编程中频繁的使用：

//头信息	                    描述
//Accept	            这个头信息指定浏览器或其他客户端可以处理的 MIME 类型。值 image/png 或 image/jpeg 是最常见的两种可能值。
//Accept-Charset	    这个头信息指定浏览器可以用来显示信息的字符集。例如 ISO-8859-1。
//Accept-Encoding	    这个头信息指定浏览器知道如何处理的编码类型。值 gzip 或 compress 是最常见的两种可能值。
//Accept-Language	    这个头信息指定客户端的首选语言，在这种情况下，Servlet 会产生多种语言的结果。例如，en、en-us、ru 等。
//Authorization	        这个头信息用于客户端在访问受密码保护的网页时识别自己的身份。
//Connection	        这个头信息指示客户端是否可以处理持久 HTTP 连接。持久连接允许客户端或其他浏览器通过单个请求来检索多个文件。值 Keep-Alive 意味着使用了持续连接。
//Content-Length	    这个头信息只适用于 POST 请求，并给出 POST 数据的大小（以字节为单位）。
//Cookie	            这个头信息把之前发送到浏览器的 cookies 返回到服务器。
//Host	                这个头信息指定原始的 URL 中的主机和端口。
//If-Modified-Since	    这个头信息表示只有当页面在指定的日期后已更改时，客户端想要的页面。如果没有新的结果可以使用，服务器会发送一个 304 代码，表示 Not Modified 头信息。
//If-Unmodified-Since	这个头信息是 If-Modified-Since 的对立面，它指定只有当文档早于指定日期时，操作才会成功。
//Referer	            这个头信息指示所指向的 Web 页的 URL。例如，如果您在网页 1，点击一个链接到网页 2，当浏览器请求网页 2 时，网页 1 的 URL 就会包含在 Referer 头信息中。
//User-Agent	        这个头信息识别发出请求的浏览器或其他客户端，并可以向不同类型的浏览器返回不同的内容。

//读取 HTTP 头信息的方法
//下述方法可以用于读取 servlet 程序中的 HTTP 头信息。通过 HttpServletRequest 对象这些方法是可用的。

//序号	方法 & 描述

//1
//Cookie[] getCookies()
//返回一个数组，包含客户端发送该请求的所有的 Cookie 对象。

//2
//Enumeration getAttributeNames()
//返回一个枚举，包含提供给该请求可用的属性名称。

//3
//Enumeration getHeaderNames()
//返回一个枚举，包含在该请求中包含的所有的头名。

//4
//Enumeration getParameterNames()
//返回一个 String 对象的枚举，包含在该请求中包含的参数的名称。

//5
//HttpSession getSession()
//返回与该请求关联的当前 session 会话，或者如果请求没有 session 会话，则创建一个。

//6
//HttpSession getSession(boolean create)
//返回与该请求关联的当前 HttpSession，或者如果没有当前会话，且创建是真的，则返回一个新的 session 会话。

//7
//Locale getLocale()
//基于 Accept-Language 头，返回客户端接受内容的首选的区域设置。

//8
//Object getAttribute(String name)
//以对象形式返回已命名属性的值，如果没有给定名称的属性存在，则返回 null。

//9
//ServletInputStream getInputStream()
//使用 ServletInputStream，以二进制数据形式检索请求的主体。

//10
//String getAuthType()
//返回用于保护 Servlet 的身份验证方案的名称，例如，“BASIC” 或 “SSL”，如果JSP没有受到保护则返回 null。

//11
//String getCharacterEncoding()
//返回请求主体中使用的字符编码的名称。

//12
//String getContentType()
//返回请求主体的 MIME 类型，如果不知道类型则返回 null。

//13
//String getContextPath()
//返回指示请求上下文的请求 URI 部分。

//14
//String getHeader(String name)
//以字符串形式返回指定的请求头的值。

//15
//String getMethod()
//返回请求的 HTTP 方法的名称，例如，GET、POST 或 PUT。

//16
//String getParameter(String name)
//以字符串形式返回请求参数的值，或者如果参数不存在则返回 null。

//17
//String getPathInfo()
//当请求发出时，返回与客户端发送的 URL 相关的任何额外的路径信息。

//18
//String getProtocol()
//返回请求协议的名称和版本。

//19
//String getQueryString()
//返回包含在路径后的请求 URL 中的查询字符串。

//20
//String getRemoteAddr()
//返回发送请求的客户端的互联网协议（IP）地址。

//21
//String getRemoteHost()
//返回发送请求的客户端的完全限定名称。

//22
//String getRemoteUser()
//如果用户已通过身份验证，则返回发出请求的登录用户，或者如果用户未通过身份验证，则返回 null。

//23
//String getRequestURI()
//从协议名称直到 HTTP 请求的第一行的查询字符串中，返回该请求的 URL 的一部分。

//24
//String getRequestedSessionId()
//返回由客户端指定的 session 会话 ID。

//25
//String getServletPath()
//返回调用 JSP 的请求的 URL 的一部分。

//26
//String[] getParameterValues(String name)
//返回一个字符串对象的数组，包含所有给定的请求参数的值，如果参数不存在则返回 null。

//27
//boolean isSecure()
//返回一个布尔值，指示请求是否使用安全通道，如 HTTPS。

//28
//int getContentLength()
//以字节为单位返回请求主体的长度，并提供输入流，或者如果长度未知则返回 -1。

//29
//int getIntHeader(String name)
//返回指定的请求头的值为一个 int 值。

//30
//int getServerPort()
//返回接收到这个请求的端口号。

//HTTP 头信息请求实例：
//下述例子使用了 HttpServletRequest 的 getHeaderNames() 方法来读取 HTTP 头信息。
// 该方法返回了一个枚举，包含与当前的 HTTP 请求相关的头信息。

//一旦我们得到一个枚举，我们可以以标准方式循环这个枚举，使用 hasMoreElements() 方法来确定何时停止循环，
// 使用 nextElement() 方法来获取每个参数的名称。

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.utils.*;
//// Extend HttpServlet class
//public class DisplayHeader extends HttpServlet {
//  // Method to handle GET method request.
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "HTTP Header Request Example";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//        "<html>\n" +
//        "<head><title>" + title + "</title></head>\n"+
//        "<body bgcolor=\"#f0f0f0\">\n" +
//        "<h1 align=\"center\">" + title + "</h1>\n" +
//        "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
//        "<tr bgcolor=\"#949494\">\n" +
//        "<th>Header Name</th><th>Header Value(s)</th>\n"+
//        "</tr>\n");
//      Enumeration headerNames = request.getHeaderNames();
//      while(headerNames.hasMoreElements()) {
//         String paramName = (String)headerNames.nextElement();
//         out.print("<tr><td>" + paramName + "</td>\n");
//         String paramValue = request.getHeader(paramName);
//         out.println("<td> " + paramValue + "</td></tr>\n");
//      }
//      out.println("</table>\n</body></html>");
//  }
//  // Method to handle POST method request.
//  public void doPost(HttpServletRequest request,
//                     HttpServletResponse response)
//      throws ServletException, IOException {
//     doGet(request, response);
//  }
//}
//现在，调用上述 servlet 会产生如下所示的结果：

//HTTP Header Request Example
}
