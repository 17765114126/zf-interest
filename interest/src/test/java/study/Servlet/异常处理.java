package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 异常处理 {
//当一个 servlet 抛出一个异常时，web 容器在使用了 exception-type 元素的 web.xml 中搜索与抛出的异常类型相匹配的配置。

//你必须在 web.xml 中使用 error-page 元素来指定 servlet 调用，作为对特定的异常或 HTTP 状态码作出的响应。

//web.xml 配置
//考虑这种情况，你有一个 ErrorHandler servelt，当任何已定义的异常或错误出现时就会被调用。
// 以下是要在 web.xml 中创建的条目。

//<!-- servlet definition -->
//<servlet>
//        <servlet-name>ErrorHandler</servlet-name>
//        <servlet-class>ErrorHandler</servlet-class>
//</servlet>
//<!-- servlet mappings -->
//<servlet-mapping>
//        <servlet-name>ErrorHandler</servlet-name>
//        <url-pattern>/ErrorHandler</url-pattern>
//</servlet-mapping>

//<!-- error-code related error pages -->
//<error-page>
//    <error-code>404</error-code>
//    <location>/ErrorHandler</location>
//</error-page>
//<error-page>
//    <error-code>403</error-code>
//    <location>/ErrorHandler</location>
//</error-page>

//<!-- exception-type related error pages -->
//<error-page>
//    <exception-type>
//          javax.servlet.ServletException
//    </exception-type >
//    <location>/ErrorHandler</location>
//</error-page>

//<error-page>
//    <exception-type>java.io.IOException</exception-type >
//    <location>/ErrorHandler</location>
//</error-page>

//如果你想对所有的异常有一个通用的错误处理程序，
// 那么你应该定义如下所示的 error-page，而不是为每个异常定义单独的 error-page 元素：

//<error-page>
//    <exception-type>java.lang.Throwable</exception-type >
//    <location>/ErrorHandler</location>
//</error-page>

//以下是异常处理中有关上述 web.xml 需要注意的点：

//Servelt ErrorHandler 与其他的 servelt 的定义方式一样，且在 web.xml 中配置。

//如果状态码有任何错误出现，不管是 404（未找到）还是 403（禁止），那么 ErrorHandler servlet 会被调用。

//如果 web 应用程序抛出 ServletException 或 IOException，那么 web 容器就会调用 /ErrorHandler servlet。

//你可以定义不同的错误处理程序来处理不同类型的错误或异常。上述例子非常通用，希望它达到了让你理解基本概念的目的。
//请求属性 - 错误/异常
//以下是错误处理 servlet 可以访问的请求属性列表，用来分析错误/异常的性质。

//序号	    属性 & 描述
//1
//javax.servlet.error.status_code
//该属性给出状态码，状态码可被存储，并在存储为 java.lang.Integer 数据类型后可被分析。

//2
//javax.servlet.error.exception_type
//该属性给出异常类型的信息，异常类型可被存储，并在存储为 java.lang.Class 数据类型后可被分析。

//3
//javax.servlet.error.message
//该属性给出确切错误消息的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。

//4
//javax.servlet.error.request_uri
//该属性给出有关 URL 调用 Servlet 的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。

//5
//javax.servlet.error.exception
//该属性给出异常产生的信息，信息可被存储，并在存储为 java.lang.Throwable 数据类型后可被分析。

//6
//javax.servlet.error.servlet_name
//该属性给出 servlet 的名称，名称可被存储，并在存储为 java.lang.String 数据类型后可被分析。

//Servlet 错误处理程序实例
//以下是 Servlet 实例，用于任何你定义的 servlet 出现任何错误或异常时的情况。

//这个例子让你对 Servlet 中的异常处理有了基本的了解，但是你可以使用相同的概念编写更复杂的异常处理应用程序：

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.utils.*;
//// Extend HttpServlet class
//public class ErrorHandler extends HttpServlet {
//  // Method to handle GET method request.
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Analyze the servlet exception
//      Throwable throwable = (Throwable)
//      request.getAttribute("javax.servlet.error.exception");
//      Integer statusCode = (Integer)
//      request.getAttribute("javax.servlet.error.status_code");
//      String servletName = (String)
//      request.getAttribute("javax.servlet.error.servlet_name");
//      if (servletName == null){
//         servletName = "Unknown";
//      }
//      String requestUri = (String)
//      request.getAttribute("javax.servlet.error.request_uri");
//      if (requestUri == null){
//         requestUri = "Unknown";
//      }
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Error/Exception Information";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//        "<html>\n" +
//        "<head><title>" + title + "</title></head>\n" +
//        "<body bgcolor=\"#f0f0f0\">\n");
//      if (throwable == null && statusCode == null){
//         out.println("<h2>Error information is missing</h2>");
//         out.println("Please return to the <a href=\"" +
//           response.encodeURL("http://localhost:8080/") +
//           "\">Home Page</a>.");
//      }else if (statusCode != null){
//         out.println("The status code : " + statusCode);
//      }else{
//         out.println("<h2>Error information</h2>");
//         out.println("Servlet Name : " + servletName +
//                             "</br></br>");
//         out.println("Exception Type : " +
//                             throwable.getClass( ).getName( ) +
//                             "</br></br>");
//         out.println("The request URI: " + requestUri +
//                             "<br><br>");
//         out.println("The exception message: " +
//                                 throwable.getMessage( ));
//      }
//      out.println("</body>");
//      out.println("</html>");
//  }
//  // Method to handle POST method request.
//  public void doPost(HttpServletRequest request,
//                     HttpServletResponse response)
//      throws ServletException, IOException {
//     doGet(request, response);
//  }
//}
//以常用的方式编译 ErrorHandler.java 并把你的类文件放入/webapps/ROOT/WEB-INF/classes 中。

//让我们将下述配置添加到 web.xml 文件中来处理异常：

//<servlet>
//        <servlet-name>ErrorHandler</servlet-name>
//        <servlet-class>ErrorHandler</servlet-class>
//</servlet>
//<!-- servlet mappings -->
//<servlet-mapping>
//        <servlet-name>ErrorHandler</servlet-name>
//        <url-pattern>/ErrorHandler</url-pattern>
//</servlet-mapping>
//<error-page>
//    <error-code>404</error-code>
//    <location>/ErrorHandler</location>
//</error-page>
//<error-page>
//    <exception-type>java.lang.Throwable</exception-type >
//    <location>/ErrorHandler</location>
//</error-page>
//现在，尝试使用一个会产生任何异常的 servlet 或者输入一个错误的 URL，
// 这将触发 Web 容器调用 ErrorHandler servlet 并显示适当的消息。
// 例如，如果你输入了一个错误的 URL，那么它将显示如下所示的结果：

//The status code : 404
//上述代码在一些 web 浏览器中可能无法工作。
// 因此请尝试使用 Mozilla 和 Safari 浏览器，这样上述代码应该能正常工作。
}
