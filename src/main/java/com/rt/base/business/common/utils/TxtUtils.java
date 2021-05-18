package com.rt.base.business.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 转换TXT文件中的内容工具类
 *
 */
public class TxtUtils {

    /**
     * @Description 获取上传的txt文件，去掉其中的空格、换行、回车、制表符并且把全角符号转换成半角符号和大写
     * @param multfile txt文件
     * @Return com.alibaba.fastjson.JSONObject
     * @Exception
     */
    public static JSONObject readTxtTransformString(MultipartFile multfile) {
        JSONObject result = new JSONObject();
        try {
            // 获取文件名
            String fileName = multfile.getOriginalFilename();
            // 获取文件后缀
            String prefix = fileName.substring(fileName.lastIndexOf("."));
            // 用uuid作为文件名，防止生成的临时文件重复
            File excelFile = File.createTempFile(UUID.randomUUID().toString().replaceAll("-",""), prefix);
            // MultipartFile to File
            multfile.transferTo(excelFile);
            // 输入流读取临时文件
            FileInputStream fileInputStream = new FileInputStream(excelFile);
            // size 为字串的长度 ，这里一次性读完
            int size = fileInputStream.available();
            byte[] buffer = new byte[size];
            fileInputStream.read(buffer);
            fileInputStream.close();
            // 将读取内容赋值给字符串
            String fileContent = new String(buffer, "UTF-8");

            // 去掉字符串中的空格 换行 回车 制表符
            Pattern p = compile("\\s*|\t|\r|\n");
            Matcher str = p.matcher(fileContent);
            // 去除中文
            String regex = "[\u4e00-\u9fa5]";
            //大写字母数字正则
            String numberLetter = "^[a-z0-9A-Z]+$";

           String stringRegex = toSemiangle(str.replaceAll("")).toUpperCase();
           //  逗号分隔成数组
            String[] strArray =stringRegex.split (",");
            if(strArray.length > 1){
                stringRegex = "";
                for (String strf :strArray){
                    //数组判断是否存在汉字
                    strf = strf.replaceAll(regex,"");

                    if(strf.matches(numberLetter)){
                        if(strf != "" && !"".equals(strf)){
                            stringRegex += strf + ",";
                        }
                    }
                }
                //以上代码得到 xx,xxx,x,xx,x, 则需要去掉最后一个逗号 既满足标准分隔符条件
                stringRegex = stringRegex.substring(0, stringRegex.length() - 1);
            }
            else {
                //去除中文
                stringRegex = stringRegex.replaceAll(regex, "");
                //只留下英文字母数字
                if (!stringRegex.matches(numberLetter)) {
                    stringRegex="";
                }
            }
            // 把全角符号转换成半角符号后再转换成大写
            result.put("data",stringRegex);
            //数据过滤完成,删除临时文件
            deleteFile(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * @Description 删除MultipartFile转换File是生成的临时文件
     * @param files 临时文件
     * @Return void
     * @Exception
     * @Date 2019-11-15 10:54
     */
    private static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * @Description 把全角符号转好成半角符号
     * @param conversion 转换内容
     * @Return java.lang.String 转换成半角符号的内容
     * @Exception
     */
    public static String toSemiangle(String conversion) {
        char[] c = conversion.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                //全角空格为12288，半角空格为32
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                //其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

}
