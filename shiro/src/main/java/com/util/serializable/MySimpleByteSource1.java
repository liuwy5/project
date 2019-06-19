package com.util.serializable;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

/**
 * SimpleByteSource没有实现Serializable接口
 * 自定义一个MySimpleByteSource类继承SimpleByteSource实现Serializable接口
 *
 * 但是因为SimpleByteSource没有不带参数的构造方法 导致反序列化时失败
 */
public class MySimpleByteSource1 extends SimpleByteSource implements Serializable {
    public MySimpleByteSource1(String string) {
        super(string);
    }


}
