package com.example.springboot.utils.aliyun;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.*;
import com.example.springboot.utils.date.DateUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class ALiYunUtil {

    public static final String ACCESS_ID = "";
    public static final String ACCESS_KEY = "";
    //imgpackage.oss-cn-shanghai.aliyuncs.com
    public static final String OSS_ENDPOINT = "";
    public static final String IMAGE_URL = "https://oss.dorago.cn/";
    public static final String BUCKET_NAME = "";
    public static final String SEPARATOR = "/";
    public static final String CMS_PATH = "";
    public static final String CMS_PATH_START = "c";
    public static final String Business_PATH = "";
    public static final String Business_PATH_START = "b";
    public static final String MSC_PATH = "msc";
    public static final String MSC_PATH_START = "m";
    private static final OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);


    /**
     * 上传Object
     *
     * @param bucketName 名称
     * @param key        相对路径
     * @param file       文件
     * @return
     */
    public String putObject(String bucketName, String key, MultipartFile file) {

        // 初始化OSSClient
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);

        // 获取指定文件的输入流
        InputStream content = null;
        try {
            content = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(file.getSize());
        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, key, content, meta);
        // 打印ETag
        System.out.println(result.getETag());

        return result.toString();
    }

    /**
     * 新建bucket
     *
     * @param bucketName 名称
     * @throws OSSException
     * @throws ClientException
     */
    static void ensureBucket(String bucketName)
            throws OSSException, ClientException {
        // 初始化OSSClient
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
        try {
            // 创建bucket
            client.createBucket(bucketName);
        } catch (ServiceException e) {
            /*if (!OSSErrorCode.BUCKES_ALREADY_EXISTS.equals(e.getErrorCode())) {
                // 如果Bucket已经存在，则忽略
                throw e;
            }*/
            throw e;
        }
    }

    /**
     * 删除一个Bucket和其中的Objects
     *
     * @param bucketName
     * @throws OSSException
     * @throws ClientException
     */
    public static void deleteBucket(String bucketName)
            throws OSSException, ClientException {
        // 初始化OSSClient
        OSSClient client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
        ObjectListing ObjectListing = client.listObjects(bucketName);
        List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
        for (int i = 0; i < listDeletes.size(); i++) {
            String objectName = listDeletes.get(i).getKey();
            // 如果不为空，先删除bucket下的文件
            client.deleteObject(bucketName, objectName);
        }
        client.deleteBucket(bucketName);
    }

    /**
     * 删除某个Objects
     *
     * @param client
     * @param bucketName
     * @param key
     * @throws OSSException
     * @throws ClientException
     */
    public static void deleteObject(String bucketName, String key)
            throws OSSException, ClientException {
        // 如果不为空，先删除bucket下的文件
        client.deleteObject(bucketName, key);
    }

    /**
     * 上传图片
     *
     * @param bucketName 名称
     * @param key        上传路径
     * @param file       文件
     * @return
     * @throws OSSException
     * @throws ClientException
     * @throws IOException
     */
    public static PutObjectResult uploadFile(String bucketName, String key, MultipartFile file)
            throws OSSException, ClientException, IOException {
        // 初始化OSSClient
//        OSSClient client = new OSSClient(OSS_ENDPOINT,ACCESS_ID, ACCESS_KEY);
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.getSize());
        // 可以在metadata中标记文件类型
        objectMeta.setContentType("image/jpeg");

        InputStream input = null;
        PutObjectResult result = null;
        try {
            input = file.getInputStream();
            result = client.putObject(bucketName, key, input, objectMeta);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 上传图片
     *
     * @param bucketName 名称
     * @param key        上传路径
     * @param file       文件
     * @return
     * @throws OSSException
     * @throws ClientException
     * @throws IOException
     */
    public static PutObjectResult uploadFile(String bucketName, String key, InputStream input, long size)
            throws OSSException, ClientException, IOException {
        // 初始化OSSClient
//        OSSClient client = new OSSClient(OSS_ENDPOINT,ACCESS_ID, ACCESS_KEY);
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(size);
        // 可以在metadata中标记文件类型
        objectMeta.setContentType("image/jpeg");

        PutObjectResult result = null;
        try {
            result = client.putObject(bucketName, key, input, objectMeta);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 上传图片,默认bucket:
     *
     * @param key  上传路径
     * @param file 文件
     * @return
     * @throws OSSException
     * @throws ClientException
     * @throws IOException
     */
    public static PutObjectResult uploadFile(String key, MultipartFile file) throws OSSException, ClientException, IOException {

        PutObjectResult result = uploadFile(BUCKET_NAME, key, file);

        return result;
    }

    /**
     * 上传视频
     *
     * @param bucketName 名称
     * @param key        上传路径
     * @param file       文件
     * @return
     * @throws OSSException
     * @throws ClientException
     * @throws IOException
     */
    public static PutObjectResult uploadFileVideo(String bucketName, String key, MultipartFile file, String fileF)
            throws OSSException, ClientException, IOException {
        // 初始化OSSClient
//        OSSClient client = new OSSClient(OSS_ENDPOINT,ACCESS_ID, ACCESS_KEY);
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.getSize());
        // 可以在metadata中标记文件类型
        if (fileF.equals("mp4")) {//.mp4|avi|rm|rmvb|mov
            objectMeta.setContentType("video/mpeg4");
        }
        if (fileF.equals("avi")) {
            objectMeta.setContentType("application/vnd.rn-realmedia");
        }
        if (fileF.equals("rm")) {
            objectMeta.setContentType("application/vnd.rn-realmedia");
        }
        if (fileF.equals("rmvb")) {
            objectMeta.setContentType("application/vnd.rn-realmedia-vbr");
        }
        if (fileF.equalsIgnoreCase("pdf")) {
            objectMeta.setContentType("application/pdf");
        }

        InputStream input = null;
        PutObjectResult result = null;
        try {
            input = file.getInputStream();
            result = client.putObject(bucketName, key, input, objectMeta);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 上传视频,默认bucket:
     *
     * @param key  上传路径
     * @param file 文件
     * @return
     * @throws OSSException
     * @throws ClientException
     * @throws IOException
     */
    public static PutObjectResult uploadFileVideo(String key, MultipartFile file, String fileF) throws OSSException, ClientException, IOException {
        PutObjectResult result = uploadFileVideo(BUCKET_NAME, key, file, fileF);
        return result;
    }


    /**
     * 下载文件
     *
     * @param client
     * @param bucketName
     * @param key
     * @param filename
     * @throws OSSException
     * @throws ClientException
     */
    public static InputStream downloadFile(String bucketName, String key)
            throws OSSException, ClientException {
        // 获取Object，返回结果为OSSObject对象
        OSSObject object = client.getObject(bucketName, key);

        // 获取ObjectMeta
//        ObjectMetadata meta = object.getObjectMetadata();

        // 获取Object的输入流
        return object.getObjectContent();
    }


    /**
     * 创建通用路径
     *
     * @param suffx 图片类型
     * @return
     */
    public static String createCmsKey(String suffx) {
        return CMS_PATH + SEPARATOR + createYearMonthPath() + SEPARATOR + CMS_PATH_START + getCode() + "." + suffx;
    }

    /**
     * 创建包含宽高路径
     *
     * @param suffx 图片类型
     * @return
     */
    public static String createCmsWHKey(String suffx, Integer width, Integer height) {
        return CMS_PATH + SEPARATOR + createYearMonthPath() + SEPARATOR + CMS_PATH_START + "&" + width + "*" + height + "&" + getCode() + "." + suffx;
    }

    public static String createBusinessKey(String suffx) {
        return Business_PATH + SEPARATOR + createYearMonthPath() + SEPARATOR + Business_PATH_START + getCode() + "." + suffx;
    }

    public static String createMSCKey(String suffx) {
        return MSC_PATH + SEPARATOR + createYearMonthPath() + SEPARATOR + MSC_PATH_START + getCode() + "." + suffx;
    }

    /**
     * 创建通用路径
     *
     * @param suffx 图片类型
     * @return
     */
    public static String createCmsKey(String suffx, Long adminId) {
        return CMS_PATH + SEPARATOR + createYearMonthPath() + SEPARATOR + CMS_PATH_START + converUserIdTo4(adminId) + getCode() + "." + suffx;
    }

    public static String createCmsCircleKey(String suffx) {
        return "video" + SEPARATOR + CMS_PATH + SEPARATOR + DateUtil.getDateNoTime() + SEPARATOR + CMS_PATH_START + getCode() + "." + suffx;
    }

    /**
     * 返回15位随机数，2位随机数+13位当时时间毫秒数
     *
     * @return
     */
    public static String getCode() {
        // 当前时间13位
        String timeStr = String.valueOf(System.currentTimeMillis());
        // 加上2位随机数
        Random random = new Random();
        String randomCode2 = String.valueOf(random.nextInt(89) + 10);
        return randomCode2 + timeStr;
    }

    /**
     * 用户ID后4位，不足4位的加1000
     *
     * @param userId
     * @return
     */
    public static String converUserIdTo4(Long userId) {
        Long n = userId % 1000;
        if (n < 1000) {
            n += 1000;
        }
        return n.toString();
    }

    /**
     * 获取年/月
     *
     * @return
     */
    public static String createYearMonthPath() {
        Calendar now = Calendar.getInstance();

        return String.valueOf(now.get(Calendar.YEAR)) + SEPARATOR + String.valueOf(now.get(Calendar.MONTH) + 1);
    }
}
