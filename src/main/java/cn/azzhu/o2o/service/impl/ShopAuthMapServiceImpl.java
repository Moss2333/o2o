package cn.azzhu.o2o.service.impl;

import cn.azzhu.o2o.entity.LocalAuth;
import cn.azzhu.o2o.entity.ShopAuthMap;
import cn.azzhu.o2o.mapper.ShopAuthMapMapper;
import cn.azzhu.o2o.service.ShopAuthMapService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {

    @Autowired
   private ShopAuthMapMapper shopAuthMapMapper;
    /*** 查询登录人 可以看哪些店铺
     *  * @param auth 参数可以只传Long/int 关键employee_id是什么类型，主要减少强转 * @return
     * @param auth*/
    @Override
    public List<ShopAuthMap> getShopsByAuthId(LocalAuth auth) {
        QueryWrapper<ShopAuthMap> shopAuthMapQueryWrapper = new QueryWrapper<>();
        shopAuthMapQueryWrapper.eq("employee_id",auth.getUserId());
        return shopAuthMapMapper.selectList(shopAuthMapQueryWrapper);
    }
}
