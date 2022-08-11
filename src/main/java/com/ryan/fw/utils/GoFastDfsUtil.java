package com.ryan.fw.utils;

import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ryan.fw.common.GoFastDfsUploadFileResult;
import com.ryan.fw.config.GoFastDfsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @apiNote 文件服务器工具类
 */
@Component
public class GoFastDfsUtil {

    @Autowired
    private GoFastDfsConfig configVO;

    /**
     * 上传文件服务器
     */
    private static final String FILE_UPLOAD_URL = "/upload";

    /**
     * 删除文件服务器文件
     */
    private static final String FILE_DELETE_URL = "/delete";

    /**
     * 获取文件信息
     */
    private static final String GET_FILE_INFO_URL = "/get_file_info";

    /**
     * 查询文件数量
     */
    private static final String FILE_STAT_URL = "/stat";

    /**
     * 上传文件到文件服务器
     *
     * @param file
     * @return
     */
    public ResponseResult<GoFastDfsUploadFileResult> uploadFileResult(MultipartFile file) {
        GoFastDfsUploadFileResult uploadFileResult = new GoFastDfsUploadFileResult();
        try {
            InputStreamResource isr = new InputStreamResource(file.getInputStream(), file.getOriginalFilename());
            uploadFileResult = uploadFile(isr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseResult.success(uploadFileResult);
    }

    /**
     * 根据文件的md5值删除文件服务器上的文件
     *
     * @param md5
     * @return
     */
    public Map deleteFileByMd5(String md5) {
        // 校验参数是否为空
        checkCondition(md5);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("md5", md5);
        String servicePath = getServicePath();
        String postResult = HttpUtil.post(servicePath + FILE_DELETE_URL, paramsMap);
        JSONObject jsonObject = JSONObject.parseObject(postResult);
        return JSON.toJavaObject(jsonObject, Map.class);
    }


    /**
     * 根据文件的文件服务器路径删除文件服务器上的文件
     *
     * @param path
     * @return
     */
    public Map deleteFileByPath(String path) {
        // 校验参数是否为空
        checkCondition(path);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("path", path);
        String servicePath = getServicePath();
        String postResult = HttpUtil.post(servicePath + FILE_DELETE_URL, paramsMap);
        JSONObject jsonObject = JSONObject.parseObject(postResult);
        return JSON.toJavaObject(jsonObject, Map.class);
    }

    /**
     * 根据文件的md5值获取文件信息
     *
     * @param md5
     * @return
     */
    public Map getFileInfoByMd5(String md5) {
        // 校验参数是否为空
        checkCondition(md5);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("md5", md5);
        String servicePath = getServicePath();
        String postResult = HttpUtil.post(servicePath + GET_FILE_INFO_URL, paramsMap);
        JSONObject jsonObject = JSONObject.parseObject(postResult);
        return JSON.toJavaObject(jsonObject, Map.class);
    }

    /**
     * 根据文件的文件服务器路径获取文件信息
     *
     * @param path
     * @return
     */
    public Map getFileInfoByPath(String path) {
        // 校验参数是否为空
        checkCondition(path);
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("path", path);
        String servicePath = getServicePath();
        String postResult = HttpUtil.post(servicePath + GET_FILE_INFO_URL, paramsMap);
        JSONObject jsonObject = JSONObject.parseObject(postResult);
        return JSON.toJavaObject(jsonObject, Map.class);
    }

    /**
     * 文件服务器--->文件统计
     *
     * @return
     */
    public Map getFileStat() {
        String servicePath = getServicePath();
        String postResult = HttpUtil.post(servicePath + FILE_STAT_URL, new HashMap<>());
        JSONObject jsonObject = JSONObject.parseObject(postResult);
        return JSON.toJavaObject(jsonObject, Map.class);
    }

    /**
     * 根据文件路径获取文件的byte数组
     * @param filePath
     * @return
     */
    public ResponseResult getFileBytes(String filePath) {
        BufferedInputStream bin = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] resultByte = null;
        try {
            // 统一资源并将filePath中的空格替换为特殊符号
            URL url = new URL(filePath.replaceAll(" ", "%20"));
            // 连接类的父类,抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设置超时时间
            httpURLConnection.setConnectTimeout(1000 * 5);
            // 设置请求方式,默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接(如果尚未建立这样的连接)
            httpURLConnection.connect();
            // 从请求中获取数据
            bin = new BufferedInputStream(httpURLConnection.getInputStream());
            byte[] bytes = new byte[1024 * 4];
            int n = 0;
            while (-1 != (n = bin.read(bytes))) {
                bos.write(bytes, 0, n);
            }
            resultByte = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bin != null) {
                    bin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseResult.success(resultByte);
    }

    /**
     * 根据文件服务器的文件路径下载文件到本地
     * @param filePath 文件服务器的文件地址
     * @param localDir 本地存放目录
     */
    public ResponseResult downloadFileToLocal(String filePath,String localDir) {
        OutputStream outputStream = null;
        BufferedInputStream bin = null;
        try {
            // 统一资源
            URL url = new URL(filePath.replaceAll(" ", "%20"));
            // 连接类的父类,抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设置超时时间
            httpURLConnection.setConnectTimeout(1000 * 5);
            // 设置请求方式,默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接(如果尚未建立这样的连接)
            httpURLConnection.connect();
            // 从请求中获取数据
            bin = new BufferedInputStream(httpURLConnection.getInputStream());
            outputStream = new FileOutputStream(new File(localDir));
            int size = 0;
            byte[] buf = new byte[2048];
            while ((size = bin.read(buf)) != -1) {
                outputStream.write(buf, 0, size);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bin != null) {
                    bin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseResult.success();
    }


    /**
     * 文件上传文件服务器工具类
     *
     * @param isr
     * @return
     */
    private GoFastDfsUploadFileResult uploadFile(InputStreamResource isr) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("file", isr);
        paramsMap.put("output", "json");
        paramsMap.put("scene", configVO.getScene());
        String servicePath = getServicePath();
        String postResult = HttpUtil.post(servicePath + FILE_UPLOAD_URL, paramsMap);
        JSONObject jsonObject = JSONObject.parseObject(postResult);
        return JSON.toJavaObject(jsonObject, GoFastDfsUploadFileResult.class);
    }


    /**
     * 拼接文件服务器路径
     *
     * @return
     */
    private String getServicePath() {
        return configVO.getIp() + ":" + configVO.getPort() + "/" + configVO.getGroup();
    }

    /**
     * 校验参数
     *
     * @param condition
     */
    private void checkCondition(String condition) {
        if (StrUtil.isEmpty(condition)) {
            throw new RuntimeException("文件的md5或path不能为空,请输入文件的md5或path值!");
        }
    }
}
