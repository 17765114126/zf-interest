package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 包 {
//涉及到 WEB-INF 子目录的 web 应用程序结构是所有的 Java web 应用程序的标准，
// 并且是由 servlet API 规范指定的。给定一个 myapp 的顶级目录名，这里是目录结构，如下所示：
//
///myapp
//    /images
//    /WEB-INF
//        /classes
//        /lib
//WEB-INF 子目录包含了应用程序的部署描述符，命名为 web.xml。
// 所有的 HTML 文件都位于顶级目录 myapp 下。对于管理员用户，你会发现 ROOT 目录是和 myApp 一样的父目录。
//
//创建包中的 Servlets
//WEB-INF/classes 目录在与它们的包名称匹配的结构中包含了所有的 servlet 类和其他的类文件。
//例如，如果你有一个完全合格的类名称 com.myorg.MyServlet，那么这个 servlet 类必须被放置在如下所示的目录中：

///myapp/WEB-INF/classes/com/myorg/MyServlet.class
//下面是创建包名为 com.myorg 的 MyServlet 类的例子

//// Name your package
//package com.myorg;
//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//public class MyServlet extends HttpServlet {
//  private String message;
//  public void init() throws ServletException
//  {
//      // Do required initialization
//      message = "Hello World";
//  }
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      // Actual logic goes here.
//      PrintWriter out = response.getWriter();
//      out.println("<h1>" + message + "</h1>");
//  }
//  public void destroy()
//  {
//      // do nothing.
//  }
//}
//编译包中的 Servlets
//编译包中可用的类没有什么大的不同。
// 最简单的方法是将你的 java 文件保存在完全限定路径中，正如上面所提到的一样，类将被保存在 com.myorg 中。
// 你还需要将该目录添加到 CLASSPATH 中。

//假设你的环境已正确设置，
// 进入 <Tomcat-installation-directory>/webapps/ROOT/WEB-INF/classes 目录并编译 MyServlet.java，
// 如下所示：

//$ javac MyServlet.java
//如果 servlet 依赖于任何其他的库，那么你必须在你的 CLASSPATH 中包含那些 JAR 文件。
// 我只包含了 servlet-api.jar JAR 文件，因为我在 Hello World 程序中没有使用任何其他的库。

//该命令行使用来自 Sun Microsystems Java 软件开发工具包（JDK）内置的 javac 编译器。
// 为了让该命令正常工作，必须包括你在 PATH 环境变量中所使用的 Java SDK 的位置。

//如果一切顺利，上述编译会在相同的目录下生成 MyServlet.class 文件。
// 下一节将解释如何在生产中部署一个已编译的 servlet。

//打包的 Servlet 部署
//默认情况下，servlet 应用程序位于路径 <Tomcat-installation-directory>/webapps/ROOT 下且类文件放在 <Tomcat-installation-directory>/webapps/ROOT/WEB-INF/classes 中。

//如果你有一个完全合格的类名称 com.myorg.MyServlet，
// 那么这个 servlet 类必须位于 WEB-INF/classes/com/myorg/MyServlet.class 中，
// 你需要在位于 <Tomcat-installation-directory>/webapps/ROOT/WEB-INF/ 的 web.xml 文件中创建以下条目：

//<servlet>
//   <servlet-name>MyServlet</servlet-name>
//   <servlet-class>com.myorg.MyServlet</servlet-class>
//</servlet>
//<servlet-mapping>
//   <servlet-name>MyServlet</servlet-name>
//   <url-pattern>/MyServlet</url-pattern>
//</servlet-mapping>
//上述条目要被创建在 web.xml 文件中的 <web-app>...</web-app> 标签内。
// 在该文件中可能已经有各种可用的条目，但没有关系。

//你基本上已经完成了，
// 现在让我们使用 <Tomcat-installation-directory>\bin\startup.bat（Windows 操作系统中）
// 或 <Tomcat-installation-directory>/bin/startup.sh（Linux/Solaris 等操作系统中）
// 启动 tomcat 服务器，最后在浏览器的地址栏中输入 http://localhost:8080/MyServlet。
// 如果一切顺利，你会得到如下所示的结果：
//
//Hello World
}
