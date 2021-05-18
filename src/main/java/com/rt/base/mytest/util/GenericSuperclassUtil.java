package com.rt.base.mytest.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericSuperclassUtil {

    /*
     * 获取泛型类Class对象，不是泛型类则返回null
     */
    public static Class<?> getClassGeneric(Class<?> clazz) {
        Class<?> entitiClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
                    .getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                entitiClass = (Class<?>) actualTypeArguments[0];
            }
        }
        return entitiClass;
    }

    /*
     * 获取泛型类Class对象，不是泛型类则返回null
     */
    public static Class<?> getInterfacesGeneric(Class<?> clazz) {
        Class<?> entitiClass = null;
        //  注意这里的 getGenericInterfaces 是获取接口泛型的方法
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        //  这里强转是因为 ParameterizedType 继承Type接口 并可以获取对应的参数类
        ParameterizedType genericInterface = (ParameterizedType) genericInterfaces[0];
        Type[] typeArguments = genericInterface.getActualTypeArguments();
        // 这是多泛型时 要使用循环来获取
        for (int i = 0; i < typeArguments.length; i++) {
            Class<?> typeArgument =(Class<?>) typeArguments[i];
            System.out.println(typeArgument);
        }
        return entitiClass;
    }

}