package com.example.springboot.utils.aliyun;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.example.springboot.model.enums.ResultCodeEnum;
import com.example.springboot.model.form.BizException;
import com.example.springboot.utils.Assert;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: syjcms
 * @description: 文件传输工具类
 * @create: 2019-07-30 13:26
 */
public class FileUtil {
    /**
     * @param file MultipartFile类型文件集合
     * @Description:批量上传图片
     */
    public static List<String> uploadPictureList(
            MultipartFile[] file) {
        String msg = "";//返回存储路径
        List imgList = new ArrayList();
        if (file != null && file.length > 0) {
            for (MultipartFile multipartFile : file) {
                if (multipartFile.getSize() > 1024 * 1024)
                    throw new BizException(-1, multipartFile.getName() + "大于1M");
            }
            for (MultipartFile multipartFile : file) {
                String fileName = multipartFile.getOriginalFilename();//获取文件名加后缀
                if (fileName != null && fileName != "") {
                    String fileF = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());//文件后缀

                    String key = ALiYunUtil.createCmsKey(fileF);
                    try {
                        ALiYunUtil.uploadFile(key, multipartFile);
                        msg = ALiYunUtil.IMAGE_URL + key;
                        imgList.add(msg);
                    } catch (OSSException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return imgList;
    }

    /**
     * @param multipartFile MultipartFile类型文件
     * @Description:批量上传图片
     */
    public static String uploadPicture(
            MultipartFile multipartFile) {
        Assert.isTrue(multipartFile.getSize() > 1024 * 1024, ResultCodeEnum.UPLOAD_PICTURE_SIZE_ERROR);
        String msg = "";//返回存储路径
        if (multipartFile != null) {
            String fileName = multipartFile.getOriginalFilename();//获取文件名加后缀
            if (fileName != null && fileName != "") {
                String fileF = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());//文件后缀
                Assert.notTrue("jpg/png/gif/jpeg".contains(fileF.toLowerCase()), ResultCodeEnum.PICTURE_TYPE_ERROR);
                String key = ALiYunUtil.createCmsKey(fileF);
                try {
                    ALiYunUtil.uploadFile(key, multipartFile);
                    msg = ALiYunUtil.IMAGE_URL + key;
                } catch (OSSException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return msg;
    }

    /**
     * @param multipartFile MultipartFile类型文件
     * @Description:批量上传图片
     */
    public static String uploadWHPicture(
            MultipartFile multipartFile) {
        Assert.isTrue(multipartFile.getSize() > 1024 * 1024, ResultCodeEnum.UPLOAD_PICTURE_SIZE_ERROR);
        String msg = "";//返回存储路径
        if (multipartFile != null) {
            String fileName = multipartFile.getOriginalFilename();//获取文件名加后缀
            if (fileName != null && fileName != "") {
                String fileF = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());//文件后缀
                Assert.notTrue("jpg/png/gif".contains(fileF.toLowerCase()), ResultCodeEnum.PICTURE_TYPE_ERROR);
                try {
                    File file = File.createTempFile(fileF, String.valueOf(System.currentTimeMillis())); // 创建临时文件
                    FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
                    BufferedImage bufferedImage = ImageIO.read(file); // 通过临时文件获取图片流
                    Assert.isTrue(bufferedImage == null, ResultCodeEnum.PICTURE_TYPE_ERROR);
                    Integer width = bufferedImage.getWidth(); // 通过图片流获取图片宽度
                    Integer height = bufferedImage.getHeight(); // 通过图片流获取图片高度
                    String key = ALiYunUtil.createCmsWHKey(fileF, width, height);
                    ALiYunUtil.uploadFile(key, multipartFile);
                    msg = ALiYunUtil.IMAGE_URL + key;
                } catch (OSSException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return msg;
    }

    /**
     * 上传图片返回map不作大小判断
     *
     * @param multipartFile
     * @return
     */
    public static Map<String, String> uploadPic(MultipartFile multipartFile) {
        Map<String, String> map = new HashMap<>();
        String url = "";//返回存储路径
        Assert.isNull(multipartFile, ResultCodeEnum.CLIENT_PARAM_ERROR);
        String fileName = multipartFile.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "") {
            String fileF = fileName.substring(fileName.lastIndexOf(".") + 1);//文件后缀
            Assert.notTrue("jpg/png/gif".contains(fileF.toLowerCase()), ResultCodeEnum.PICTURE_TYPE_ERROR);
            String key = ALiYunUtil.createCmsKey(fileF);
            try {
                ALiYunUtil.uploadFile(key, multipartFile);
                url = ALiYunUtil.IMAGE_URL + key;

                map.put("url", url);
            } catch (OSSException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return map;
    }

    /**
     * @param files MultipartFile类型文件
     * @Date: 2019/9/20
     * @Author: zhaofu
     * @Description: 上传pdf
     **/
    public static FilePDFDTO uploadPDF(MultipartFile files) {
        String msg = "";//返回存储路径
        FilePDFDTO filePDFDTO = new FilePDFDTO();
        String fileName = files.getOriginalFilename();//获取文件名加后缀
        filePDFDTO.setName(fileName);
        if (fileName != null && fileName != "") {
            String fileF = fileName.substring(fileName.lastIndexOf(".") + 1);//文件后缀
            Assert.notTrue("pdf".contains(fileF.toLowerCase()), ResultCodeEnum.FILE_FORMAT);
            String key = ALiYunUtil.createCmsKey(fileF);
            try {
                ALiYunUtil.uploadFileVideo(key, files, fileF);
                filePDFDTO.setUrl(ALiYunUtil.IMAGE_URL + key);
            } catch (OSSException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePDFDTO;
    }

    /**
     * @param files MultipartFile类型文件
     * @Date: 2019/9/20
     * @Author: zhaofu
     * @Description: 上传视频
     **/
    public static String uploadVideo(MultipartFile files) {
        Assert.isTrue(!checkFileSize(files.getSize(), 50, "M"), ResultCodeEnum.UPLOAD_VIDEO_SIZE_ERROR);
        String msg = "";//返回存储路径
        String fileName = files.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "") {
            String fileF = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());//文件后缀
            String key = ALiYunUtil.createCmsKey(fileF);
            try {
                ALiYunUtil.uploadFileVideo(key, files, fileF);
                msg = ALiYunUtil.IMAGE_URL + key;
            } catch (OSSException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }




    /**
     * 判断文件大小
     *
     * @param len  文件长度
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
//        long len = file.length();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
