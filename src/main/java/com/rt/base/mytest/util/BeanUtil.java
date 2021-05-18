package com.rt.base.mytest.util;

import com.rt.base.mytest.model.FieldConfig;
import com.rt.base.mytest.model.FieldModel;
import com.rt.base.mytest.util.error.BizException;
import com.rt.base.mytest.util.error.JsonReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class BeanUtil {

    /**
     * 插入对象的某属性值
     *
     * @param object     对象
     * @param fieldName  属性名称
     * @param fieldValue 属性值
     * @return 属性值
     */
    public static Object setObjectValue(Object object, String fieldName, Object fieldValue) {
        Class tClass = object.getClass();
        try {
            Field field = tClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return object;
    }

    /**
     * 某对象为空，则赋值。
     *
     * @param object     对象
     * @param fieldName  属性名
     * @param fieldValue 值
     * @return
     */
    public static Object setNullObjectValue(Object object, String fieldName, Object fieldValue) {

        Class tClass = object.getClass();
        try {
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    field.setAccessible(true);
                    field.set(object, fieldValue);
                }
            }
            return object;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error(BizException.ERROR_MSG, e);
            throw new BizException(JsonReturnCode.BIZ_ILLEGAL_ACCESS_ERROR);
        }
    }


    /**
     * 获取对象的某属性值
     *
     * @param object    对象
     * @param fieldName 属性名称
     * @return 属性值
     */
    public static Object getObjectValue(Object object, String fieldName) {
        Class tClass = object.getClass();
        Field[] fields = tClass.getDeclaredFields();
        for (Field fd : fields) {
            if (fd.getName().equals(fieldName)) {
                fd.setAccessible(true);
                try {
                    return fd.get(object);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException();
                }
            }
        }
        return null;
    }

    /**
     * 获取对象中某类型的字段名称集合
     *
     * @param beanClz 对象
     * @param typeClz 需要收集的类型
     * @return
     */
    public static List<String> getDateList(Class beanClz, Class typeClz) {
        List<String> dateList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(beanClz) && !ObjectUtils.isEmpty(typeClz)) {
            //若是Map集合，则不操作。
            Field[] declaredFields = beanClz.getDeclaredFields();
            for (Field field : declaredFields) {
                if (typeClz.isAssignableFrom(field.getType())) {
                    dateList.add(field.getName());
                }
            }
        }
        return dateList;
    }

    /**
     * bean->map（bean中的Date改为String,yyyy-MM-dd）
     *
     * @param object 对象
     * @return 属性值
     */
    public static Map bean2Map(Object object) {
        if (object instanceof Map) {
            return (Map) object;
        }
        Map<String, Object> map = new HashMap();
        Class tClass = object.getClass();
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            try {
                Object value = field.get(object);
                Class<?> type = field.getType();
                if (type == Date.class) {
                    value = DateUtil.date2Str((Date) value);
                }
                map.put(name, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.error(BizException.ERROR_MSG, e);
                throw new BizException(JsonReturnCode.BIZ_ILLEGAL_ACCESS_ERROR);
            }
        }
        return map;
    }


    /**
     * bean->map(bean中类型是不改变)
     *
     * @param object 对象
     * @return 属性值
     */
    public static Map bean2MapPrototype(Object object) {
        if (object instanceof Map) {
            return (Map) object;
        }
        Map<String, Object> map = new HashMap();
        Class tClass = object.getClass();
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            try {
                Object value = field.get(object);
                map.put(name, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.error(BizException.ERROR_MSG, e);
                throw new BizException(JsonReturnCode.BIZ_ILLEGAL_ACCESS_ERROR);
            }
        }
        return map;
    }



    /**
     * map->bean
     *
     * @param tClass 转换的对象类型
     * @param map    要转换的Map
     * @param <T>    生成的对象
     * @return
     */
    public static <T> T map2Bean2(Class<T> tClass, Map<String, Object> map) {
        T obj = null;
        try {

            if (!ObjectUtils.isEmpty(map)) {
                obj = tClass.newInstance();
                Field[] fields = tClass.getDeclaredFields();
                for (Field field : fields) {
                    Class type = field.getType();
                    String fieldValue = null;
                    String fieldName = field.getName();
                    if (map.containsKey(fieldName)) {
                        Object object = map.get(fieldName);
                        if (!ObjectUtils.isEmpty(object)) {
                            fieldValue = object.toString();
                        }
                    }
                    //转换为需要的类型
                    Object value = parseValue(fieldValue, type);
                    //赋值
                    field.setAccessible(true);
                    field.set(obj, value);
                }
            }
        } catch (IllegalAccessException e) {
            log.error(BizException.ERROR_MSG, e);
            throw new BizException(JsonReturnCode.BIZ_NO_CLASS_PATH_ERROR);
        } catch (InstantiationException e) {
            log.error(BizException.ERROR_MSG, e);
            throw new BizException(JsonReturnCode.BIZ_NO_CLASS_PATH_ERROR);
        }

        return obj;

    }

    /**
     * 复制对象
     *
     * @param froObj 原对象
     * @param toObj  赋值之后的对象
     * @return
     */
    public static Object copy(Object froObj, Object toObj) {
        try {
            BeanUtils.copyProperties(froObj, toObj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return toObj;
    }



    /**
     * 字段是否是对象的属性
     *
     * @param tClass 对象
     * @param field  字段名
     * @return
     */
    public static boolean isField(Class tClass, String field) {
        if (!ObjectUtils.isEmpty(tClass)) {
            try {
                Field fields = tClass.getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                return false;
            }
        }
        return true;
    }


//    /**
//     * 对象转换
//     *
//     * @param obj 原对象
//     * @param clz 需要转换成的对象
//     * @param <T> 转换之后的对象
//     * @return
//     * @throws Exception
//     */
//    public static <T> T entityPropertyMap(Object obj, Class<T> clz) {
//        ObjectMapper om = new ObjectMapper();
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        om.setDateFormat(new SimpleDateFormat(DateUtil.DATE_YMD_));
//        /*  om.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);*/
//        T res;
//        try {
//            String tmp = om.writeValueAsString(obj);
//            res = om.readValue(tmp, clz);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error(BizException.ERROR_MSG, e);
//            throw new BizException(JsonReturnCode.FIELD_TO_FIELD_ERROR);
//        }
//        return res;
//    }


    /**
     * 转换类型，把实体根据fieldMap中对应的字段 转化为claz。
     *
     * @param object   原实体数据
     * @param claz     新实体
     * @param fieldMap 转换字段配置
     * @param <T>      新实体
     * @return 新实体
     */
    public static <T> T beanCovert(Object object, Class<T> claz, Map<String, String> fieldMap) {
        if (ObjectUtils.isEmpty(claz)) {
            throw new BizException(JsonReturnCode.BIZ_NULL_CLASS_PARAM.addParam("msg", claz.getName()));
        }
        T t;
        try {
            t = claz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new BizException(JsonReturnCode.BIZ_NO_INST_PARAM.addParam("msg", claz.getName()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new BizException(JsonReturnCode.BIZ_ILLEGAL_ACCESS_ERROR_PARAM.addParam("msg", claz.getName()));
        }
        if (!ObjectUtils.isEmpty(fieldMap)) {
            Set<Map.Entry<String, String>> entries = fieldMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                Object objectValue = BeanUtil.getObjectValue(object, entry.getValue());
                BeanUtil.setObjectValue(t, entry.getKey(), objectValue);
            }
        }
        return t;
    }

//    /**
//     * 转换类型，把实体根据fieldMap中对应的字段 转化为claz。
//     *
//     * @param object      原实体数据
//     * @param claz        新实体
//     * @param fieldConfig 转换字段配置
//     * @param <T>         新实体
//     * @return 新实体
//     */
//    public static <T> T beanCovert(Object object, Class<T> claz, FieldConfig fieldConfig) {
//        T t = newInstance(claz);
//        return beanCovert(object, t, fieldConfig);
//    }

    /**
     * 必填校验
     *
     * @param list       数据集合
     * @param mustFields 必填字段
     * @param <T>        实体
     * @return 校验结果
     */
    public static <T> String checkMust(List<T> list, String[] mustFields, String no, String title) {
        if (ObjectUtils.isEmpty(list)) {
            return "";
        }
        StringBuilder checkSB = new StringBuilder();
        String checkRow;

        int i = 0;
        Object noValue;
        for (T t : list) {
            i++;
            checkRow = checkMust(t, mustFields);
            if (!"".equals(checkRow)) {
                checkSB.append("第");
                checkSB.append(i);
                checkSB.append("条");
                // 提示的信息（比如发票号码）
                if (no != null) {
                    noValue = getObjectValue(t, no);
                    if (noValue != null) {
                        checkSB.append("（");
                        checkSB.append(title);
                        checkSB.append("：");
                        checkSB.append(noValue);
                        checkSB.append("）");
                    }
                }


                checkSB.append(checkRow);
                checkSB.append("为必填\n");
            }
        }
        return checkSB.toString();
    }

    /**
     * 必填校验
     *
     * @param mustFields 必填字段
     * @param <T>        实体
     * @return 校验结果
     */
    private static <T> String checkMust(T t, String[] mustFields) {
        if (ObjectUtils.isEmpty(t) || ObjectUtils.isEmpty(mustFields)) {
            return "";
        }
        StringBuilder checkSB = new StringBuilder();
        Object objectValue;
        for (String str : mustFields) {
            objectValue = getObjectValue(t, str);
            if (ObjectUtils.isEmpty(objectValue)) {
                checkSB.append(" ");
                checkSB.append(str);
                checkSB.append(" ");
            }
        }
        return checkSB.toString();
    }

    /**
     * 实例化实体
     *
     * @param claz 新实体
     * @param <T>  新实体
     * @return 新实体
     */
    public static <T> T newInstance(Class<T> claz) {
        if (ObjectUtils.isEmpty(claz)) {
            throw new BizException(JsonReturnCode.BIZ_NULL_CLASS_PARAM.addParam("msg", ""));
        }
        T t;
        try {
            t = claz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new BizException(JsonReturnCode.BIZ_NO_INST_PARAM.addParam("msg", claz.getName()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new BizException(JsonReturnCode.BIZ_ILLEGAL_ACCESS_ERROR_PARAM.addParam("msg", claz.getName()));
        }
        return t;
    }

//    /**
//     * 转换类型，把实体根据fieldMap中对应的字段 转化为claz。
//     *
//     * @param object      原实体数据
//     * @param t           新实体
//     * @param fieldConfig 转换字段配置
//     * @param <T>         新实体
//     * @return 新实体
//     */
//    public static <T> T beanCovert(Object object, T t, FieldConfig fieldConfig) {
//        if (ObjectUtils.isEmpty(t)) {
//            throw new BizException(JsonReturnCode.BIZ_NULL_CLASS_PARAM.addParam("msg", ""));
//        }
//
//        if (!ObjectUtils.isEmpty(fieldConfig)) {
//            List<FieldModel> fieldList = fieldConfig.getFieldList();
//
//            Field[] newFields = t.getClass().getDeclaredFields();
//
//            Field[] fields = object.getClass().getDeclaredFields();
//
//            for (FieldModel fieldModel : fieldList) {
//                Optional<Field> fieldOptional = Arrays.stream(fields).filter(item -> fieldModel.getFieldName().equals(item.getName())).findFirst();
//                if (!fieldOptional.isPresent()) {
//                    throw new BizException(JsonReturnCode.BIZ_NO_FIELD.addParam("msg", fieldModel.getFieldName()));
//                }
//                Field field = fieldOptional.get();
//
//                Optional<Field> newFieldOptional = Arrays.stream(newFields).filter(item -> fieldModel.getFieldNewName().equals(item.getName())).findFirst();
//                if (!newFieldOptional.isPresent()) {
//                    throw new BizException(JsonReturnCode.BIZ_NO_FIELD.addParam("msg", fieldModel.getFieldNewName()));
//                }
//                Field newField = newFieldOptional.get();
//
//                Class<?> newType = newField.getType();
//                Object obj;
//                try {
//                    field.setAccessible(true);
//                    obj = field.get(object);
//
//                    if (obj != null) {
//                        newField.setAccessible(true);
//                        if (!field.getType().equals(newType)) {
//                            if (BigDecimal.class.equals(newType)) {
//                                BigDecimal value = NumUtil.strToDec(obj.toString());
//                                int fieldScale = fieldModel.getFieldScale();
//                                if (fieldScale != 0) {
//                                    value = value.setScale(fieldScale, BigDecimal.ROUND_HALF_UP);
//                                }
//                                newField.set(t, value);
//                                continue;
//                            } else if (Date.class.equals(newType)) {
//                                String formatStr = fieldModel.getFormatStr();
//                                if (formatStr == null) {
//                                    formatStr = DateUtil.DATE_YMD_;
//                                }
//                                Date value = DateUtil.str2Date(formatStr, obj.toString());
//                                newField.set(t, value);
//                                continue;
//                            } else {
//                                throw new BizException("请配置" + newType.getName());
//                            }
//                        }
//                        newField.set(t, obj);
//                    }
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                    log.error(e.getMessage());
//                    throw new BizException(JsonReturnCode.BIZ_ILLEGAL_ACCESS_ERROR_PARAM.addParam("msg", newField.getName()));
//                }
//
//            }
//
//        }
//        return t;
//    }


    /**
     * 转换类型，把实体根据fieldMap中对应的字段 转化为claz。
     *
     * @param map         原实体数据
     * @param t           新实体
     * @param fieldConfig 转换字段配置
     * @param <T>         新实体
     * @return 新实体
     */
    public static <T> T mapCovert(Map<String, String> map, T t, FieldConfig fieldConfig) {
        if (ObjectUtils.isEmpty(t)) {
            throw new BizException(JsonReturnCode.BIZ_NULL_CLASS_PARAM.addParam("msg", ""));
        }
        if (!ObjectUtils.isEmpty(fieldConfig)) {
            List<FieldModel> fieldList = fieldConfig.getFieldList();
            Field[] newFields = t.getClass().getDeclaredFields();
            for (FieldModel fieldModel : fieldList) {
                String valueStr = map.get(fieldModel.getName());
                Optional<Field> newFieldOptional = Arrays.stream(newFields).filter(item -> fieldModel.getNewName().equals(item.getName())).findFirst();
                if (!newFieldOptional.isPresent()) {
                    throw new BizException(JsonReturnCode.BIZ_NO_FIELD.addParam("msg", fieldModel.getNewName()));
                }
                Field newField = newFieldOptional.get();
                Class<?> newType = newField.getType();
                try {
                    if (valueStr != null) {
                        newField.setAccessible(true);
                        if (!String.class.equals(newType)) {
                            if (BigDecimal.class.equals(newType)) {
                                BigDecimal value = NumUtil.strToDec(valueStr);
                                int fieldScale = Integer.parseInt(fieldModel.getScale());
                                if (fieldScale != 0) {
                                    value = value.setScale(fieldScale, BigDecimal.ROUND_HALF_UP);
                                }
                                newField.set(t, value);
                                continue;
                            } else if (Date.class.equals(newType)) {
                                String formatStr = fieldModel.getFormatStr();
                                if (formatStr == null) {
                                    formatStr = DateUtil.DATE_YMD_;
                                }
                                Date value = DateUtil.str2Date(formatStr, valueStr);
                                newField.set(t, value);
                                continue;
                            } else if (Integer.class.equals(newType)) {
                                newField.set(t, Integer.parseInt(valueStr));
                                continue;
                            } else if (Short.class.equals(newType)) {
                                newField.set(t, Short.parseShort(valueStr));
                                continue;
                            } else if (Long.class.equals(newType)) {
                                newField.set(t, Long.parseLong(valueStr));
                                continue;
                            } else {
                                throw new BizException("请配置" + newType.getName());
                            }
                        }
                        newField.set(t, valueStr);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    log.error(e.getMessage());
                    throw new BizException(JsonReturnCode.BIZ_ILLEGAL_ACCESS_ERROR_PARAM.addParam("msg", newField.getName()));
                }
            }
        }
        return t;
    }

    /**
     * 转换类型，把实体根据fieldMap中对应的字段 转化为claz。
     *
     * @param map         原实体数据
     * @param claz        新实体
     * @param fieldConfig 转换字段配置
     * @param <T>         新实体
     * @return 新实体
     */
    public static <T> T mapCovert(Map<String, String> map, Class<T> claz, FieldConfig fieldConfig) {
        T t = newInstance(claz);
        return mapCovert(map, t, fieldConfig);
    }

    /**
     * 转换值
     *
     * @param fieldValue
     * @param tClass
     * @return
     */
    public static Object parseValue(String fieldValue, Class tClass) {
        Object value = null;
        if (!StringUtils.isEmpty(fieldValue)) {
            if (tClass == BigDecimal.class) {
                value = new BigDecimal(fieldValue);
            } else if (tClass == Integer.class) {
                value = Integer.parseInt(fieldValue);
            } else if (tClass == Short.class) {
                value = Short.parseShort(fieldValue);
            } else if (tClass == Long.class) {
                value = Long.parseLong(fieldValue);
            } else if (tClass == Double.class) {
                value = Double.parseDouble(fieldValue);
            } else if (tClass == Float.class) {
                value = Float.parseFloat(fieldValue);
            } else {
                value = fieldValue;
            }
        }
        return value;
    }


//    /**
//     * 获取转换的实体注解配置
//     *
//     * @param tClaz 需要转换的实体
//     * @return fieldConfig
//     */
//    public static FieldConfig getFieldConfig(Class<?> tClaz) {
//        Field[] declaredFields = tClaz.getDeclaredFields();
//        FieldConfig fieldConfig = new FieldConfig(declaredFields.length);
//        String name;
//        String newName;
//        String dateFormat;
//        int fieldlength;
//        int fieldScale;
//        ConvertField annotation;
//        for (Field declaredField : declaredFields) {
//            annotation = declaredField.getAnnotation(ConvertField.class);
//            if (annotation != null) {
//                name = declaredField.getName();
//                newName = annotation.newName();
//                if (DataUtil.isEmpty(newName)) {
//                    newName = name;
//                }
//                dateFormat = annotation.dateFormat();
//                fieldlength = annotation.fieldLength();
//                fieldScale = annotation.fieldScale();
//                fieldConfig.beanConvert(name, newName, fieldlength, fieldScale, dateFormat);
//            }
//        }
//        return fieldConfig;
//    }

}
