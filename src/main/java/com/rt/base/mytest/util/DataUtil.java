package com.rt.base.mytest.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class DataUtil {

    //外贸lcCode
    public static final String WM_LC_CODE = "WMSB";

    //生产lcCode
    public static final String SC_LC_CODE = "SCSB";

    // 企业类型--外贸
    public static final String QYLX_WM = "wm";
    // 企业类型--生产
    public static final String QYLX_SC = "sc";

    // 企业隔离--字段
    public static final String F_CPCODE = "cpcode";

    // 主键--字段
    public static final String F_UUID = "uuid";

    // 请求头中的 请求URL -- 打印二维码请求用
    public static final String VIEW_SERVER_PATH = "viewServerPath";

    // 二维码请求路径字段
    public static final String IMAGE_URL = "imageURL";

    // 日志中的分割标记
    public static final String SPLIT_MARK = "=============================================\n";

    // PASS
    public static final String PASS = "PASS";



    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID_() {
        return UUID.randomUUID().toString();
    }


    /**
     * 判断是否是Map
     *
     * @param object 要判断的类
     * @return
     */
    public static Boolean isMap(Object object) {
        return ((object != null) &&
                (Map.class.isAssignableFrom(object.getClass()) || object.getClass().getName().endsWith("DataUtil$1")));
    }

    /**
     * 把list遍历，转换为String
     *
     * @param list   需要拼接的集合
     * @param length 集合的长度， 为String Buffer初始化做准备
     * @return
     */
    public static String list2Str(List list, int length) {
        if (ObjectUtils.isEmpty(length)) {
            return "";
        }
        // 一个元素大约占用length个字符吧。。。
        StringBuffer sb = new StringBuffer(list.size() * length);
        for (Object object : list) {
            sb.append(object);
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 把list遍历，转换为String（默认元素长度是10，为String Buffer初始化做准备）
     *
     * @param list 需要拼接的集合
     * @return
     */
    public static String list2Str(List list) {
        return list2Str(list, 10);
    }

//    /**
//     * 根据MultipartFile 获取workbook
//     *
//     * @param file
//     * @return
//     */
//    public static Workbook getWorkbook(MultipartFile file) {
//
//        String fileName = file.getOriginalFilename();
//        Workbook wb = null;
//        try {
//            if (fileName.endsWith("xls")) {
//                wb = new HSSFWorkbook(file.getInputStream());
//            } else if (fileName.endsWith("xlsx")) {
//                wb = new XSSFWorkbook(file.getInputStream());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error(BizException.ERROR_MSG, e);
//            throw new BizException(JsonReturnCode.BIZ_FILE_READ);
//        }
//        return wb;
//    }

    /**
     * 读取Excel中的Cell
     *
     * @param row 行
     * @param i   单元格
     */
    public static String getCellValue(Row row, int i) {
        return getCellValue(row, i, null);
    }

    /**
     * 读取Excel中的Cell
     *
     * @param row          行
     * @param i            单元格
     * @param defaultValue 默认值
     */
    public static String getCellValue(Row row, int i, String defaultValue) {
        if (!ObjectUtils.isEmpty(row)) {
            if (row.getCell(i) != null) {
                defaultValue = row.getCell(i).toString();
            }
        }
        return defaultValue;
    }

    /**
     * 检测重复数据
     *
     * @param strs 需要检测的字段
     * @param data 导入的数据集合
     * @param <T>
     * @return 重复的数据集合
     */
    public static <T> Set<String> checkRepeat(String[] strs, List<T> data) {
        Set<String> excelSet = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        for (T t : data) {
            StringBuffer filedSb = new StringBuffer();
            for (String filed : strs) {
                filedSb.append(" ");
                filedSb.append(BeanUtil.getObjectValue(t, filed));
            }
            String fileds = filedSb.toString();
            if (null == map.get(fileds)) {
                map.put(fileds, "");
            } else {
                excelSet.add(fileds);
            }
        }
        return excelSet;
    }

    /**
     * 拼接字段
     *
     * @param set      拼接的数据集合
     * @param separate 分隔符
     * @return
     */
    public static String splicingStr(Set<String> set, String separate) {
        String str = "";
        StringBuffer sb = new StringBuffer();
        if (!ObjectUtils.isEmpty(set)) {
            for (String value : set) {
                sb.append(value);
                sb.append(separate);
            }
            str = sb.toString();
        }
        return str;
    }

    /**
     * 拼接字段
     *
     * @param strs     拼接的数据集合
     * @param separate 分隔符
     * @return
     */
    public static String splicingStr(String[] strs, String separate) {
        String str = "";
        StringBuffer sb = new StringBuffer();
        if (!ObjectUtils.isEmpty(strs)) {
            for (String value : strs) {
                sb.append(value);
                sb.append(separate);
            }
            str = sb.toString();
        }
        return str;
    }

    /**
     * 合并List
     *
     * @param lists 需要合并的集合
     * @return 合并之后的集合
     */
    public static List sumList(List... lists) {
        List sumList = new ArrayList();
        if (!ObjectUtils.isEmpty(lists)) {
            for (List list : lists) {
                if (!ObjectUtils.isEmpty(list)) {
                    sumList.addAll(list);
                }
            }
        }
        return sumList;
    }

    /**
     * 合并List
     *
     * @param lists 需要合并的集合
     * @return 合并之后的集合
     */
    public static String[] sumList2Arr(List... lists) {

        if (ObjectUtils.isEmpty(lists)) {
            return null;
        }
        int length = 0;
        for (List list : lists) {
            if (!ObjectUtils.isEmpty(list)) {
                length = length + list.size();
            }
        }
        String[] arrs = new String[length];
        int i = 0;
        for (List list : lists) {
            if (!ObjectUtils.isEmpty(list)) {
                for (Object o : list) {
                    arrs[i++] = o.toString();
                }
            }
        }
        return arrs;
    }

    /***
     * 通用TTTTTT
     * 下划线命名转为驼峰命名
     * @param para
     */

    public static String UnderlineToHump(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /***
     * 通用TTTTTT
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String HumpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;//定位
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toUpperCase();
    }



    /**
     * 判断数组中是否包含某元素
     *
     * @param arr     数组
     * @param element 元素
     * @return
     */
    public static boolean arrContain(Object[] arr, Object element) {
        boolean isContain = false;
        if (!ObjectUtils.isEmpty(arr)) {
            for (Object obj : arr) {
                if ((element == null && obj == null) || (element != null && element.equals(obj))) {
                    isContain = true;
                    break;
                }
            }
        }
        return isContain;
    }

    /**
     * map中的value值去重转换为数组
     *
     * @param map
     * @return
     */
    public static String[] value2Strs(Map<String, String> map) {
        Map<String, String> itemMap = new HashMap();
        Iterator<String> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            itemMap.put(iterator.next(), null);
        }
        return itemMap.keySet().toArray(new String[itemMap.size()]);
    }

    /**
     * 获取map中的值，若为空，则返回空字符串（String类型）
     *
     * @param key key值
     * @param map map集合
     * @return
     */
    public static String getMapStrValue(String key, Map map) {
        String value = "";
        if (!ObjectUtils.isEmpty(map)) {
            if (!ObjectUtils.isEmpty(map.get(key))) {
                value = map.get(key).toString().trim();
            }
        }
        return value;
    }

    /**
     * 获取map中的Long，若为空，则返回空
     *
     * @param key key值
     * @param map map集合
     * @return
     */
    public static Long getMapLongValue(String key, Map map) {
        Long value = null;
        if (!ObjectUtils.isEmpty(map) && !ObjectUtils.isEmpty(map.get(key))) {
            value = (Long) map.get(key);
        }
        return value;
    }

    /**
     * 获取map中的Long,转换为日期，若为空，则返回空
     *
     * @param key key值
     * @param map map集合
     * @return
     */
    public static Date getMapLongDateValue(String key, Map map) {
        Date date = null;
        Long value = getMapLongValue(key, map);
        if (value != null) {
            date = new Date(value);
        }
        return date;
    }

    /**
     * 获取map中的值，若为空，则返回零（BigDecimal类型）
     *
     * @param key key值
     * @param map map集合
     * @return
     */
    public static BigDecimal getMapDecValue(String key, Map map) {
        BigDecimal value = BigDecimal.ZERO;
        //先取出值，若不为空，则转换为数字
        String mapStrValue = getMapStrValue(key, map);
        if (!ObjectUtils.isEmpty(mapStrValue)) {
            try {
                value = new BigDecimal(mapStrValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 判断是否为空
     *
     * @param object 判断的元素
     * @return 是否为空
     */
    public static boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object);
    }

    /**
     * 字符串--判断是否为空（去除空格等）
     *
     * @param str 判断的字符串
     * @return 是否为空
     */
    public static boolean isEmptyTrim(String str) {
        return isEmpty(str) || (isEmpty(str.trim()));
    }

}
