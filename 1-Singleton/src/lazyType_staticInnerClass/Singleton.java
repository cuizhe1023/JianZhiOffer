package lazyType_staticInnerClass;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 23:04
 */
public class Singleton {

    private Singleton(){
        System.out.println("创建了实例化对象");
    }

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }

}
