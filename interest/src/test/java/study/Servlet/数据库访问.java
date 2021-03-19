package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 数据库访问 {
//本教程假定你已经了解了 JDBC 应用程序的工作方式。
// 在你开始学习 servlet 数据库访问之前，确保你已经有适当的 JDBC 环境设置和数据库。

//关于如何使用 JDBC 访问数据库及其环境配置的更多细节，你可以查看我们的 JDBC 教程。

//从基本概念开始，让我们创建一个简单的表，并在表中创建几条记录，如下所示：

//创建表
//在 TEST 数据库中创建 Employees 表，请按以下步骤进行：

//步骤 1
//打开 Command Prompt，并改为安装目录，如下所示：

//C:\>
//C:\>cd Program Files\MySQL\bin
//C:\Program Files\MySQL\bin>
//步骤 2
//登录数据库，如下所示：

//C:\Program Files\MySQL\bin>mysql -u root -p
//Enter password: ********
//mysql>
//步骤 3
//在 TEST 数据库中创建 Employee 表，如下所示：

//mysql> use TEST;
//mysql> create table Employees
//    (
//     id int not null,
//     age int not null,
//     first varchar (255),
//     last varchar (255)
//    );
//Query OK, 0 rows affected (0.08 sec)
//mysql>
//创建数据记录
//最后在 Employee 表中创建几条记录，如下所示：

//mysql> INSERT INTO Employees VALUES (100, 18, 'Zara', 'Ali');
//Query OK, 1 row affected (0.05 sec)
//mysql> INSERT INTO Employees VALUES (101, 25, 'Mahnaz', 'Fatma');
//Query OK, 1 row affected (0.00 sec)
//mysql> INSERT INTO Employees VALUES (102, 30, 'Zaid', 'Khan');
//Query OK, 1 row affected (0.00 sec)
//mysql> INSERT INTO Employees VALUES (103, 28, 'Sumit', 'Mittal');
//Query OK, 1 row affected (0.00 sec)
//mysql>
//访问数据库
//这里的例子演示了如何使用 Servlet 访问 TEST 数据库。

//// Loading required libraries
//import java.io.*;
//import java.utils.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//import java.sql.*;
//public class DatabaseAccess extends HttpServlet{
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // JDBC driver name and database URL
//      static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
//      static final String DB_URL="jdbc:mysql://localhost/TEST";
//      //  Database credentials
//      static final String USER = "root";
//      static final String PASS = "password";
//      // Set response content type
//      response.setContentType("text/html");
//      PrintWriter out = response.getWriter();
//      String title = "Database Result";
//      String docType =
//        "<!doctype html public \"-//w3c//dtd html 4.0 " +
//         "transitional//en\">\n";
//         out.println(docType +
//         "<html>\n" +
//         "<head><title>" + title + "</title></head>\n" +
//         "<body bgcolor=\"#f0f0f0\">\n" +
//         "<h1 align=\"center\">" + title + "</h1>\n");
//      try{
//         // Register JDBC driver
//         Class.forName("com.mysql.jdbc.Driver");
//         // Open a connection
//         conn = DriverManager.getConnection(DB_URL,USER,PASS);
//         // Execute SQL query
//         stmt = conn.createStatement();
//         String sql;
//         sql = "SELECT id, first, last, age FROM Employees";
//         ResultSet rs = stmt.executeQuery(sql);
//         // Extract data from result set
//         while(rs.next()){
//            //Retrieve by column name
//            int id  = rs.getInt("id");
//            int age = rs.getInt("age");
//            String first = rs.getString("first");
//            String last = rs.getString("last");
//            //Display values
//            out.println("ID: " + id + "<br>");
//            out.println(", Age: " + age + "<br>");
//            out.println(", First: " + first + "<br>");
//            out.println(", Last: " + last + "<br>");
//         }
//         out.println("</body></html>");
//         // Clean-up environment
//         rs.close();
//         stmt.close();
//         conn.close();
//      }catch(SQLException se){
//         //Handle errors for JDBC
//         se.printStackTrace();
//      }catch(Exception e){
//         //Handle errors for Class.forName
//         e.printStackTrace();
//      }finally{
//         //finally block used to close resources
//         try{
//            if(stmt!=null)
//               stmt.close();
//         }catch(SQLException se2){
//         }// nothing we can do
//         try{
//            if(conn!=null)
//            conn.close();
//         }catch(SQLException se){
//            se.printStackTrace();
//         }//end finally try
//      } //end try
//   }
//}
//现在让我们来编译述 servlet 并在 web.xml 文件中创建以下条目：

//....
// <servlet>
//     <servlet-name>DatabaseAccess</servlet-name>
//     <servlet-class>DatabaseAccess</servlet-class>
// </servlet>
// <servlet-mapping>
//     <servlet-name>DatabaseAccess</servlet-name>
//     <url-pattern>/DatabaseAccess</url-pattern>
// </servlet-mapping>
//....
//现在使用 URL http://localhost:8080/DatabaseAccess 调用这个 servlet，将显示如下所示响应：

//Database Result

//ID: 100, Age: 18, First: Zara, Last: Ali
//ID: 101, Age: 25, First: Mahnaz, Last: Fatma
//ID: 102, Age: 30, First: Zaid, Last: Khan
//ID: 103, Age: 28, First: Sumit, Last: Mittal
}
