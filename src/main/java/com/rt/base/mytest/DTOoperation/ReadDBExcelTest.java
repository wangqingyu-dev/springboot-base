package com.rt.base.mytest.DTOoperation;


/**
 * 主要功能    给固定的dto加注释
 * @ClassName: zhuShi
 * @Author: liwang
 * @Date: 2020-03-24 20:36
 **/
public class ReadDBExcelTest {
    private static String path = "E:\\微信\\新建文件夹\\WeChat Files\\wxid_qamg57jsmq2521\\FileStorage\\File\\2020-07\\DB设计文档_0612(1)(1).xlsx";

    // output
    private static String javaPath = "D:\\tliWorkspace\\version_2_5\\admin-server\\repos1\\TLI\\40Source\\branches\\pc\\version_2_5\\admin-server\\src\\main\\java\\net\\dlrt\\tli\\adminserver\\dto\\teacher\\TeacherDailylHourScore.java";
//    private static String outPath = "E:\\ipad文档\\所有input-output-vo-bo字段内容\\newFile\\";
    private static String outPath = javaPath;

//    public static void main(String[] args) {
//        Map<String, TableDTO3> map = getMap();
////        把路径中的java文件取出方法遍历来添加
//        List<File> files = getFiles(javaPath);
//        for (File file : files) {
//            String s = DevFileUtil.readFileNote2(file.getAbsolutePath());
//            String aa = aa(s, map);
//
//            DevFileUtil.createFile(aa, outPath + file.getName());
//        }
//
//    }
//
//    private static List<File> getFiles(String path) {
//        return DevFileUtil.getFileList(path);
//
//    }

    /**
     * @param note
     * @param map
     * @return
     */
//    private static String aa(String note, Map<String, TableDTO3> map) {
//        StringBuilder sb = new StringBuilder();
//        String[] notes = note.split("\n");
//        boolean flag = true;
//        for (String s : notes) {
//            if (s.contains("private")) {
//                String trim = s.trim();
//                String[] s1 = trim.split(" ");
//                int length = s1.length;
//                String s2 = s1[length - 1].replace(";", "");
//                String s3 = s2.trim().toLowerCase();
//                TableDTO3 tableDTO = map.get(s3);
//                if (tableDTO != null && flag) {
//                    sb.append("    /**\n");
//                    sb.append("     * ");
//                    sb.append(tableDTO.getZh());
//                    sb.append("\n");
//                    sb.append("     */\n");
//                }
//                flag = true;
//                sb.append(s);
//            } else if (s.contains("//")) {
//                sb.append("    /**\n");
//                sb.append(s.replace("//", " * "));
//                sb.append("\n");
//                sb.append("     */\n");
//                flag = false;
//            } else if (s.contains("*")) {
//                flag = false;
//                sb.append(s);
//            } else {
//                sb.append(s);
//                flag = true;
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
    /**
     * 从execl文件中取出数据，以name和注释为对象返回
     * @return
     */
//    private static Map<String, TableDTO3> getMap() {
//        List<TableDTO3> tableDTOS = ReadExcel.read2Model(TableDTO3.class, path, 17, 205, 218);
//        return tableDTOS.stream().collect(Collectors.toMap(item -> item.getName() == null ? "" : item.getName().replace("_","").toLowerCase(), Function.identity(), (a, b) -> a));
//
//    }
}
