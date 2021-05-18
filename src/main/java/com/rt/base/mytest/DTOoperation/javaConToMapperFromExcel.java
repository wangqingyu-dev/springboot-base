package com.rt.base.mytest.DTOoperation;

import com.rt.base.mytest.util.DataUtil;
import com.rt.base.mytest.util.DevFileUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author :static
 * 一键从controller到数据库增删改查
 */
public class javaConToMapperFromExcel {

    public static void main(String[] args) {
        read();
    }

    private static void read() {
//        Sheet sheet = DevFileUtil.getSheet(CommonPath.dataPath, 0);
        Sheet sheet = DevFileUtil.getSheet("", 0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (!DataUtil.isEmpty(row)) {
                String name = DevFileUtil.getCellValue(row, 0, "");
                String type = DevFileUtil.getCellValue(row, 8, "");
                String note = DevFileUtil.getCellValue(row, 12, "");

                String str = "  /**\n" +
                        "     * " + note + "\n" +
                        "     */\n" +
                        "    private " + type + " " + name + ";\n";
                System.out.println(str);
            }
        }
    }
}
