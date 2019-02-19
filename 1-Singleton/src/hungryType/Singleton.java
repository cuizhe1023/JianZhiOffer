package hungryType;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 21:26
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton(){
        System.out.println("创建了实例化对象");
    }

    public static Singleton getInstance(){
        return instance;
    }
}
