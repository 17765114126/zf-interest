package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class Cookies处理 {
//Cookies 是存储在客户端计算机上的文本文件，用于各种信息的跟踪目的。Java Servlet 透明的支持 HTTP Cookies。

//识别返回用户包括以下三个步骤：

//服务器脚本向浏览器发送一组 cookies。例如姓名、年龄或身份证号码等。

//浏览器将这些信息存储在本地计算机中以备将来使用。

//当下次浏览器向 web 服务器发送任何请求时，它会把这些 cookies 信息发送到服务器，服务器使用这些信息来识别用户。
//本章教你如何设置或重置 cookies，如何访问它们，以及如何删除它们。

//Cookie 剖析
//通常情况下，Cookies 设置在 HTTP 头信息中（尽管 JavaScript 也可以直接在浏览器上设置 cookie）。设置 cookie 的 servlet 可能会发送如下所示的头信息：

//HTTP/1.1 200 OK
//Date: Fri, 04 Feb 2000 21:03:38 GMT
//Server: Apache/1.3.9 (UNIX) PHP/4.0b3
//Set-Cookie: name=xyz; expires=Friday, 04-Feb-07 22:03:38 GMT;
//                 path=/; domain=tutorialspoint.com
//Connection: close
//Content-Type: text/html
//正如你所看到的，Set-Cookie 头信息包含了一个名称值对、一个 GMT 日期、一个路径和一个域。名称和值会被 URL 编码。有效期字段指示浏览器在给定的时间和日期之后“忘记”该 cookie。

//如果浏览器被配置为存储 cookies，它将会把这个信息保留到截止日期。如果用户在任何与该 cookie 的路径和域匹配的页面点击浏览器，它就会将这个 cookie 重新发送到服务器。浏览器的头信息可能如下所示：

//GET / HTTP/1.0
//Connection: Keep-Alive
//User-Agent: Mozilla/4.6 (X11; I; Linux 2.2.6-15apmac ppc)
//Host: zink.demon.co.uk:1126
//Accept: image/gif, */*
//Accept-Encoding: gzip
//Accept-Language: en
//Accept-Charset: iso-8859-1,*,utf-8
//Cookie: name=xyz
//之后 servlet 就能够通过请求方法 request.getCookies() 访问 cookie，该方法将返回一个 Cookie 对象的数组。

//Servlet Cookies 方法
//以下是在 servlet 中操作 cookies 时你可能会用到的有用的方法列表。

//序号	方法 & 描述
//1
//public void setDomain(String pattern)

//该方法设置 cookie 适用的域，例如 tutorialspoint.com。

//2
//public String getDomain()

//该方法获取 cookie 适用的域，例如 tutorialspoint.com。

//3
//public void setMaxAge(int expiry)

//该方法设置 cookie 过期的时间（以秒为单位）。如果不这样设置，cookie 只会在当前 session 会话中持续有效。

//4
//public int getMaxAge()

//该方法返回 cookie 的最大生存周期（以秒为单位），默认情况下，-1 表示 cookie 将持续下去，直到浏览器关闭。

//5
//public String getName()

//该方法返回 cookie 的名称。名称在创建后不能改变。

//6
//public void setValue(String newValue)

//该方法设置与 cookie 关联的值。

//7
//public String getValue()

//该方法获取与 cookie 关联的值。

//8
//public void setPath(String uri)

//该方法设置 cookie 适用的路径。如果您不指定路径，与当前页面相同目录下的（包括子目录下的）所有 URL 都会返回 cookie。

//9
//public String getPath()

//该方法获取 cookie 适用的路径。

//10
//public void setSecure(boolean flag)

//该方法设置布尔值，表示 cookie 是否应该只在加密的（即 SSL）连接上发送。
//
//11
//public void setComment(String purpose)

//该方法规定了描述 cookie 目的的注释。该注释在浏览器向用户呈现 cookie 时非常有用。

//12
//public String getComment()

//该方法返回了描述 cookie 目的的注释，如果 cookie 没有注释则返回 null。

//用 Servlet 设置 Cookies
//用 servlet 设置 cookies 包括三个步骤：

//(1) 创建一个 Cookie 对象：用 cookie 名和 cookie 值调用 Cookie 构造函数，cookie 名和 cookie 值都是字符串。

//Cookie cookie = new Cookie("key","value");
//记住，无论是名字还是值，都不应该包含空格和以下任何字符：

//[ ] ( ) = , " / ? @ : ;
//(2) 设置最长有效期：你可以使用 setMaxAge 方法来指定 cookie 有效的时间（以秒为单位）。下面是设置了一个最长有效期为 24 小时的 cookie。

//cookie.setMaxAge(60*60*24);
//(3) 发送 Cookie 到 HTTP 响应头：你可以使用 response.addCookie 来在 HTTP 响应头中添加 cookies，如下所示：

//response.addCookie(cookie);
//实例：
//让我们修改我们的 表单实例 来为姓名设置 cookies。

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//// Extend HttpServlet class
//public class HelloForm extends HttpServlet {
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Create cookies for first and last names.
//      Cookie firstName = new Cookie("first_name",
//                      request.getParameter("first_name"));
//      Cookie lastName = new Cookie("last_name",
//                      request.getParameter("last_name"));
//      // Set expiry date after 24 Hrs for both the cookies.
//      firstName.setMaxAge(60*60*24);
//      lastName.setMaxAge(60*60*24);
//      // Add both the cookies in the response header.
//      response.addCookie( firstName );
//      response.addCookie( lastName );
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Setting Cookies Example";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//                "<html>\n" +
//                "<head><title>" + title + "</title></head>\n" +
//                "<body bgcolor=\"#f0f0f0\">\n" +
//                "<h1 align=\"center\">" + title + "</h1>\n" +
//                "<ul>\n" +
//                "  <li><b>First Name</b>: "
//                + request.getParameter("first_name") + "\n" +
//                "  <li><b>Last Name</b>: "
//                + request.getParameter("last_name") + "\n" +
//                "</ul>\n" +
//                "</body></html>");
//  }
//}
//编译上述 servlet HelloForm 并在 web.xml 文件中创建适当的条目，最后尝试使用下述 HTML 页面来调用 servlet。


//<html>
//<body>
//<form action="HelloForm" method="GET">
//First Name: <input type="text" name="first_name">
//<br />
//Last Name: <input type="text" name="last_name" />
//<input type="submit" value="Submit" />
//</form>
//</body>
//</html>
//将上述 HTML 内容保存到文件 hello.htm 中并把它放在 <Tomcat-installation-directory>/webapps/ROOT 目录中。当你访问 http://localhost:8080/Hello.htm 时，上述表单的实际输出如下所示：

//First Name:

//Last Name:
//尝试输入姓名，然后点击提交按钮。这将在你的屏幕上显示姓名，同时会设置 firstName 和 lastName 这两个 cookies，当下次你点击提交按钮时，会将这两个 cookies 传回到服务器。

//下一节会解释如何在你的 web 应用程序中访问这些 cookies。

//用 Servlet 读取 Cookies
//要读取 cookies，你需要通过调用 HttpServletRequest 的 getCookies( ) 方法创建一个 javax.servlet.http.Cookie 对象的数组。然后循环遍历数组，并使用 getName() 和 getValue() 方法来访问每个 cookie 及其相关的值。

//实例
//让我们读取上述例子中已经设置的 cookies：

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//// Extend HttpServlet class
//public class ReadCookies extends HttpServlet {
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      Cookie cookie = null;
//      Cookie[] cookies = null;
//      // Get an array of Cookies associated with this domain
//      cookies = request.getCookies();
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Reading Cookies Example";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//                "<html>\n" +
//                "<head><title>" + title + "</title></head>\n" +
//                "<body bgcolor=\"#f0f0f0\">\n" );
//      if( cookies != null ){
//         out.println("<h2> Found Cookies Name and Value</h2>");
//         for (int i = 0; i < cookies.length; i++){
//            cookie = cookies[i];
//            out.print("Name : " + cookie.getName( ) + ",  ");
//            out.print("Value: " + cookie.getValue( )+" <br/>");
//         }
//      }else{
//          out.println(
//            "<h2>No cookies founds</h2>");
//      }
//      out.println("</body>");
//      out.println("</html>");
//   }
//}
//编译上述 servlet ReadCookies 并在 web.xml 文件中创建适当的条目。如果你已经设置了 first name cookie 为 “John”，last name cookie 为 “Player” ，那么尝试运行 http://localhost:8080/ReadCookies，将显示如下所示结果：

// Found Cookies Name and Value

//Name : first_name, Value: John

//Name : last_name,  Value: Player
//用 Servlet 删除 Cookies
//删除 cookies 非常简单。如果你想删除一个 cookie，那么只需要按照如下所示的三个步骤进行：

//读取一个现存的 cookie 并把它存储在 Cookie 对象中。
//使用 setMaxAge() 方法设置 cookie 的年龄为零来删除一个现存的 cookie。
//将这个 cookie 添加到响应z中。
//实例
//下述例子将删除一个现存的命名为 “first name” 的 cookie，且当你下次运行 ReadCookies servlet 时，它会为 first name 返回空值。

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//// Extend HttpServlet class
//public class DeleteCookies extends HttpServlet {
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      Cookie cookie = null;
//      Cookie[] cookies = null;
//      // Get an array of Cookies associated with this domain
//      cookies = request.getCookies();
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Delete Cookies Example";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//                "<html>\n" +
//                "<head><title>" + title + "</title></head>\n" +
//                "<body bgcolor=\"#f0f0f0\">\n" );
//       if( cookies != null ){
//         out.println("<h2> Cookies Name and Value</h2>");
//         for (int i = 0; i < cookies.length; i++){
//            cookie = cookies[i];
//            if((cookie.getName( )).compareTo("first_name") == 0 ){
//                 cookie.setMaxAge(0);
//                 response.addCookie(cookie);
//                 out.print("Deleted cookie : " +
//                              cookie.getName( ) + "<br/>");
//            }
//            out.print("Name : " + cookie.getName( ) + ",  ");
//            out.print("Value: " + cookie.getValue( )+" <br/>");
//         }
//      }else{
//          out.println(
//            "<h2>No cookies founds</h2>");
//      }
//      out.println("</body>");
//      out.println("</html>");
//   }
//}
//编译上述 servlet DeleteCookies 并在 web.xml 文件中创建适当的条目。现在运行 http://localhost:8080/DeleteCookies，将显示如下所示的结果：

//Cookies Name and Value

//Deleted cookie : first_name


//Name : first_name, Value: John


//Name : last_name,  Value: Player


//现在尝试运行 http://localhost:8080/ReadCookies，它将只显示一个 cookie，如下所示：

// Found Cookies Name and Value

//Name : last_name,  Value: Player
//你可以在 IE 浏览器中手动删除 Cookies。在“工具”菜单，选择“Internet 选项”。如果要删除所有的 cookies，请按删除 Cookies。
}
