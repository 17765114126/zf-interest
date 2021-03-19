package study.request;

//三、request接收表单提交中文参数乱码问题

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestFour乱码问题 {

    //.1、以POST方式提交表单中文参数的乱码问题
    //例如有如下的form表单页面：

    //  <%@ page language="java" import="java.utils.*" pageEncoding="UTF-8"%>
    //
    //  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    //  <html>
    //    <head>
    //      <title>request接收中文参数乱码问题</title>
    //    </head>
    //
    //    <body>
    //       <form action="<%=request.getContextPath()%>/servlet/RequestDemo04" method="post">
    //           用户名：<input type="text" name="userName"/>
    //           <input type="submit" value="post方式提交表单">
    //       </form>
    //   </body>
    // </html>


    //　　此时在服务器端接收中文参数时就会出现中文乱码


    //3.2、post方式提交中文数据乱码产生的原因和解决办法


    //　　可以看到，之所以会产生乱码，就是因为服务器和客户端沟通的编码不一致造成的，因此解决的办法是：
    //   在客户端和服务器之间设置一个统一的编码，之后就按照此编码进行数据的传输和接收。

    //　　由于客户端是以UTF-8字符编码将表单数据传输到服务器端的，因此服务器也需要设置以UTF-8字符编码进行接收，要想完成此操作，
    //   服务器可以直接使用从ServletRequest接口继承而来的"setCharacterEncoding(charset)"方法进行统一的编码设置。修改后的代码如下：

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 客户端是以UTF-8编码传输数据到服务器端的，所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
         */
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        System.out.println("userName：" + userName);
    }
    //　　使用request.setCharacterEncoding("UTF-8");设置服务器以UTF-8的编码接收数据后，此时就不会产生中文乱码问题了


    //3.3、以GET方式提交表单中文参数的乱码问题


    //例如有如下的form表单页面：


    //  <%@ page language="java" import="java.utils.*" pageEncoding="UTF-8"%>

    //  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    //  <html>
    //    <head>
    //      <title>request接收中文参数乱码问题</title>
    //    </head>

    //    <body>
    //         <form action="${pageContext.request.contextPath}/servlet/RequestDemo04" method="get">
    //           姓名：<input type="text" name="name"/>
    //           <input type="submit" value="get方式提交表单">
    //       </form>
    //   </body>
    // </html>


    //　　此时在服务器端接收中文参数时就会出现中文乱码

    //　　那么这个中文乱码问题又该如何解决呢，是否可以通过request.setCharacterEncoding("UTF-8");
    //   设置服务器以UTF-8的编码进行接收这种方式来解决中文乱码问题呢，
    //   注意，对于以get方式传输的中文数据，通过request.setCharacterEncoding("UTF-8");
    //   这种方式是解决不了中文乱码问题


    //3.4、get方式提交中文数据乱码产生的原因和解决办法
    //　　对于以get方式传输的数据，request即使设置了以指定的编码接收数据也是无效的(至于为什么无效我也没有弄明白)，
    //   默认的还是使用ISO8859-1这个字符编码来接收数据，客户端以UTF-8的编码传输数据到服务器端，
    //   而服务器端的request对象使用的是ISO8859-1这个字符编码来接收数据，服务器和客户端沟通的编码不一致因此才会产生中文乱码的。
    //   解决办法：在接收到数据后，先获取request对象以ISO8859-1字符编码接收到的原始数据的字节数组，然后通过字节数组以指定的编码构建字符串，解决乱码问题。
    //   代码如下：


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         *
         * 对于以get方式传输的数据，request即使设置了以指定的编码接收数据也是无效的，默认的还是使用ISO8859-1这个字符编码来接收数据
         */
        //接收数据
        String name = request.getParameter("name");
        //获取request对象以ISO8859-1字符编码接收到的原始数据的字节数组，然后通过字节数组以指定的编码构建字符串，解决乱码问题
        name = new String(name.getBytes("ISO8859-1"), "UTF-8");

        System.out.println("name：" + name);
    }


    //3.5、以超链接形式传递中文参数的乱码问题

    //　　客户端想传输数据到服务器，可以通过表单提交的形式，也可以通过超链接后面加参数的形式，例如：

    // <a href="${pageContext.request.contextPath}/servlet/RequestDemo05?userName=gacl&name=徐达沛">点击</a>
    //　　点击超链接，数据是以get的方式传输到服务器的，所以接收中文数据时也会产生中文乱码问题，
    //   而解决中文乱码问题的方式与上述的以get方式提交表单中文数据乱码处理问题的方式一致

    //1 String name = request.getParameter("name");
    //2 name =new String(name.getBytes("ISO8859-1"), "UTF-8");
    //　另外，需要提的一点就是URL地址后面如果跟了中文数据，那么中文参数最好使用URL编码进行处理，如下所示：

    // <a href="${pageContext.request.contextPath}/servlet/RequestDemo05?userName=gacl&name=<%=URLEncoder.encode("徐达沛", "UTF-8")%>">点击</a>


    //3.6、提交中文数据乱码问题总结
    //　　1、如果提交方式为post，想不乱码，只需要在服务器端设置request对象的编码即可，客户端以哪种编码提交的，服务器端的request对象就以对应的编码接收，
    //      比如客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding("UTF-8"))
    //
    //　　2、如果提交方式为get，设置request对象的编码是无效的，request对象还是以默认的ISO8859-1编码接收数据，
    //      因此要想不乱码，只能在接收到数据后再手工转换，
    //      步骤如下：
    //
    //　　1).获取获取客户端提交上来的数据，得到的是乱码字符串,data="???è?????"
    //
    //　　 String data = request.getParameter("paramName");
    //
    //　　2).查找ISO8859-1码表，得到客户机提交的原始数据的字节数组
    //
    //　　 byte[] source = data.getBytes("ISO8859-1");
    //
    //　　3).通过字节数组以指定的编码构建字符串，解决乱码
    //
    //　　 data = new String(source, "UTF-8");
    //
    //　　通过字节数组以指定的编码构建字符串，这里指定的编码是根据客户端那边提交数据时使用的字符编码来定的，
    //   如果是GB2312，那么就设置成data = new String(source, "GB2312")，如果是UTF-8，那么就设置成data = new String(source, "UTF-8")
}
