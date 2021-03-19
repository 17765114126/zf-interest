package study.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//四、Request对象实现请求转发

/**
 * @ClassName RequestFive
 * @Author zhaofu
 * @Date 2020/5/21
 * @Version V1.0
 **/
public class RequestFive请求转发 extends HttpServlet {

    //4.1、请求转发的基本概念
    //　　请求转发：指一个web资源收到客户端请求后，通知服务器去调用另外一个web资源进行处理。
    //　　请求转发的应用场景：MVC设计模式

    //　　在Servlet中实现请求转发的两种方式：

    //　1、通过ServletContext的getRequestDispatcher(String path)方法，该方法返回一个RequestDispatcher对象，
    //    调用这个对象的forward方法可以实现请求转发。

    //   例如：将请求转发的test.jsp页面

    //   RequestDispatcher reqDispatcher =this.getServletContext().getRequestDispatcher("/test.jsp");
    //   reqDispatcher.forward(request, response);

    //　2、通过request对象提供的getRequestDispatche(String path)方法，该方法返回一个RequestDispatcher对象，
    //    调用这个对象的forward方法可以实现请求转发。

    //   例如：将请求转发的test.jsp页面

    // request.getRequestDispatcher("/test.jsp").forward(request, response);
    // request对象同时也是一个域对象(Map容器)，开发人员通过request对象在实现转发时，把数据通过request对象带给其它web资源处理。

    //例如：RequestFive Servlet，RequestFive将请求转发到test.jsp页面
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String data = "JavaWeb";
        /**
         * 将数据存放到request对象中,此时把request对象当作一个Map容器来使用
         */
        request.setAttribute("data", data);

        //客户端访问RequestFive这个Servlet后，RequestFive通知服务器将请求转发(forward)到test.jsp页面进行处理
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    //test.jsp页面代码如下：

    //  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

    //  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    //  <html>
    //    <head>
    //      <title>Request对象实现请求转发</title>
    //    </head>

    //    <body>
    //       使用普通方式取出存储在request对象中的数据：
    //       <h3 style="color:red;"><%=(String)request.getAttribute("data")%></h3>
    //      使用EL表达式取出存储在request对象中的数据：
    //      <h3 style="color:red;">${data}</h3>
    //   </body>
    // </html>


    //　　request对象作为一个域对象(Map容器)使用时，主要是通过以下的四个方法来操作

    //setAttribute(String name,Object o)方法，将数据作为request对象的一个属性存放到request对象中，
    //例如：request.setAttribute("data", data);

    //getAttribute(String name)方法，获取request对象的name属性的属性值，
    //例如：request.getAttribute("data")

    //removeAttribute(String name)方法，移除request对象的name属性，
    //例如：request.removeAttribute("data")

    //getAttributeNames方法，获取request对象的所有属性名，返回的是一个，
    //例如：Enumeration<String> attrNames = request.getAttributeNames();

    //4.2、请求重定向和请求转发的区别
    //　　一个web资源收到客户端请求后，通知服务器去调用另外一个web资源进行处理，称之为请求转发/307。
    //　　一个web资源收到客户端请求后，通知浏览器去访问另外一个web资源进行处理，称之为请求重定向/302。

}
