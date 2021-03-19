package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 页面重定向 {
//当文档移动到一个新的位置时，通常会使用页面重定向，
// 我们需要将客户端发送到这个新位置或者也可能是由于负载均衡，或者只是为了简单的随机。

//重定向请求到另一个页面的最简单的方式是使用 response 对象的 sendRedirect() 方法。
// 下面是该方法的特征：

//public void HttpServletResponse.sendRedirect(String location)
//throws IOException
//该方法将响应和状态码及新的页面位置发送回浏览器。
// 你也可以通过一起使用 setStatus() 和 setHeader() 方法来达到同样的效果：

//....
//String site = "http://www.newpage.com" ;
//response.setStatus(response.SC_MOVED_TEMPORARILY);
//response.setHeader("Location", site);
//....
//实例
//这个例子显示了 servlet 如何将页面重定向到另一个位置：
//
//import java.io.*;
//import java.sql.Date;
//import java.utils.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//public class PageRedirect extends HttpServlet{
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      // New location to be redirected
//      String site = new String("http://www.photofuntoos.com");
//      response.setStatus(response.SC_MOVED_TEMPORARILY);
//      response.setHeader("Location", site);
//    }
//}
//现在让我们来编译述 servlet 并在 web.xml 文件中创建以下条目：

//....
// <servlet>
//     <servlet-name>PageRedirect</servlet-name>
//     <servlet-class>PageRedirect</servlet-class>
// </servlet>
// <servlet-mapping>
//     <servlet-name>PageRedirect</servlet-name>
//     <url-pattern>/PageRedirect</url-pattern>
// </servlet-mapping>
//....
//现在使用 URL http://localhost:8080/PageRedirect 来调用这个 servlet。
// 这将使你跳转到给定的 URL http://www.photofuntoos.com 中。
}
