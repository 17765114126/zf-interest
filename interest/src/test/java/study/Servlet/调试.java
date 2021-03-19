package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 调试 {
//测试/调试 servlet 始终是困难的。Servlets 往往涉及大量的客户端/服务器交互，可能会出现错误但是又难以重现。

//这里有一些提示和建议，可以帮助你调试。

//System.out.println()
//System.out.println() 作为一个标记用来测试某一代码片段是否被执行，使用方法非常简单。
// 我们也可以输出变量值。
// 另外：

//由于 System 对象是核心 Java 对象的一部分，它可以用于任何不需要安装任何额外类的地方。
// 这包括 Servlets、JSP、RMI、EJB's、普通的 Beans 和类，以及独立的应用程序。

//与在断点处停止相比，写入 System.out 不会对应用程序的正常执行流程有太多干扰，这使得它在时序重要的时候显得非常有价值。
//以下使用 System.out.println() 的语法：

//System.out.println("Debugging message");
//通过上述语法生成的所有消息将被记录在 web 服务器的日志文件中。

//消息记录
//利用标准日志记录方法，
//使用适当的日志记录方法来记录所有调试、警告和错误消息是非常好的想法，我使用的是 log4J 来记录所有的消息。

//Servlet API 还提供了一个简单的输出信息的方式，使用 log() 方法，如下所示：

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//public class ContextLog extends HttpServlet {
//  public void doGet(HttpServletRequest request,
//      HttpServletResponse response) throws ServletException,
//         java.io.IOException {
//      String par = request.getParameter("par1");
//      //Call the two ServletContext.log methods
//      ServletContext context = getServletContext( );
//      if (par == null || par.equals(""))
//      //log version with Throwable parameter
//      context.log("No message received:",
//          new IllegalStateException("Missing parameter"));
//      else
//          context.log("Here is the visitor's message: " + par);
//      response.setContentType("text/html");
//      java.io.PrintWriter out = response.getWriter( );
//      String title = "Context Log";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//        "<html>\n" +
//        "<head><title>" + title + "</title></head>\n" +
//        "<body bgcolor=\"#f0f0f0\">\n" +
//        "<h1 align=\"center\">" + title + "</h1>\n" +
//        "<h2 align=\"center\">Messages sent</h2>\n" +
//        "</body></html>");
//    } //doGet
//}
//ServletContext 把它的文本消息记录到 servlet 容器的日志文件中。
// 使用 Tomcat，这些日志可以在 <Tomcat-installation-directory>/logs 目录中找到。

//这些日志文件确实为新出现的错误或问题的频率给出了指示。
// 正因为如此，在通常不会出现的异常 catch 子句中使用 log() 函数是很好的。

//使用 JDB 调试器
//你可以使用调试 applet 或应用程序的相同的 jdb 命令来调试 servlet。

//为了调试一个 servlet，我们可以调试 sun.servlet.http.HttpServer，
// 然后把它看成是 HttpServer 执行 servlet 来响应来自浏览器端的 HTTP 请求。
// 这与调试 applet 小程序的方式非常相似。
// 与调试 applet 不同的是，被调试的实际程序是 sun.applet.AppletViewer。

//大多数调试器会自动隐藏了解如何调试 applet 的细节。
// 直到他们为 servlet 做同样的事情，你必须做以下操作来帮助你的调试器：

//设置你的调试器的类路径，以便它可以找到 sun.servlet.http.Http-Server 和相关的类。

//设置你的调试器的类路径，以便它可以找到你的 servlet 和支持的类，
// 通常是在 server_root/servlets 和 server_root/classes 中。
//你通常不会希望 server_root/servlets 在你的 classpath 中，因为它会禁用 servlet 的重载。
// 然而这种包含对于调试是有用的。
// 在 HttpServer 中的自定义的 servlet 加载器加载 servlet 之前，它允许你的调试器在 servlet 中设置断点。

//一旦你设置了正确的类路径，就可以开始调试 sun.servlet.http.HttpServer。
// 你可以在任何你想要调试的 servlet 中设置断点，
// 然后使用 web 浏览器为给定的 servlet（http://localhost:8080/servlet/ServletToDebug) 向 HttpServer 发出请求。
// 你会看到程序执行到你设置的断点处停止。

//使用注释
//代码中的注释有助于以各种方式调试程序。注释可用于调试过程中的许多其他方式中。

//Servlet 使用 Java 注释，单行注释（//...）和多行注释（/ .../）可用于暂时移除部分 Java 代码。
// 如果 bug 消失，仔细看看你之前注释的代码并找出问题所在。

//客户端和服务器端头信息
//有时，当一个 servlet 并没有像预期那样工作时，查看原始的 HTTP 请求和响应是非常有用的。
// 如果你对 HTTP 结构很熟悉，你可以阅读请求和响应，看看这些头信息中究竟是什么。

//重要的调试技巧
//这里是 servlet 调试中的一些调试技巧列表：

//请注意 server _ root/classes 不会重载，而 server_root/servlets 可能会。

//要求浏览器显示它所显示的页面的原始内容。这有助于识别格式的问题。它通常是视图菜单下的一个选项。

//通过强制执行完全重载页面，来确保浏览器还没有缓存前一个请求的输出。
// 在 Netscape Navigator 中，使用 Shift-Reload；在 IE 浏览器中，请使用 Shift-Refresh。

//确认 servlet 的 init() 方法接受一个 ServletConfig 参数并立即调用 super.init(config)。
}
