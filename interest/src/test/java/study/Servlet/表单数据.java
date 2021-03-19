package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 表单数据 {
//当你需要从浏览器到 Web 服务器传递一些信息并最终传回到后台程序时，你一定遇到了许多情况。
// 浏览器使用两种方法向 Web 服务器传递信息。这些方法是 GET 方法和 POST 方法。

//GET 方法
//GET 方法向页面请求发送已编码的用户信息。页面和已编码的信息用 ? 字符分隔，如下所示：

//http://www.test.com/hello?key1=value1&key2=value2
//GET 方法是从浏览器向 web 服务器传递信息的默认的方法，且它会在你的浏览器的地址栏中产生一个很长的字符串。
// 如果你向服务器传递密码或其他敏感信息，请不要使用 GET 方法。
// GET 方法有大小限制：请求字符串中最多只能有 1024 个字符。

//这些信息使用 QUERY_STRING 头传递，并通过 QUERY_STRING 环境变量访问，
// Servlet 使用 doGet() 方法处理这种类型的请求。

//POST 方法
//一般情况下，将信息传递给后台程序的一种更可靠的方法是 POST 方法。
// POST 方法打包信息的方式与 GET 方法相同，
// 但是 POST 方法不是把信息作为 URL 中 ? 字符之后的文本字符串进行发送，而是把它作为一个单独的消息发送。
// 消息以标准输出的形式传到后台程序，你可以在你的处理过程中解析并使用这些标准输出。
// Servlet 使用 doPost() 方法处理这种类型的请求。

//使用 Servlet 读取表单数据
//Servlet 以自动解析的方式处理表单数据，根据不同的情况使用如下不同的方法：

//getParameter()：你可以调用 request.getParameter() 方法来获取表单参数的值。

//getParameterValues()：如果参数出现不止一次，那么调用该方法并返回多个值，例如复选框。

//getParameterNames()：如果你想要得到一个当前请求的所有参数的完整列表，那么调用该方法。
//使用 URL 的 GET 方法实例
//这是一个简单的 URL，使用 GET 方法将两个值传递给 HelloForm 程序。

//http://localhost:8080/HelloForm?first_name=ZARA&last_name=ALI

//下面是 HelloForm.java servlet 程序，处理由 web 浏览器给定的输入。
// 我们将使用 getParameter() 方法，使访问传递的信息变得非常容易：

// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
// Extend HttpServlet class
//public class HelloForm extends HttpServlet {
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Using GET Method to Read Form Data";
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
//假设你的环境已经正确地设置，编译 HelloForm.java，如下所示：

//$ javac HelloForm.java
//如果一切顺利，上述编译会产生 HelloForm.class 文件。
// 接下来，你需要把这个类文件复制到 <Tomcat-installation-directory>/webapps/ROOT/WEB-INF/classes 中，
// 并在 <Tomcat-installation-directory>/webapps/ROOT/WEB-INF/ 中的 web.xml 文件中创建以下条目：

//    <servlet>
//        <servlet-name>HelloForm</servlet-name>
//        <servlet-class>HelloForm</servlet-class>
//    </servlet>
//    <servlet-mapping>
//        <servlet-name>HelloForm</servlet-name>
//        <url-pattern>/HelloForm</url-pattern>
//    </servlet-mapping>
//现在在你浏览器的地址栏中输入 http://localhost:8080/HelloForm?first_name=ZARA&last_name=ALI，
// 并在浏览器中触发上述命令之前，确保你已经启动 Tomcat 服务器。这将产生如下所示的结果：

//Using GET Method to Read Form Data

//使用表单的 GET 方法实例
//下面是一个简单的实例，使用 HTML 表单和提交按钮传递两个值。
// 我们将使用相同的 Servlet HelloForm 来处理这个输入。

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
//将这个 HTML 保存到 hello.htm 文件中，并把它放在 <Tomcat-installation-directory>/webapps/ROOT 目录下。
// 当你访问 http://localhost:8080/Hello.htm 时，下面是上述表单的实际输出。

//First Name:
// Last Name:
//尝试输入姓名，然后点击提交按钮来在 tomcat 运行的本地计算机上查看结果。
// 基于提供的输入，它会产生与上述例子中相似的结果。

//使用表单的 POST 方法实例
//让我们对上述 servlet 做一点修改，以便它可以处理 GET 方法和 POST 方法。
// 下面是 HelloForm.java servlet 程序，使用 GET 和 POST 方法处理由 web 浏览器给出的输入。

// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
// //Extend HttpServlet class
//public class HelloForm extends HttpServlet {
//  // Method to handle GET method request.
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Using GET Method to Read Form Data";
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
//  // Method to handle POST method request.
//  public void doPost(HttpServletRequest request,
//                     HttpServletResponse response)
//      throws ServletException, IOException {
//     doGet(request, response);
//  }
//}
//现在编译，部署上述 Servlet，并使用带有 POST 方法的 Hello.htm 测试它，如下所示：

//<html>
//<body>
//<form action="HelloForm" method="POST">
//First Name: <input type="text" name="first_name">
//<br />
//Last Name: <input type="text" name="last_name" />
//<input type="submit" value="Submit" />
//</form>
//</body>
//</html>
//这是上述表单的实际输出，尝试输入姓名，然后点击提交按钮，在 tomcat 运行的本地计算机上查看结果。

//First Name:
// Last Name:
//基于提供的输入，它会产生与上述例子中相似的结果。

//将复选框数据传递到 Servlet 程序
//当要选择多个选项时，就要使用复选框。

//这是一个 HTML 代码实例，CheckBox.htm，一个表单带有两个复选框。

//<html>
//<body>
//<form action="CheckBox" method="POST" target="_blank">
//<input type="checkbox" name="maths" checked="checked" /> Maths
//<input type="checkbox" name="physics"  /> Physics
//<input type="checkbox" name="chemistry" checked="checked" />
//                                                Chemistry
//<input type="submit" value="Select Subject" />
//</form>
//</body>
//</html>
//这段代码的结果是如下所示的表单：

// Maths  Physics  Chemistry
//下面是 CheckBox.java servlet 程序，来为复选框按钮处理 web 浏览器给定的输入。

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//// Extend HttpServlet class
//public class CheckBox extends HttpServlet {
//  // Method to handle GET method request.
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Reading Checkbox Data";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//                "<html>\n" +
//                "<head><title>" + title + "</title></head>\n" +
//                "<body bgcolor=\"#f0f0f0\">\n" +
//                "<h1 align=\"center\">" + title + "</h1>\n" +
//                "<ul>\n" +
//                "  <li><b>Maths Flag : </b>: "
//                + request.getParameter("maths") + "\n" +
//                "  <li><b>Physics Flag: </b>: "
//                + request.getParameter("physics") + "\n" +
//                "  <li><b>Chemistry Flag: </b>: "
//                + request.getParameter("chemistry") + "\n" +
//                "</ul>\n" +
//                "</body></html>");
//  }
//  // Method to handle POST method request.
//  public void doPost(HttpServletRequest request,
//                     HttpServletResponse response)
//      throws ServletException, IOException {
//     doGet(request, response);
//  }
//}
//上面的实例将显示如下所示结果：

//Reading Checkbox Data

//读取所有的表单参数
//以下是使用 HttpServletRequest 的 getParameterNames() 方法的通用实例来读取所有可用的表单参数。
// 该方法返回一个枚举，包含了未指定顺序的参数名称。

//一旦我们得到一个枚举，我们可以以标准方式循环这个枚举，使用 hasMoreElements() 方法来确定何时停止循环，
// 使用 nextElement() 方法来获取每个参数的名称。

//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.utils.*;
//// Extend HttpServlet class
//public class ReadParams extends HttpServlet {
//  // Method to handle GET method request.
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Reading All Form Parameters";
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//        "<html>\n" +
//        "<head><title>" + title + "</title></head>\n" +
//        "<body bgcolor=\"#f0f0f0\">\n" +
//        "<h1 align=\"center\">" + title + "</h1>\n" +
//        "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
//        "<tr bgcolor=\"#949494\">\n" +
//        "<th>Param Name</th><th>Param Value(s)</th>\n"+
//        "</tr>\n");
//      Enumeration paramNames = request.getParameterNames();
//      while(paramNames.hasMoreElements()) {
//         String paramName = (String)paramNames.nextElement();
//         out.print("<tr><td>" + paramName + "</td>\n<td>");
//         String[] paramValues =
//                request.getParameterValues(paramName);
//         // Read single valued data
//         if (paramValues.length == 1) {
//           String paramValue = paramValues[0];
//           if (paramValue.length() == 0)
//             out.println("<i>No Value</i>");
//           else
//             out.println(paramValue);
//         } else {
//             // Read multiple valued data
//             out.println("<ul>");
//             for(int i=0; i < paramValues.length; i++) {
//                out.println("<li>" + paramValues[i]);
//             }
//             out.println("</ul>");
//         }
//      }
//      out.println("</tr>\n</table>\n</body></html>");
//  }
//  // Method to handle POST method request.
//  public void doPost(HttpServletRequest request,
//                     HttpServletResponse response)
//      throws ServletException, IOException {
//     doGet(request, response);
//  }
//}
//现在，用下面的表单尝试上述 servlet：

//<html>
//<body>
//<form action="ReadParams" method="POST" target="_blank">
//<input type="checkbox" name="maths" checked="checked" /> Maths
//<input type="checkbox" name="physics"  /> Physics
//<input type="checkbox" name="chemistry" checked="checked" /> Chem
//<input type="submit" value="Select Subject" />
//</form>
//</body>
//</html>
//现在使用上述表单调用 servlet 将产生如下所示结果：

//Reading All Form Parameters

//你可以尝试使用上述 servlet 来读取有其他对象的其他表单数据，比如文本框、单选按钮或下拉框等。
}
