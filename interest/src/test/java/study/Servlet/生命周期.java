package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 生命周期 {
//Servlet 生命周期可被定义为从它被创建直到被销毁的整个过程。以下是 servlet 遵循的过程：

//通过调用 init () 方法 servlet 被初始化。

//Servlet 调用 service() 方法来处理客户端的请求。

//通过调用 destroy() 方法 servlet 终止。

//最后，servlet 是由 JVM 的垃圾回收器进行垃圾回收的。
//现在让我们详细的讨论生命周期的方法。

//init() 方法
//init 方法被设计成只调用一次。它在第一次创建 servlet 时被调用，在后续每次用户请求时不再调用。
// 因此，它用于一次性初始化，与 applets 的 init 方法一样。

//通常情况下，当用户第一次调用对应于该 servlet 的 URL 时，servlet 被创建，
// 但是当服务器第一次启动时，你也可以指定 servlet 被加载。

//当用户调用 servlet 时，每个 servlet 的一个实例就会被创建，
// 并且每一个用户请求都会产生一个新的线程，该线程在适当的时候移交给 doGet 或 doPost 方法。
// init() 方法简单地创建或加载一些数据，这些数据将被用于 servlet 的整个生命周期。

//init 方法的定义如下：

//public void init() throws ServletException {
//  // Initialization code...
//}
//service() 方法
//service() 方法是执行实际任务的主要方法。
// Servlet 容器（即 web 服务器）调用 service() 方法来处理来自客户端（浏览器）的请求，
// 并将格式化的响应写回到客户端。

//每次服务器接收到一个 servlet 请求时，服务器会产生一个新的线程并调用服务。
// service() 方法检查 HTTP 请求类型（GET、POST、PUT、DELETE 等），
// 并在适当的时候调用 doGet、doPost、doPut、doDelete 等方法。

//下面是该方法的特征：

//public void service(ServletRequest request,
//                    ServletResponse response)
//      throws ServletException, IOException{
//}
//service() 方法由容器调用，且 service 方法在适当的时候调用 doGet、doPost、doPut、doDelete 等。
// 所以对 service() 方法你什么都不需要做，只是根据你接收到的来自客户端的请求类型来重写 doGet() 或 doPost()。

//doGet() 和 doPost() 方法在每次服务请求中是最常用的方法。下面是这两种方法的特征。

//doGet() 方法
//GET 请求来自于一个 URL 的正常请求，或者来自于一个没有 METHOD 指定的 HTML 表单，且它由 doGet() 方法处理。

//public void doGet(HttpServletRequest request,
//                  HttpServletResponse response)
//    throws ServletException, IOException {
//    // Servlet code
//}
//doPost() 方法
//POST 请求来自于一个 HTML 表单，该表单特别的将 POST 列为 METHOD 且它由 doPost() 方法处理。

//public void doPost(HttpServletRequest request,
//                   HttpServletResponse response)
//    throws ServletException, IOException {
//    // Servlet code
//}
//destroy() 方法
//destroy() 方法只在 servlet 生命周期结束时被调用一次。
// destroy() 方法可以让你的 servlet 关闭数据库连接、停止后台线程、将 cookie 列表或点击计数器写入磁盘，
// 并执行其他类似的清理活动。

//在调用 destroy() 方法之后，servlet 对象被标记用于垃圾回收。destroy 方法的定义如下所示：

//public void destroy() {
//    // Finalization code...
//  }
//结构框图
//下图显示了一个典型的 servlet 生命周期场景。

//最先到达服务器的 HTTP 请求被委派到 servlet 容器。

//在调用 service() 方法之前 servlet 容器加载 servlet。

//然后 servlet 容器通过产生多个线程来处理多个请求，每个线程执行 servlet 的单个实例的 service() 方法。
//https://wiki.jikexueyuan.com/project/servlet/images/lifecycle1.jpg
}
