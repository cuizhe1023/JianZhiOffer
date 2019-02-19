package SingletonManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cuizhe
 * @Date: 2019/2/18 23:30
 */
public class Singleton {

    //用 SingletonManager 将多种的单例类统一管理，在使用时根据 key 获取对象对应类型的对象。
    private static Map<String,Object> objMap = new HashMap<>();

    private Singleton(){

    }

    public static void registerService(String key,Object instance){
        if (objMap.containsKey(key)){
            objMap.put(key,instance);
        }
    }

    public static Object getInstance(String key){
        return objMap.get(key);
    }

}
