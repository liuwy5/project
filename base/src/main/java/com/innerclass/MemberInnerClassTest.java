package com.innerclass;

public class MemberInnerClassTest {

    private Integer a = 54;

    class DotInner {

        private Integer a = 34;

        // 获取BaseUse的对象
        public MemberInnerClassTest parent() {
            return MemberInnerClassTest.this;
        }

        public void print() {
            System.out.println("dot inner print");
        }

        public void sss() {
            // 访问外部类成员变量
            System.out.println("outer class param: " + MemberInnerClassTest.this.a);
            System.out.println("class param: " + this.a);
        }
    }

    public void method() {
        // 外部类方法构建内部类对象
        DotInner inner = this.new DotInner();
        inner.print();
        DotInner inner1 = new DotInner();
        inner1.print();
    }

    public static void main(String[] args) {
        MemberInnerClassTest memberInnerClassTest = new MemberInnerClassTest();
        memberInnerClassTest.method();
        // .new新建内部类对象
        MemberInnerClassTest.DotInner inner = memberInnerClassTest.new DotInner();
        inner.print();
        inner.sss();
    }
}
