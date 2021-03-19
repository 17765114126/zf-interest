package study.Servlet;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 文件上传 {
//Servlet 可以与 HTML form 标签一起使用允许用户将文件上传到服务器。
// 上传的文件可以是文本文件或图像文件或任何文档。

//创建一个文件上传表单
//下述 HTML 代码创建了一个文件上传表单。以下是需要注意的几点：

//表单 method 属性应该设置为 POST 方法且不能使用 GET 方法。

//表单 enctype 属性应该设置为 multipart/form-data.

//表单 action 属性应该设置为 servlet 文件，能够在后端服务器处理文件上传。
// 下面的例子是使用 UploadServlet servlet 来上传文件的。

//要上传单个文件，你应该使用单个带有属性 type=“file” 的 <input .../> 标签。
// 为了允许多个文件上传，要包含多个带有 name 属性不同值的输入标签。
// 浏览器将把一个浏览按钮和每个输入标签关联起来。

//<html>
//<head>
//<title>File Uploading Form</title>
//</head>
//<body>
//<h3>File Upload:</h3>
//Select a file to upload: <br />
//<form action="UploadServlet" method="post"
//                        enctype="multipart/form-data">
//<input type="file" name="file" size="50" />
//<br />
//<input type="submit" value="Upload File" />
//</form>
//</body>
//</html>
//这将显示如下所示的结果，允许从本地计算机中选择一个文件，
// 当用户点击“上传文件”时，表单会和选择的文件一起提交：

//File Upload:
//Select a file to upload:

//NOTE: This is just dummy form and would not work.
//编写后台 Servlet
//以下是 servlet UploadServlet，
// 会接受上传的文件并把它储存在目录 <Tomcat-installation-directory>/webapps/data 中。
// 使用外部配置，如 web.xml 中的 context-param 元素，这个目录名也可以被添加，如下所示：

//<web-app>
//....
//<context-param>
//    <description>Location to store uploaded file</description>
//    <param-name>file-upload</param-name>
//    <param-value>
//         c:\apache-tomcat-5.5.29\webapps\data\
//     </param-value>
//</context-param>
//....
//</web-app>
//以下是 UploadServlet 的源代码，可以一次处理多个文件的上传。在继续操作之前，请确认下列各项：

//下述例子依赖于 FileUpload，
// 所以一定要确保在你的 classpath 中有最新版本的 commons-fileupload.x.x.jar 文件。
// 你可以从 http://commons.apache.org/fileupload/ 中下载。

//FileUpload 依赖于 Commons IO，所以一定要确保在你的 classpath 中有最新版本的 commons-io-x.x.jar 文件。
// 可以从 http://commons.apache.org/io/ 中下载。

//在测试下面实例时，你上传的文件大小不能大于 maxFileSize，否则文件将无法上传。

//请确保已经提前创建好目录 c:\temp and c:\apache-tomcat-5.5.29\webapps\data。
//// Import required java libraries
//import java.io.*;
//import java.utils.*;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.io.output.*;
//public class UploadServlet extends HttpServlet {
//   private boolean isMultipart;
//   private String filePath;
//   private int maxFileSize = 50 * 1024;
//   private int maxMemSize = 4 * 1024;
//   private File file ;
//   public void init( ){
//      // Get the file location where it would be stored.
//      filePath = getServletContext().getInitParameter("file-upload");
//   }
//   public void doPost(HttpServletRequest request,
//               HttpServletResponse response)
//              throws ServletException, java.io.IOException {
//      // Check that we have a file upload request
//      isMultipart = ServletFileUpload.isMultipartContent(request);
//      response.setContentType("text/html");
//      java.io.PrintWriter out = response.getWriter( );
//      if( !isMultipart ){
//         out.println("<html>");
//         out.println("<head>");
//         out.println("<title>Servlet upload</title>");
//         out.println("</head>");
//         out.println("<body>");
//         out.println("<p>No file uploaded</p>");
//         out.println("</body>");
//         out.println("</html>");
//         return;
//      }
//      DiskFileItemFactory factory = new DiskFileItemFactory();
//      // maximum size that will be stored in memory
//      factory.setSizeThreshold(maxMemSize);
//      // Location to save data that is larger than maxMemSize.
//      factory.setRepository(new File("c:\\temp"));
//      // Create a new file upload handler
//      ServletFileUpload upload = new ServletFileUpload(factory);
//      // maximum file size to be uploaded.
//      upload.setSizeMax( maxFileSize );
//      try{
//      // Parse the request to get file items.
//      List fileItems = upload.parseRequest(request);
//      // Process the uploaded file items
//      Iterator i = fileItems.iterator();
//      out.println("<html>");
//      out.println("<head>");
//      out.println("<title>Servlet upload</title>");
//      out.println("</head>");
//      out.println("<body>");
//      while ( i.hasNext () )
//      {
//         FileItem fi = (FileItem)i.next();
//         if ( !fi.isFormField () )
//         {
//            // Get the uploaded file parameters
//            String fieldName = fi.getFieldName();
//            String fileName = fi.getName();
//            String contentType = fi.getContentType();
//            boolean isInMemory = fi.isInMemory();
//            long sizeInBytes = fi.getSize();
//            // Write the file
//            if( fileName.lastIndexOf("\\") >= 0 ){
//               file = new File( filePath +
//               fileName.substring( fileName.lastIndexOf("\\"))) ;
//            }else{
//               file = new File( filePath +
//               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
//            }
//            fi.write( file ) ;
//            out.println("Uploaded Filename: " + fileName + "<br>");
//         }
//      }
//      out.println("</body>");
//      out.println("</html>");
//   }catch(Exception ex) {
//       System.out.println(ex);
//   }
//   }
//   public void doGet(HttpServletRequest request,
//                       HttpServletResponse response)
//        throws ServletException, java.io.IOException {
//        throw new ServletException("GET method used with " +
//                getClass( ).getName( )+": POST method required.");
//   }
//}
//编译和运行 Servlet
//编译上述 servlet UploadServlet 并在 web.xml 文件中创建所需的条目，如下所示：

//<servlet>
//   <servlet-name>UploadServlet</servlet-name>
//   <servlet-class>UploadServlet</servlet-class>
//</servlet>
//<servlet-mapping>
//   <servlet-name>UploadServlet</servlet-name>
//   <url-pattern>/UploadServlet</url-pattern>
//</servlet-mapping>
//现在尝试使用上面创建的 HTML 表单来上传文件。
// 当你访问 http://localhost:8080/UploadFile.htm 时，它会显示如下所示的结果，
// 这将有助于你从本地计算机中上传任何文件。


//File Upload:
//Select a file to upload:

//如果你的 servelt 脚本能正常工作，那么你的文件会被上传到 c:\apache-tomcat-5.5.29\webapps\data\ 目录中
}
