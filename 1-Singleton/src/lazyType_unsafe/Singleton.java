package lazyType_unsafe;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 21:43
 */
public class Singleton {
    private static Singleton instance;

    private Singleton(){
        System.out.println("创建了实例化对象");
    }

    public static Singleton getInstance(){
        if (instance == null){
            System.out.println("还没有创建实例化对象");
            return instance = new Singleton();
        }
        return instance;
    }

}
