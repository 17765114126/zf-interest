package study.io;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class utils {

    /**
     * 下载文件到浏览器
     *
     * @param response
     * @param response
     * @throws IOException
     */
    public void downFile(HttpServletResponse response, InputStream inputStream) throws IOException {
        OutputStream out = null;
        try {
            File f = new File("E:\\20210611-购车合同（713起新版本）_2884799374418773001.pdf");
            InputStream is = new FileInputStream(f);

            // 设置下载文件的mineType，告诉浏览器下载文件类型
            response.setContentType("application/pdf");
            // 设置一个响应头，无论是否被浏览器解析，都下载
            response.setCharacterEncoding("UTF-8");
            // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
            String fileName = "汽车购买协议—无章版" + ".pdf";
            fileName = URLEncoder.encode(fileName, "UTF-8");
            // 设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//            response.setHeader("Cache-Control", "no-cache");

            // 将要下载的文件内容通过输出流写到浏览器
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

}
