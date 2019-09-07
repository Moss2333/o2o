package cn.azzhu.o2o.service;

import cn.azzhu.o2o.entity.LocalAuth;
import cn.azzhu.o2o.entity.ShopAuthMap;

import java.util.List;

public interface ShopAuthMapService {

    /*** 查询登录人 可以看哪些店铺
     *  * @param auth 参数可以只传Long/int 关键employee_id是什么类型，主要减少强转 * @return */
    List<ShopAuthMap> getShopsByAuthId(LocalAuth auth);
}
