package com.design.proxy.dynamic.demo;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        System.out.println("--------------");

        RealSubject realSubject = new RealSubject();

        Subject proxyInstance =
                (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                        new Class[]{Subject.class}, new SubjectProxy(realSubject));

        proxyInstance.reverse("hello");

        RealSubjectSub realSubjectSub = new RealSubjectSub();
        SubjectSub proxyInstanceSub = (SubjectSub) Proxy.newProxyInstance(SubjectSub.class.getClassLoader(),
                new Class[]{SubjectSub.class}, new SubjectProxy(realSubjectSub));
        proxyInstanceSub.sub("hello");

        System.out.println("-------一个实现类实现两个接口-------");

        Object object = Proxy.newProxyInstance(SubjectSub.class.getClassLoader(), new Class[]{Subject.class, SubjectSub.class},
                new SubjectProxy(new MultiRealSubject()));
        Subject subject = (Subject) object;
        subject.reverse("hello");

        SubjectSub subjectSub = (SubjectSub) object;
        subjectSub.sub("hello");

        System.out.println("-------一个接口有两个实现类-------");
        SubjectProxy subjectProxy = new SubjectProxy(new RealSubject());
        Subject subj = (Subject) Proxy.newProxyInstance(subjectProxy.getClass().getClassLoader(),
                new Class[]{Subject.class}, subjectProxy);
        subj.reverse("abc");

        // 通过set改变实现类
        subjectProxy.setTarget(new MultiRealSubject());
        subj.reverse("abc");
    }
}
