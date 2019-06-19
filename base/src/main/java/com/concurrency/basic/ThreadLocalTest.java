package com.concurrency.basic;

import javax.websocket.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThreadLocalTest {
    public static void main(String[] args) {
//        ThreadLocalTest1 threadLocalTest1 = new ThreadLocalTest1();
//        threadLocalTest1.method();

        ThreadLocalTestAdvance threadLocalTestAdvance = new ThreadLocalTestAdvance();
        threadLocalTestAdvance.method();
    }
}

/**
 * ThreadLocal Test
 */
class ThreadLocalTest1 {
    ThreadLocal<Long> integerThreadLocal = new ThreadLocal<>();
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public void set() {
        integerThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public Long getLong() {
        return integerThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }

    public void method() {
        System.out.println("main: ");
        set();
        System.out.println("id: " + getLong());
        System.out.println("name: " + getString());

        new Thread() {
            @Override
            public void run() {
                System.out.println("thread: ");
                set();
                System.out.println("thread id: " + getLong());
                System.out.println("thread name: " + getString());
            }
        }.start();

        System.out.println("main: ");
        System.out.println("id: " + getLong());
        System.out.println("name: " + getString());
    }
}

/**
 * 如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法。
 */
class ThreadLocalTestAdvance {
    ThreadLocal<Long> integerThreadLocal = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };
    ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return Thread.currentThread().getName();
        }
    };

    public void set() {
        integerThreadLocal.set(Thread.currentThread().getId());
        stringThreadLocal.set(Thread.currentThread().getName());
    }

    public Long getLong() {
        return integerThreadLocal.get();
    }

    public String getString() {
        return stringThreadLocal.get();
    }

    public void method() {
        System.out.println("main: ");
//        set();
        System.out.println("id: " + getLong());
        System.out.println("name: " + getString());

        new Thread() {
            @Override
            public void run() {
                System.out.println("thread: ");
//                set();
                System.out.println("thread id: " + getLong());
                System.out.println("thread name: " + getString());
            }
        }.start();

        System.out.println("main: ");
        System.out.println("id: " + getLong());
        System.out.println("name: " + getString());
    }
}

/**
 * usage: 数据库连接
 */
class ThreadLocalConnection {
    static final String DB_URL = "127.0.0.1:3306";
    public static ThreadLocal<Connection> connection = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    };

    public static Connection getConnection() {
        return connection.get();
    }
}

/**
 * usage: session
 */
class ThreadLocalSession {
    private static final ThreadLocal sessionThread = new ThreadLocal();

    public static Session getSession() {
        Session session = (Session) sessionThread.get();
        if (session == null) {
//            session = getSessionFactory().openSession();
            sessionThread.set(session);
        }
        return session;
    }
}
