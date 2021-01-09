package com.wanghui.design.pattern.singleton;

public class TestThreadSafe {

    public static void main(String[] args) {

        Excutor excutor = new Excutor();
        Excutor excutor1 = new Excutor();
        Excutor excutor2 = new Excutor();
       Thread thread = new Thread(excutor);
       Thread thread1 = new Thread(excutor1);
       Thread thread2 = new Thread(excutor2);
       thread.start();
       thread1.start();
       thread2.start();

    }

}
