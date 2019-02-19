package lazyType_safe;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 22:32
 */
public class Singleton {
    private static Singleton instance;

    private Singleton(){
        System.out.println("创建了实例化对象");
    }

    //为了保证线程安全，需要在 getInstance() 方法前加上 synchronized 关键字
    public synchronized static Singleton getInstance(){
        if (instance==null){
            System.out.println("开始实例化对象");
            return instance = new Singleton();
        }
        return instance;
    }
}
