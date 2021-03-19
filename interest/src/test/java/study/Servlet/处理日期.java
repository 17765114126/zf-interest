package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 处理日期 {
//使用 Servlet 的最重要的优势之一是你可以使用核心 Java 中的大多数可用的方法。
// 本教程将讲解 Java 提供的 Date 类，该类在 java.utils 包中是可用的，这个类封装了当前的日期和时间。

//Date 类支持两个构造函数。第一个构造函数用当前日期和时间初始化对象。

//Date( )
//下面的构造函数接受一个参数，该参数等于自 1970 年 1 月 1 日凌晨零点以来经过的毫秒数。

//Date(long millisec)
//一旦你得到一个可用的 Date 对象，你可以调用下列任意支持的方法来使用日期：

//序号	    方法和描述
//1
//boolean after(Date date)
//如果调用的 Date 对象中包含的日期在 date 指定的日期之后，则返回 true，否则返回 false。

//2
//boolean before(Date date)
//如果调用的 Date 对象中包含的日期在 date 指定的日期之前，则返回 true，否则返回 false。

//3
//Object clone( )
//重复调用 Date 对象。

//4
//int compareTo(Date date)
//把调用对象的值与 date 的值进行比较。如果两个值是相等的，则返回 0。
// 如果调用对象在 date 之前，则返回一个负值。如果调用对象在 date 之后，则返回一个正值。

//5
//int compareTo(Object obj)
//如果 obj 是 Date 类，则操作等同于 compareTo(Date)。否则，它会抛出一个 ClassCastException。

//6
//boolean equals(Object date)
//如果调用的 Date 对象中包含的时间和日期与 date 指定的相同，则返回 true，否则返回 false。

//7
//long getTime( )
//返回 1970 年 1 月 1 日以来经过的毫秒数。

//8
//int hashCode( )
//为调用对象返回哈希代码。

//9
//void setTime(long time)
//设置 time 指定的时间和日期，这表示从 1970 年 1 月 1 日凌晨零点以来经过的时间（以毫秒为单位）。

//10
//String toString( )
//转换调用的 Date 对象为一个字符串，并返回结果。

//获取当前的日期和时间
//在 Java Servlet 中获取当前的日期和时间是非常容易的.
// 你可以使用一个带有 toString() 方法的简单的 Date 对象来输出当前的日期和时间，如下所示：

//// Import required java libraries
//import java.io.*;
//import java.utils.Date;
//import javax.servlet.*;
//import javax.servlet.http.*;
//// Extend HttpServlet class
//public class CurrentDate extends HttpServlet {
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Display Current Date & Time";
//      Date date = new Date();
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//        "<html>\n" +
//        "<head><title>" + title + "</title></head>\n" +
//        "<body bgcolor=\"#f0f0f0\">\n" +
//        "<h1 align=\"center\">" + title + "</h1>\n" +
//        "<h2 align=\"center\">" + date.toString() + "</h2>\n" +
//        "</body></html>");
//  }
//}
//现在，让我们来编译上述 servlet 并在 web.xml 文件中创建适当的条目，
// 然后使用 URL http://localhost:8080/CurrentDate 来调用该 servlet。这将会产生如下所示的结果：

//Display Current Date & Time

//Mon Jun 21 21:46:49 GMT+04:00 2010

//尝试刷新 URLhttp://localhost:8080/CurrentDate，每隔几秒刷新一次你都会发现显示时间的差异。

//日期比较
//正如我上面所提到的一样，你可以在 Servlet 中使用所有可用的 Java 方法。
// 如果你需要比较两个日期，以下是方法：

//你可以使用 getTime() 来获取两个对象自 1970 年 1 月 1 日凌晨零点以来经过的毫秒数，然后比较这两个值。

//你可以使用方法 before( )、after( ) 和 equals( )。
// 由于一个月里 12 号在 18 号之前，例如，new Date(99, 2, 12).before(new Date (99, 2, 18)) 返回 true。

//你可以使用 compareTo( ) 方法，该方法由 Comparable 接口定义并由 Date 实现。
//使用 SimpleDateFormat 格式化日期
//SimpleDateFormat 是一个以语言环境敏感的方式来格式化和解析日期的具体类。
// SimpleDateFormat 允许你通过为日期时间格式化选择任何用户定义的模式开始。

//让我们修改上述例子，如下所示：

//// Import required java libraries
//import java.io.*;
//import java.text.*;
//import java.utils.Date;
//import javax.servlet.*;
//import javax.servlet.http.*;
//// Extend HttpServlet class
//public class CurrentDate extends HttpServlet {
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Display Current Date & Time";
//      Date dNow = new Date( );
//      SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//      String docType =
//      "<!doctype html public \"-//w3c//dtd html 4.0 " +
//      "transitional//en\">\n";
//      out.println(docType +
//        "<html>\n" +
//        "<head><title>" + title + "</title></head>\n" +
//        "<body bgcolor=\"#f0f0f0\">\n" +
//        "<h1 align=\"center\">" + title + "</h1>\n" +
//        "<h2 align=\"center\">" + ft.format(dNow) + "</h2>\n" +
//        "</body></html>");
//  }
//}
//再次编译上述 servlet，然后使用 URL http://localhost:8080/CurrentDate 来调用该 servlet。
// 这将会产生如下所示的结果：

//Display Current Date & Time

//Mon 2010.06.21 at 10:06:44 PM GMT+04:00

//简单的日期格式的格式代码
//要指定时间格式，那么使用时间模式的字符串。
// 在这种模式下，所有的 ASCII 字母被保留为模式字母，这些字母定义如下：

//字符	    描述	                        实例
//G	    时代指示器	                    AD
//y	    四位数的年	                    2001
//M	    一年中的月	                    July 或 07
//d	    一月中的第几天	                10
//h	    带有 A.M./P.M. 的小时（1~12）	    12
//H	    一天中的第几小时（0~23）	        22
//m	    一小时中的第几分	                30
//s	    一分中的第几秒	                55
//S	    毫秒	                            234
//E	    一周中的星期几	                Tuesday
//D	    一年中的第几天	                360
//F	    一个月中的某一周的某一天	        2 (second Wed. in July)
//w	    一年中的第几周	                40
//W	    一月中的第几周	                1
//a	    A.M./P.M. 标记	                PM
//k	    一天中的第几小时（1~24）	        24
//K	    带有 A.M./P.M. 的小时（0~11）	    10
//z	    时区	Eastern Standard Time
//'	    Escape for text	分隔符
//"	    单引号	`

//处理日期方法的可用方法的完整列表，你可以参考标准的 Java 文档。
}
