package priv.jesse.mall.utils;

import java.util.UUID;

/**
 * @author chen
 * @version 1.0
 * @date 2020/3/24 20:14
 */
public class UUIDUtils {
    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
