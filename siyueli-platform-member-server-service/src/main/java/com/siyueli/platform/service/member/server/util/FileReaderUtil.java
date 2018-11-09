package com.siyueli.platform.service.member.server.util;

import java.io.*;

/**
 * @author gavin
 * @description:
 * @date 2017/10/27 14:35
 */
public class FileReaderUtil {

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String read(InputStream inputStream) {
        BufferedReader reader = null;
        try {
            StringBuffer sb = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readFile(String resourceFile) {
        InputStream inputStream = null;
        try {
            inputStream = FileReaderUtil.class.getResourceAsStream(resourceFile);
            String content = read(inputStream);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
