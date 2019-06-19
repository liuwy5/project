package com.annotation;

import java.lang.reflect.Method;

/**
 * 自定义注解使用 利用反射
 */
public class AnnotationTest {

    @MyAnnotation(value1 = "value1")
    public void method() {
        System.out.println("Hello world");
    }

    public static void main(String[] args) throws Exception {
        AnnotationTest annotationTest = new AnnotationTest();

        Class clazz = annotationTest.getClass();

        Method method = clazz.getMethod("method", new Class[]{});

        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            System.out.println("value: " + annotation.value() + ", value1: " + annotation.value1()); // 直接调用注解属性

            method.invoke(annotationTest, new Object[]{});
        }
    }
}
