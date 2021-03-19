package study.Servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 点击计数器 {
//Web 页面的点击计数器
//很多时候，你可能有兴趣知道你网站的某个特定页面上的总点击量。
// 使用 servlet 来计算这些点击量是非常简单的，因为一个 servlet 的生命周期是由它运行的容器控制的。

//以下是基于 Servlet 生命周期实现一个简单的页面点击计数器需要的步骤：

//在 init() 方法中初始化一个全局变量。

//每次调用 doGet() 或 doPost() 方法时，增加全局变量。

//如果需要，你可以使用一个数据库表来存储 destroy() 方法中的全局变量。
// 在下次初始化 servlet 时，这个值可以在 init() 方法内被读取。这一步是可选的。

//如果你想计算一个会话内一个页面的点量击，那么你可以使用 isNew() 方法来查看该会话内是否已点击过相同的页面。
// 这一步是可选的。

//你可以显示全局计数器的值来显示网站中的总点击量。这一步是可选的。
//在这里我假设 web 容器不会被重新启动。
// 如果 web 容器被重新启动或 servlet 被销毁，计数器将被重置。

//实例
//这个例子演示了如何实现一个简单的页面点击计数器：

    public class PageHitCounter extends HttpServlet {
        private int hitCount;

        public void init() {
            // Reset hit counter.
            hitCount = 0;
        }

        public void doGet(HttpServletRequest request,
                          HttpServletResponse response)
                throws ServletException, IOException {
            // Set response content type
            response.setContentType("text/html");
            // This method executes whenever the servlet is hit
            // increment hitCount
            hitCount++;
            PrintWriter out = response.getWriter();
            String title = "Total Number of Hits";
            String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
                            "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<h2 align=\"center\">" + hitCount + "</h2>\n" +
                    "</body></html>");
        }

        public void destroy() {
            // This is optional step but if you like you
            // can write hitCount value in your database.
        }
    }
//现在让我们来编译上述 servlet 并在 web.xml 文件中创建以下条目：

//....
// <servlet>
//     <servlet-name>PageHitCounter</servlet-name>
//     <servlet-class>PageHitCounter</servlet-class>
// </servlet>
// <servlet-mapping>
//     <servlet-name>PageHitCounter</servlet-name>
//     <url-pattern>/PageHitCounter</url-pattern>
// </servlet-mapping>
//....
//现在使用 URL http://localhost:8080/PageHitCounter 来调用这个 servlet。
// 每次页面刷新时，计数器的值都会加 1，这将产生如下所示的结果：

//Total Number of Hits

//6

//网站点击计数器
//很多时候，你可能有兴趣知道你整个网站的总点击量。
// 在 Servlet 中，这也是非常简单的，我们可以使用过滤器实现这一点。

//以下是实现一个基于过滤器生命周期的简单的网站点击计数器需要的步骤：

//在过滤器的 init() 方法中初始化一个全局变量。

//每次调用 doFilter 方法时，增加全局变量。

//如果需要，你可以使用一个数据库表来存储过滤器的 destroy() 方法中的全局变量的值。
// 在下次初始化过滤器时，该值可以在 init() 方法内被读取。这一步是可选的。
//在这里我假设 web 容器不会被重新启动。如果 web 容器被重新启动或 servlet 被销毁，点击计数器将被重置。

//实例
//这个例子演示了如何实现一个简单的网站点击计数器：

    public class SiteHitCounter implements Filter {
        private int hitCount;

        public void init(FilterConfig config)
                throws ServletException {
            // Reset hit counter.
            hitCount = 0;
        }

        public void doFilter(ServletRequest request,
                             ServletResponse response,
                             FilterChain chain)
                throws java.io.IOException, ServletException {
            // increase counter by one
            hitCount++;
            // Print the counter.
            System.out.println("Site visits count :" + hitCount);
            // Pass request back down the filter chain
            chain.doFilter(request, response);
        }

        public void destroy() {
            // This is optional step but if you like you
            // can write hitCount value in your database.
        }
    }
//现在让我们来编译上述 servlet 并在 web.xml 文件中创建以下条目：

//....
//<filter>
//   <filter-name>SiteHitCounter</filter-name>
//   <filter-class>SiteHitCounter</filter-class>
//</filter>
//<filter-mapping>
//   <filter-name>SiteHitCounter</filter-name>
//   <url-pattern>/*</url-pattern>
//</filter-mapping>
//....
//现在调用任意 URL 如 URLhttp://localhost:8080/。
// 每次任意页面被点击时，计数器的值都会加 1 并且会在日志中显示如下所示的消息：
//
//Site visits count : 1
//Site visits count : 2
//Site visits count : 3
//Site visits count : 4
//Site visits count : 5
//..................
}
