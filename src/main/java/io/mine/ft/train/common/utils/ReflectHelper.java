package io.mine.ft.train.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

@Slf4j
public class ReflectHelper {
    public static Object getFieldValue(Object obj, String fieldName) {
        if (obj == null || StringUtils.isBlank(fieldName)) {
            return null;
        }

        Field targetField = getTargetField(obj.getClass(), fieldName);
        try {
            return FieldUtils.readField(targetField, obj, true);
        } catch (IllegalAccessException e) {
            log.error("ReflectHelper->getFieldValue:", e);
        }
        return null;
    }

    public static Field getTargetField(Class<?> targetClass, String fieldName) {
        Field field = null;
        try {
            if (targetClass == null) {
                return field;
            }

            if (Object.class.equals(targetClass)) {
                return field;
            }

            field = FieldUtils.getDeclaredField(targetClass, fieldName, true);
            if (field == null)
                field = getTargetField(targetClass.getSuperclass(), fieldName);
        } catch (Exception e) {
        }
        return field;
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        if (null == obj) {
            return;
        }
        Field targetField = getTargetField(obj.getClass(), fieldName);
        try {
            FieldUtils.writeField(targetField, obj, value);
        } catch (IllegalAccessException e) {
            log.error("ReflectHelper->setFieldValue:", e);
        }
    }
}
