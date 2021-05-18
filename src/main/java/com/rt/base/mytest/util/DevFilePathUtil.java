package com.rt.base.mytest.util;

/**
 * @author liW
 * @version 1.0
 * @date 2019-11-4 11:42
 */
public class DevFilePathUtil {

    //
    public static String getFilePath(Class<?> tclass) {
        // /E:/20Project/Base2/trunk/tssb-xb/testapi/target/classes/com/holyjet/zdevTools/excel/
        String classPath = tclass.getResource("").getPath();
        //  E:\20Project\Base2\trunk\tssb-xb\testapi\src\main\java\com\holyjet\zdevTools\excel
        return classPath.replace("target/classes", "src/main/java").substring(1);
    }

    public static String getPagePath(Class<?> tclass) {
        String path = getFilePath(tclass);
        String[] split = path.split("java/");
        if (split.length > 1) {
            return split[1].replace("/", ".");
        } else {
            return "";
        }
    }
}
