package com.rt.base.mytest.util;


/**
 * @author liW
 * @version 1.0
 * @date 2019-10-22 10:04
 */
public class DevJavaDocUtil {

//
//    private static RootDoc root;
//
//    /**
//     *
//     * @param path
//     */
//    public static ClassDoc[] readJavaDoc(String path){
//        // 调用com.sun.tools.javadoc.Main执行javadoc,参见 参考资料3
//        // javadoc的调用参数，参见 参考资料1
//        // -doclet 指定自己的docLet类名
//        // -classpath 参数指定 源码文件及依赖库的class位置，不提供也可以执行，但无法获取到完整的注释信息(比如annotation)
//        // -encoding 指定源码文件的编码格式
//        com.sun.tools.javadoc.Main.execute(new String[] {"-doclet", Doclet.class.getName(),
//// 因为自定义的Doclet类并不在外部jar中，就在当前类中，所以这里不需要指定-docletpath 参数，
////				"-docletpath",
////				Doclet.class.getResource("/").getPath(),
//                "-encoding","utf-8",
////                "-classpath",
////                "D:/j/facelog/facelog-main/target/classes;D:/j/facelog/db/target/classes;D:/j/facelog/db/sql2java/lib/swift-annotations-0.14.2.jar",
//                path
//        });
//        ClassDoc[] classes = root.classes();
//        return classes;
//    }
//
//    // 一个简单Doclet,收到 RootDoc对象保存起来供后续使用
//    // 参见参考资料6
//    public static  class Doclet {
//
//        public Doclet() {}
//
//        public static boolean start(RootDoc root) {
//            DevJavaDocUtil.root = root;
//            return true;
//        }
//    }

}
