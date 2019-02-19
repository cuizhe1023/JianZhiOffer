package lazyType_dcl;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 22:45
 */
public class Singleton {
    //volatile 保证，当 instance 变量被初始化成 Singleton 实例时，多个线程可以正确处理instance 变量
    private volatile static Singleton instance;

    private Singleton(){
        System.out.println("创建了实例化对象");
    }

    public static Singleton getInstance(){
        //检查实例
        if (instance == null){
            System.out.println("还没有创建实例化对象");
            //只有第一次才彻底执行这里的代码
            synchronized (Singleton.class){
                System.out.println("开始实例化对象");
                //进入同步代码块之后，再检查一次，如果依然为空，那么就创建实例
                if (instance == null){
                    return instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
