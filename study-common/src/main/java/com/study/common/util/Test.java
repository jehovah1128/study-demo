package com.study.common.util;

public class Test {

    public String sayHello(String name, String who) {
        synchronized (name) {
            System.out.println(who + " say hello " + name);
            try {
                Thread.sleep(3000);
                System.out.println(who + " say bey " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name;
        }
    }

    public synchronized String sayHello1(String name, String who) {
        synchronized (name) {
            System.out.println(who + " say hello " + name);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(who + " say bey " + name);
        }

        return name;
    }

    public static void main(String[] args) {
        Test test = new Test();
//        for (int i = 0;i<10;i++){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String name = new String("tom");
                test.sayHello1(name, "lily");
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                String name = new String("tom");
                test.sayHello(name, "bob");
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
//        }
    }
}
