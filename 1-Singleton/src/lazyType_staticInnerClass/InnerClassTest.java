package lazyType_staticInnerClass;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 23:05
 */
public class InnerClassTest {

//    public static void main(String[] args) {
//        //在单线程的情况下运行
//        Singleton singleton = Singleton.getInstance();
//        Singleton singleton1 = Singleton.getInstance();
//        System.out.println("两次创建的对象是否相等:"+(singleton==singleton1));//运行结果一直都是true,说明单线程下是没问题的
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
         创建了实例化对象
         [lazyType_staticInnerClass.Singleton@528ff726]
         */
        //说明在多线程的情况下也可以安全创建.
    }

}