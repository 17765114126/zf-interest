package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 概述 {
//Servlet 为构建基于 Web 的应用程序提供了一个基于组件的、独立于平台的方法，没有 CGI 程序的性能限制。
// Servlet 访问 Java API 的整个家族，包括 JDBC API 来访问企业数据库。

//什么是 Servlets？
//Java servlet 是运行在 Web 或应用服务器上的程序，
// 作为在来自 Web 浏览器或其他 HTTP 客户机的请求和在 HTTP 服务器上的数据库或应用程序的中间层。

//使用 Servlet，你可以通过 web 页面表单来收集用户的输入，显示从数据库或其他来源的记录，动态地创建 web 页面。

//Java servlet 通常服务于使用 Common Gateway Interface (CGI) 实现的同样的目的程序。
// 但与 CGI 相比，Servlet 具有几个优点。

//性能更好。
//Servlet 在 Web 服务器的地址空间内执行。没有必要创建一个单独的进程来处理每个客户端请求。

//由于 Servlet 是用 Java 编写的，所以它是跨平台的。

//在服务器上的 Java 安全性管理器执行的一些限制来保护服务器上的资源。所以 servlet 是可信的。

//Java 类库的完整的功能是对 Servlet 来说是可用的。
// 它可以与小应用程序、数据库或其他软件通过通信接口和你已经了解的RMI机制进行通信。
//Servlets 架构
//下图显示了在 Web 应用程序中 Servlets 的位置。

//https://wiki.jikexueyuan.com/project/servlet/images/arch1.jpg

//Servlets 任务
//Servlet 执行以下主要任务：

//读取由客户端（浏览器）发送的显式数据。
// 这包括网页上的 HTML 表单，或者也可以是来自 applet 或自定义的 HTTP 客户端程序的表单。

//读取由客户端（浏览器）发送的隐式 HTTP 请求数据。
// 这包括 cookies、媒体类型和浏览器能理解的压缩格式等等。

//处理数据并生成结果。
// 这个过程可能需要访问数据库，执行 RMI 或 CORBA 调用，调用 Web 服务，或者直接计算响应。

//发送显式数据（即文档）到客户端（浏览器）。
// 该文档可以以多种多样的格式被发送，包括文本文件（HTML 或 XML）、二进制文件（GIF 图像）、Excel 等。

//发送隐式的 HTTP 响应到客户端（浏览器）。
// 这包括告诉浏览器或其他客户端被返回的文档类型（例如 HTML），设置 cookies 和缓存参数，以及其他类似的任务。

//Servlets 包
//Java Servlet 是运行在 Web 服务器上的 Java 类，在 Web 服务器上有一个支持 Java Servlet 规范的解释器。

//Servlet 可以使用 javax.servlet 和 javax.servlet.http 包来创建。
// 它们是 Java 企业版的一个标准部分，也是支持大型开发项目的 Java 类库的扩展版。

//这些类实现了 Java Servlet 和 JSP 规范。
// 在写这篇教程的时候，使用的版本分别是 Java Servlet 2.5 和 JSP 2.5。

//就像任何其他 Java 类一样，Java Servlet 可以创建和编译。
// 在安装 Servlet 包，并将它们添加到你的电脑的 Classpath 中之后，
// 你可以使用 JDK 的 Java 编译器或其他任何当前编译器来编译 Servlet。

//引自 : https://wiki.jikexueyuan.com/project/servlet/
}
