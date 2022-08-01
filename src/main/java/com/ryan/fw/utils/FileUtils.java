package com.ryan.fw.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;

/**
 * @author Ryan
 * @date 2022/8/1 16:51
 * @description 文件工具类
 */
public class FileUtils {

    /**
     * File转换byte[]
     *
     * @param file
     * @return byte[]
     */
    public static byte[] fileToByteArray(File file) {
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream(2048);
            byte[] b = new byte[2048];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件流输出
     *
     * @param httpServletResponse 响应Servlet
     * @param bytes               文件byte[]
     */
    public static void outputFile(HttpServletResponse httpServletResponse, byte[] bytes) {
        ServletOutputStream out = null;
        InputStream in = null;
        try {
            out = httpServletResponse.getOutputStream();
            in = new ByteArrayInputStream(bytes);
            byte[] buf = new byte[2048];
            int length;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(out)) {
                    out.flush();
                    out.close();
                }
                if (Objects.nonNull(in)) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
