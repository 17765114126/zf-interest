package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 发送电子邮件 {
//使用 Servlet 发送一封电子邮件是非常简单的，
// 但是开始之前你必须在你的计算机上安装 JavaMail API 和 Java Activation Framework(JAF)。

//你可以从 Java 标准网站下载最新版本的 JavaMail版本 1.2

//你可以从 Java 标准网站下载最新版本的 JAF版本 1.1.1
//下载并解压缩这些文件，在新创建的顶级目录中你会发现这两个应用程序的一些 jar 文件。
// 你需要把 mail.jar 和 activation.jar 文件添加到你的 CLASSPATH 中。

//发送一封简单的电子邮件
//这是从你的计算机上发送一封简单的电子邮件的实例。
// 这里假设你的本地主机已连接到互联网并可以发送电子邮件。
// 同时确保来自 Java Email API 包和 JAF 包的所有的 jar 文件在 CLASSPATH 中是可用的。

//// File Name SendEmail.java
//import java.io.*;
//import java.utils.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
//public class SendEmail extends HttpServlet{
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Recipient's email ID needs to be mentioned.
//      String to = "abcd@gmail.com";
//      // Sender's email ID needs to be mentioned
//      String from = "web@gmail.com";
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//      // Get system properties
//      Properties properties = System.getProperties();
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      try{
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO,
//                                  new InternetAddress(to));
//         // Set Subject: header field
//         message.setSubject("This is the Subject Line!");
//         // Now set the actual message
//         message.setText("This is actual message");
//         // Send message
//         Transport.send(message);
//         String title = "Send Email";
//         String res = "Sent message successfully....";
//         String docType =
//         "<!doctype html public \"-//w3c//dtd html 4.0 " +
//         "transitional//en\">\n";
//         out.println(docType +
//         "<html>\n" +
//         "<head><title>" + title + "</title></head>\n" +
//         "<body bgcolor=\"#f0f0f0\">\n" +
//         "<h1 align=\"center\">" + title + "</h1>\n" +
//         "<p align=\"center\">" + res + "</p>\n" +
//         "</body></html>");
//      }catch (MessagingException mex) {
//         mex.printStackTrace();
//      }
//   }
//}
//现在让我们来编译上述 servlet 并在 web.xml 文件中创建以下条目：

//....
// <servlet>
//     <servlet-name>SendEmail</servlet-name>
//     <servlet-class>SendEmail</servlet-class>
// </servlet>
// <servlet-mapping>
//     <servlet-name>SendEmail</servlet-name>
//     <url-pattern>/SendEmail</url-pattern>
// </servlet-mapping>
//....
//现在使用 URL http://localhost:8080/SendEmail 来调用这个 servlet。
// 这将会给给定的电子邮件 ID abcd@gmail.com 发送一封电子邮件并将显示如下所示的响应：

//Send Email

//Sent message successfully....


//如果你想把一封电子邮件发送给多个收件人，那么使用下面的方法来指定多个电子邮件 ID：

//void addRecipients(Message.RecipientType type,
//                   Address[] addresses)
//throws MessagingException
//这里是对参数的描述：

//type：这将被设置为 TO、CC 或 BCC。这里 CC 代表抄送且 BCC 代表密件抄送。
// 例如 Message.RecipientType.TO。

//addresses：这是电子邮件 ID 的数组。
// 当指定电子邮件 ID 时，你需要使用 InternetAddress() 方法。
//发送一封 HTML 电子邮件

//这个例子将从你的计算机上发送一封 HTML 电子邮件。
// 这里假设你的本地主机已连接到互联网且可以发送电子邮件。
// 同时确保来自 Java Email API 包和 JAF 包的所有的 jar 文件在 CLASSPATH 中都是可用的。

//这个例子与上一个例子非常相似，除了在这里我们使用的是 setContent() 方法来设置内容，
// 它的第二个参数为 “text/html” 用来指定 HTML 内容是包含在消息中的。

//使用这个实例，你可以发送内容大小不限的 HTML 内容。

//// File Name SendEmail.java
//import java.io.*;
//import java.utils.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
//public class SendEmail extends HttpServlet{
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Recipient's email ID needs to be mentioned.
//      String to = "abcd@gmail.com";
//      // Sender's email ID needs to be mentioned
//      String from = "web@gmail.com";
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//      // Get system properties
//      Properties properties = System.getProperties();
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      try{
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO,
//                                  new InternetAddress(to));
//         // Set Subject: header field
//         message.setSubject("This is the Subject Line!");
//         // Send the actual HTML message, as big as you like
//         message.setContent("<h1>This is actual message</h1>",
//                            "text/html" );
//         // Send message
//         Transport.send(message);
//         String title = "Send Email";
//         String res = "Sent message successfully....";
//         String docType =
//         "<!doctype html public \"-//w3c//dtd html 4.0 " +
//         "transitional//en\">\n";
//         out.println(docType +
//         "<html>\n" +
//         "<head><title>" + title + "</title></head>\n" +
//         "<body bgcolor=\"#f0f0f0\">\n" +
//         "<h1 align=\"center\">" + title + "</h1>\n" +
//         "<p align=\"center\">" + res + "</p>\n" +
//         "</body></html>");
//      }catch (MessagingException mex) {
//         mex.printStackTrace();
//      }
//   }
//}
//编译并运行上述 servlet 来在给定的电子邮件 ID 上发送 HTML 消息。

//在电子邮件中发送附件
//这里的例子将从你的计算机上发送一封带有附件的电子邮件。
// 这里假设你的本地主机已连接到互联网并能够发送电子邮件。
// 同时确保来自 Java Email API 包和 JAF 包的所有的 jar 文件在 CLASSPATH 中都是可用的。

//// File Name SendEmail.java
//import java.io.*;
//import java.utils.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;
//public class SendEmail extends HttpServlet{
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Recipient's email ID needs to be mentioned.
//      String to = "abcd@gmail.com";
//      // Sender's email ID needs to be mentioned
//      String from = "web@gmail.com";
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//      // Get system properties
//      Properties properties = System.getProperties();
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//       try{
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO,
//                                  new InternetAddress(to));
//         // Set Subject: header field
//         message.setSubject("This is the Subject Line!");
//         // Create the message part
//         BodyPart messageBodyPart = new MimeBodyPart();
//         // Fill the message
//         messageBodyPart.setText("This is message body");
//         // Create a multipar message
//         Multipart multipart = new MimeMultipart();
//         // Set text message part
//         multipart.addBodyPart(messageBodyPart);
//         // Part two is attachment
//         messageBodyPart = new MimeBodyPart();
//         String filename = "file.txt";
//         DataSource source = new FileDataSource(filename);
//         messageBodyPart.setDataHandler(new DataHandler(source));
//         messageBodyPart.setFileName(filename);
//         multipart.addBodyPart(messageBodyPart);
//         // Send the complete message parts
//         message.setContent(multipart );
//         // Send message
//         Transport.send(message);
//         String title = "Send Email";
//         String res = "Sent message successfully....";
//         String docType =
//         "<!doctype html public \"-//w3c//dtd html 4.0 " +
//         "transitional//en\">\n";
//         out.println(docType +
//         "<html>\n" +
//         "<head><title>" + title + "</title></head>\n" +
//         "<body bgcolor=\"#f0f0f0\">\n" +
//         "<h1 align=\"center\">" + title + "</h1>\n" +
//         "<p align=\"center\">" + res + "</p>\n" +
//         "</body></html>");
//      }catch (MessagingException mex) {
//         mex.printStackTrace();
//      }
//   }
//}
//编译并运行上面的 servlet 来在给定的电子邮件 ID 上发送附件文件和消息。

//用户身份认证部分
//如果需要向电子邮件服务器提供用户 ID 和密码来进行身份认证，那么你可以设置如下所示的属性：

//props.setProperty("mail.user", "myuser");
// props.setProperty("mail.password", "mypwd");
//电子邮件发送机制的其余部分与上面解释的一致。
}
