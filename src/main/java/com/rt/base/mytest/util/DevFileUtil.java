package com.rt.base.mytest.util;

import com.rt.base.mytest.model.FieldModel;
import com.rt.base.mytest.model.IndModel;
import com.rt.base.mytest.model.TableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DevFileUtil {
    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr).append("\n");
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
    /**
     * 读取 Excel -> workbook
     *
     * @param path
     * @return
     */
    public static Workbook getWorkbook(String path) {
        Workbook wb = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                return createWorkbook(path);
            }
            FileInputStream fis = new FileInputStream(file);
            if (path.toLowerCase().endsWith("xls")) {
                wb = new HSSFWorkbook(fis);
            } else if (path.toLowerCase().endsWith("xlsx")) {
                wb = new XSSFWorkbook(fis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }


    /**
     * 读取  Excel -> sheet
     *
     * @param path
     * @return
     */
    public static Sheet createSheet(String path) {
        Sheet sheet = null;
        try {
            Workbook wb = getWorkbook(path);
            if (wb != null) {
                //获取第一个表单sheet
                sheet = wb.createSheet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheet;
    }

    /**
     * 读取  Excel -> sheet
     *
     * @param path
     * @param sheetPage
     * @return
     */
    public static Sheet getSheet(String path, int sheetPage) {
        Sheet sheet = null;
        try {
            Workbook wb = getWorkbook(path);
            if (wb != null) {
                //获取第一个表单sheet
                sheet = wb.getSheetAt(sheetPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheet;
    }



    /**
     * 读取  Excel -> sheet
     *
     * @param path
     * @return
     */
    public static int getSheetCount(String path) {
        Sheet sheet = null;
        try {
            Workbook wb = getWorkbook(path);
            if (wb != null) {
                //获取第一个表单sheet
                return wb.getNumberOfSheets();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 读取  Excel -> sheet
     *
     * @param path
     * @return
     */
    public static int getSheetNumbers(String path) {
        int numberOfSheets = 0;
        try {
            Workbook wb = getWorkbook(path);
            if (wb != null) {
                //获取第一个表单sheet
                numberOfSheets = wb.getNumberOfSheets();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfSheets;
    }

    /**
     * 读取Excel  row -> String
     *
     * @param row 行
     * @param i   单元格
     */
    public static String getCellValue(Row row, int i) {
        return getCellValue(row, i, "");
    }

    /**
     * 读取Excel  row -> String
     *
     * @param row 行
     * @param i   单元格
     */
    public static String getCellValueNull(Row row, int i) {
        return getCellValue(row, i, null);
    }

    /**
     * 读取Excel  row -> String
     *
     * @param row          行
     * @param i            单元格
     * @param defaultValue 默认值
     */
    public static String getCellValue(Row row, int i, String defaultValue) {
        if (!ObjectUtils.isEmpty(row)) {
            Cell cell = row.getCell(i);
            if (!ObjectUtils.isEmpty(cell)) {
//                CellType cellTypeEnum = cell.getCellTypeEnum();
//                if (cellTypeEnum == CellType.NUMERIC) {
//                    // 数字
//                    NumberFormat nf = NumberFormat.getInstance();
//                    // 去掉逗号
//                    nf.setGroupingUsed(false);
//                    try {
//                        String s = nf.format(cell.getNumericCellValue());
//                        return s;
//                    } catch (Exception e) {
//                        return String.valueOf("###########" + cell);
//                    }
//                }
//                if (cellTypeEnum == CellType.FORMULA) {
//                    // 公式
////                    double numericCellValue = cell.getNumericCellValue();
////                    return String.valueOf(numericCellValue);
//                    String stringCellValue = cell.toString();
//                    DecimalFormat df = new DecimalFormat("0.00");
//                    try {
//                        return df.format(cell.getNumericCellValue());
//                    } catch (Exception e) {
//                        return String.valueOf("###########" + cell);
//                    }
//                } else {
//                    try {
//                       return cell.getStringCellValue().trim();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return String.valueOf("###########" + cell);
//                    }
//                }
//                // 字符串

            }
        }
        return defaultValue;
    }

    /**
     * 读取  ->  字符串
     *
     * @param path 文件路径
     */
    public static String readFileNote(String path) {
        String str = "";
        InputStream in;
        try {
            File f = new File(path);
            in = new FileInputStream(f);
            int i;
            int j = 0;
            char[] c = new char[(int) f.length()];
            while ((i = in.read()) != -1) {
                c[j++] = (char) i;
            }
            in.close();
            str = new String(c);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取错误  " + path);
        }
        return str;
    }

    /**
     * 读取文件内容
     *
     * @param filePath String 如 c:\\1.txt 绝对路径
     * @return boolean
     */
    public static String readFileNote2(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            File f = new File(filePath);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 创建  workbook
     *
     * @param path 路径，提取格式用
     * @return
     */
    public static Workbook createWorkbook(String path) {
        Workbook wb = null;
        try {
            if (path.endsWith("xls")) {
                wb = new HSSFWorkbook();
            } else if (path.endsWith("xlsx")) {
                wb = new XSSFWorkbook();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }


    /**
     * 创建  workbook
     *
     * @param wb    Workbook
     * @param sheet 复制的sheet
     * @return
     */
    public static Workbook addWorkSheet(Workbook wb, Sheet sheet) {
        Sheet sheet1 = wb.createSheet(sheet.getSheetName() + "aaa");
        copySheet(sheet1, sheet);
        return wb;
    }


    /**
     * 创建  workbook
     *
     * @param newSheet 路径，提取格式用
     * @return
     */
    public static void copySheet(Sheet newSheet, Sheet oldSheet) {
        int lastRowNum = oldSheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            Row oldRow = oldSheet.getRow(i);
            Row newRow = newSheet.createRow(i);
            short lastCellNum = oldRow.getLastCellNum();
            for (int j = 0; j <= lastCellNum; j++) {
                newRow.createCell(j).setCellValue(DevFileUtil.getCellValue(oldRow, j));
            }
        }
    }


    /**
     * 创建 workbook -> 文件
     *
     * @param workbook   代码
     * @param createPath 路径 + 名称
     */
    public static void createExcle(Workbook workbook, String createPath) {
        try {
            //生成文件
            File file = new File(createPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            workbook.write(os);
            os.flush();
            os.close();
            System.out.println(createPath + "  生成。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成文件页面出错！");
        }
    }

    /**
     * 创建  String -> 文件
     *
     * @param pageStr    代码
     * @param createPath 路径 + 名称
     */
    public static void createFile(String pageStr, String createPath) {
        try {
            //生成文件
            File file = new File(createPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            os.write(pageStr.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();
            System.out.println(createPath + "  生成。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成文件页面出错！");
        }
    }


    /**
     * 读取Excel
     *
     * @param sheet
     * @return
     */
    public static List<FieldModel> getFieldModelList(Sheet sheet) {
        //获取第一行
        int firstrow = 1;
        //获取最后一行
        int lastrow = sheet.getLastRowNum();
        List<FieldModel> list = new ArrayList();
        FieldModel fieldModel;
        for (int j = firstrow; j <= lastrow; j++) {
            fieldModel = new FieldModel();
            int i = 0;
            //表名	表名注释	名称	注释	长度	精度	类型	DB必填	主键
            //获取哪一行i
            Row row = sheet.getRow(j);
            fieldModel.tableName = DevFileUtil.getCellValue(row, i++);
            fieldModel.tableComment = DevFileUtil.getCellValue(row, i++);
            fieldModel.name = DevFileUtil.getCellValue(row, i++);
            fieldModel.comment = DevFileUtil.getCellValue(row, i++);
            fieldModel.length = DevFileUtil.getCellValue(row, i++);
            fieldModel.scale = DevFileUtil.getCellValue(row, i++);
            fieldModel.type = DevFileUtil.getCellValue(row, i++);
            String must = DevFileUtil.getCellValue(row, i++);
            boolean mustValue = false;
            if (!ObjectUtils.isEmpty(must)) {
                mustValue = true;
            }
            fieldModel.must = mustValue;
            String pk = DevFileUtil.getCellValue(row, i++);
            fieldModel.note = pk;
            if (!ObjectUtils.isEmpty(pk) && !pk.contains("P") && !pk.contains("C")) {
                System.out.println("卧槽，发现了一个新的约束*****************************");
                System.out.println(fieldModel);
            }
            boolean pkValue = false;
            if (!ObjectUtils.isEmpty(pk) && pk.contains("P")) {
                pkValue = true;
            }
            fieldModel.pk = pkValue;
            fieldModel.defaultValue = DevFileUtil.getCellValue(row, i++);
            list.add(fieldModel);
        }
        return list;
    }


    /**
     * 读取Excel
     *
     * @param sheet
     * @return
     */
    public static List<IndModel> getIndList(Sheet sheet) {
        //获取第一行
        int firstrow = 1;
        //获取最后一行
        int lastrow = sheet.getLastRowNum();
        List<IndModel> list = new ArrayList();
        IndModel indModel;
        for (int j = firstrow; j <= lastrow; j++) {
            indModel = new IndModel();
            int i = 0;
            //表名	表名注释	名称	注释	长度	精度	类型	DB必填	主键
            //获取哪一行i
            Row row = sheet.getRow(j);
            indModel.tableName = DevFileUtil.getCellValue(row, i++);
            indModel.indexName = DevFileUtil.getCellValue(row, i++);
            indModel.cloumnName = DevFileUtil.getCellValue(row, i++);
            indModel.columnPosition = DevFileUtil.getCellValue(row, i++);
            indModel.descend = DevFileUtil.getCellValue(row, i++);

            list.add(indModel);
        }
        return list;
    }


    /**
     * 专用--读取Excel字段名称，去除某些前缀
     *
     * @param cellValue
     * @return
     */
    public static String getFieldName(String cellValue) {
        String name;
        String[] split = cellValue.split("\\.");
        if (split.length == 2) {
            if (split[0].length() == 1) {
                name = split[1];
            } else {
                name = split[0] + "_" + split[1];
            }
        } else {
            name = cellValue;
        }
        return name;
    }


    /**
     * 获取某目录下所有的文件
     *
     * @param rootPath 根目录
     * @return 文件集合
     */
    public static List<File> getFileList(String[] rootPath) {
        try {
            List<File> fileList = new ArrayList<>();
            for (String path : rootPath) {
                File file = new File(path);
                addFileList(file, fileList);
            }

            return fileList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("目录不对啊。。。。");
        }
    }

    /**
     * 获取某目录下所有的文件
     *
     * @param rootPath 根目录
     * @return 文件集合
     */
    public static List<File> getFileList(String rootPath) {
        try {
            List<File> fileList = new ArrayList<>();
            File file = new File(rootPath);
            addFileList(file, fileList);
            return fileList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("目录不对啊。。。。");
        }
    }

    /**
     * 递归遍历目录
     *
     * @param file     目录
     * @param fileList 文件集合
     */
    private static void addFileList(File file, List<File> fileList) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (!ObjectUtils.isEmpty(files)) {
                for (File fi : files) {
                    addFileList(fi, fileList);
                }
            }
        } else {
            fileList.add(file);
        }
    }


    /**
     * 读取Excel
     *
     * @param sheet
     * @return
     */
    public static TableModel getTableModel2(Sheet sheet) {
        //获取第一行
        int firstrow = 1;
        //获取最后一行
        int lastrow = sheet.getLastRowNum();

        TableModel tableModel = new TableModel();
        tableModel.tableName = sheet.getSheetName();
        tableModel.fileNameEn = DevComUtil.toUpperCaseFirstOne(DevComUtil.UnderlineToHump(tableModel.tableName));

        List<FieldModel> list = new ArrayList();
        FieldModel fieldModel;


        for (int j = firstrow; j <= lastrow; j++) {
            //获取哪一行i
            Row row = sheet.getRow(j);
            String DBName = DevFileUtil.getCellValue(row, 0);
            String name = DevFileUtil.getCellValue(row, 1);
            String comment = DevFileUtil.getCellValue(row, 2);
            String length = DevFileUtil.getCellValue(row, 3);
            String scale = DevFileUtil.getCellValue(row, 4);
            String fielType = DevFileUtil.getCellValue(row, 5);
            String must = DevFileUtil.getCellValue(row, 6);
            String pk = DevFileUtil.getCellValue(row, 7);

            fieldModel = new FieldModel();
            fieldModel.comment = comment;

            fieldModel.DBName = DBName;
            fieldModel.name = name;

            fieldModel.DBtype = fielType;
            String type = "String";
            if ("DATE".equals(fielType)) {
                type = "Date";
            }
            if (fielType.startsWith("NUMBER")) {
                if (!ObjectUtils.isEmpty(scale)) {
                    type = "BigDecimal";
                } else {
                    type = "Integer";
                }
            }
            fieldModel.length = length;
            fieldModel.scale = scale;
            fieldModel.type = type;

            boolean mustValue = false;
            if (!ObjectUtils.isEmpty(must)) {
                mustValue = true;
            }
            fieldModel.must = mustValue;

            boolean pkValue = false;
            if (!ObjectUtils.isEmpty(pk)) {
                pkValue = true;
            }
            fieldModel.pk = pkValue;


            list.add(fieldModel);
        }
        tableModel.fieldModelList = list;
        return tableModel;
    }


    /**
     * 读取Excel
     *
     * @param sheet
     * @return
     */
    public static TableModel getTableModel(Sheet sheet) {
        //获取第一行
        int firstrow = 2;
        //获取最后一行
        int lastrow = sheet.getLastRowNum();

        TableModel tableModel = new TableModel();
        tableModel.tableName = DevFileUtil.getCellValue(sheet.getRow(0), 0);
        tableModel.tableRelation = DevBizUtil.getRelationName(tableModel.tableName);

        List<FieldModel> list = new ArrayList();
        FieldModel fieldModel;

        int count = 1;

        for (int j = firstrow; j <= lastrow; j++) {
            //获取哪一行i
            Row row = sheet.getRow(j);
            String comment = DevFileUtil.getCellValue(row, 9);
            String comment2 = DevFileUtil.getCellValue(row, 13);
            if (ObjectUtils.isEmpty(comment) && ObjectUtils.isEmpty(comment2)) {
                continue;
            }
            fieldModel = new FieldModel();
            fieldModel.comment = comment;
            fieldModel.comment2 = comment2;

            fieldModel.name = DevComUtil.UnderlineToHump(DevFileUtil.getCellValue(row, 1));
            String no = DevFileUtil.getCellValue(row, 10);
            if (ObjectUtils.isEmpty(no) && !ObjectUtils.isEmpty(comment)) {
                fieldModel.no = String.valueOf(count++);
            } else {
                fieldModel.no = no;
            }

            String fielType = DevFileUtil.getCellValue(row, 3);
            String type = "String";
            String length = "0";
            String scale = "0";
            if ("DATE".equals(fielType)) {
                type = "date";
            }
            if (fielType.startsWith("VARCHAR2")) {
                length = fielType.split("\\(")[1].split("\\)")[0];
            }
            if (fielType.startsWith("NUMBER")) {
                if (fielType.split("\\(").length < 2) {
                    type = "Integer";
                    length = "12";
                    scale = "0";
                } else {
                    String[] len = fielType.split("\\(")[1].split("\\)")[0].split(",");
                    type = "BigDecimal";
                    length = len[0];
                    scale = len[1];
                }

            }
            fieldModel.length = length;
            fieldModel.scale = scale;
            fieldModel.type = type;

            String must = DevFileUtil.getCellValue(row, 11);
            boolean mustValue = false;
            if (!ObjectUtils.isEmpty(must)) {
                mustValue = true;
            }
            fieldModel.must = mustValue;
            fieldModel.defaultValue = DevFileUtil.getCellValue(row, 12);
            fieldModel.note = DevFileUtil.getCellValue(row, 8);

            list.add(fieldModel);
        }
        tableModel.fieldModelList = list;
        return tableModel;
    }

}
