package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 编写过滤器 {
//Servlet 过滤器是Java 类，可用于 Servlet 编程中的下述目的：

//在它们访问后端资源之前，拦截这些来自客户端的请求。

//在它们发送回客户端之前，处理这些来自服务器端的响应。
//这是规范建议的各种类型的过滤器：

//身份验证过滤器。

//数据压缩过滤器。

//加密过滤器。

//触发访问事件资源的过滤器。

//图像转换过滤器。

//日志记录和审核过滤器。

//MIME-类型链过滤器。

//Tokenizing 过滤器。

//转换 XML 内容的 XSL/T 过滤器。


//过滤器在部署描述符文件 web.xml 中被部署，然后被映射到 servlet 名称或你的应用程序的部署描述符中的 URL 模式。

//当 web 容器启动你的 web 应用程序时，它会为每个在部署描述符中已声明的过滤器创建一个实例。
// 过滤器按照它们在部署描述符中声明的顺序执行。

//Servlet 过滤器方法
//过滤器仅仅是一个实现了 javax.servlet.Filter 接口的 Java 类。javax.servlet.Filter 接口定义了三种方法：

//序号	    方法 & 描述
//1
//public void doFilter (ServletRequest, ServletResponse, FilterChain)
//该方法在每次一个请求/响应对因客户端在链的末端请求资源而通过链传递时由容器调用。

//2
//public void init(FilterConfig filterConfig)
//该方法由 Web 容器调用，指示一个过滤器被放入服务。

//3
//public void destroy()
//该方法由 Web 容器调用，指示一个过滤器从服务被去除。

//Servlet 过滤器实例
//以下是 Servlet 过滤器的实例，将输出客户端的 IP 地址和当前的日期时间。
// 这个例子使你对 Servlet 过滤器有了基本的了解，但是你可以使用相同的概念编写更复杂的过滤器应用程序：

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.utils.*;
//// Implements Filter class
//public class LogFilter implements Filter  {
//   public void  init(FilterConfig config)
//                         throws ServletException{
//      // Get init parameter
//      String testParam = config.getInitParameter("test-param");
//      //Print the init parameter
//      System.out.println("Test Param: " + testParam);
//   }
//   public void  doFilter(ServletRequest request,
//                 ServletResponse response,
//                 FilterChain chain)
//                 throws java.io.IOException, ServletException {
//      // Get the IP address of client machine.
//      String ipAddress = request.getRemoteAddr();
//      // Log the IP address and current timestamp.
//      System.out.println("IP "+ ipAddress + ", Time "
//                                       + new Date().toString());
//      // Pass request back down the filter chain
//      chain.doFilter(request,response);
//   }
//   public void destroy( ){
//      /* Called before the Filter instance is removed
//      from service by the web container*/
//   }
//}
//用常用的方式编译 LogFilter.java 并把你的类文件放入 /webapps/ROOT/WEB-INF/classes 中。

//Web.xml 中的 Servlet 过滤器映射
//过滤器被定义然后被映射到一个 URL 或 Servlet中，这与Servlet被定义然后映射到一个 URL 模式中的方法是相同的。
// 为在部署描述符文件 web.xml 中过滤器标签创建如下所示条目：

//<filter>
//   <filter-name>LogFilter</filter-name>
//   <filter-class>LogFilter</filter-class>
//   <init-param>
//      <param-name>test-param</param-name>
//      <param-value>Initialization Paramter</param-value>
//   </init-param>
//</filter>
//<filter-mapping>
//   <filter-name>LogFilter</filter-name>
//   <url-pattern>/*</url-pattern>
//</filter-mapping>
//上述过滤器可以应用到所有的 servlet 中，因为在配置中我们指定了 /* 。
// 如果你只想在少数的 servlet 中应用过滤器，那么你可以指定一个特定的 servlet 路径。

//现在尝试用常用的方式调用任何 servlet，然后你将会在 web 服务器日志中看到生成的日志。
// 你也可以使用 Log4J 记录器来在一个单独的文件中记录上述日志。

//使用多个过滤器
//Web 应用程序可以定义多个带有不同目的的过滤器。考虑这种情况，你定义了两个过滤器 AuthenFilter 和 LogFilter。
// 除了你需要创建一个如下所述的不同的映射之外，其余的处理与上述解释的一样：

//<filter>
//   <filter-name>LogFilter</filter-name>
//   <filter-class>LogFilter</filter-class>
//   <init-param>
//      <param-name>test-param</param-name>
//      <param-value>Initialization Paramter</param-value>
//   </init-param>
//</filter>
//<filter>
//   <filter-name>AuthenFilter</filter-name>
//   <filter-class>AuthenFilter</filter-class>
//   <init-param>
//      <param-name>test-param</param-name>
//      <param-value>Initialization Paramter</param-value>
//   </init-param>
//</filter>
//<filter-mapping>
//   <filter-name>LogFilter</filter-name>
//   <url-pattern>/*</url-pattern>
//</filter-mapping>
//<filter-mapping>
//   <filter-name>AuthenFilter</filter-name>
//   <url-pattern>/*</url-pattern>
//</filter-mapping>

//过滤器的应用顺序
//web.xml 中的 filter-mapping 元素的顺序决定了 web 容器把过滤器应用到 servlet 的顺序。
// 若要反转过滤器的顺序，你只需要在 web.xml 文件中反转 filter-mapping 元素即可。

//例如，上述实例首先应用 LogFilter然后再应用 AuthenFilter 到任何 servlet 中，但是下述实例将反转这个顺序：

//<filter-mapping>
//   <filter-name>AuthenFilter</filter-name>
//   <url-pattern>/*</url-pattern>
//</filter-mapping>
//<filter-mapping>
//   <filter-name>LogFilter</filter-name>
//   <url-pattern>/*</url-pattern>
//</filter-mapping>
}
