package com.statics;

public class StaticCodeBlockTest1 extends BB{

    static {
        System.out.println("StaticCodeBlockTest1 static block");
    }

    public StaticCodeBlockTest1 () {
        System.out.println("StaticCodeBlockTest1 construct");
    }

    {
        System.out.println("StaticCodeBlockTest1 normal block");
    }

    public static void main(String[] args) {
        System.out.println("程序入口");
        new StaticCodeBlockTest1();
    }
}

class AA {
    static {
        System.out.println("AA static block");
    }

    public AA () {
        System.out.println("AA construct");
    }

    {
        System.out.println("AA normal block");
    }
}

class BB extends AA {
    static {
        System.out.println("BB static block");
    }

    public BB () {
        System.out.println("BB construct");
    }

    {
        System.out.println("BB normal block");
    }
}
