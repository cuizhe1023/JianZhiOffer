package Enum;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 23:21
 */
public class EnumTest {

//    public static void main(String[] args) {
//        //枚举单例的优点就是简单，并且默认枚举实例的创建是线程安全的
//        Singleton singleton = Singleton.INGLETON;
//        singleton.doSomething();
//    }

    public static void main(String[] args) {
        //在多线程的情况下运行
        ThreadTest test = new ThreadTest();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();
        new Thread(test).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.singles);

         /*
         [INGLETON]
         */
        //说明在多线程的情况下也可以安全创建.
    }
}
