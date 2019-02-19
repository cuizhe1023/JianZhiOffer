package lazyType_staticInnerClass;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 23:16
 */
public class ThreadTest implements Runnable {
    //存放单例对象，使用Set是为了不存放重复元素
    public Set<Singleton> singles = new HashSet<Singleton>();
    @Override
    public void run() {
        //获取单例
        Singleton singleton = Singleton.getInstance();
        //添加单利
        singles.add(singleton);
    }
}