package cn.azzhu.o2o.service.impl;

import cn.azzhu.o2o.entity.LocalAuth;
import cn.azzhu.o2o.entity.PersonInfo;
import cn.azzhu.o2o.enums.PersonInfoFlag;
import cn.azzhu.o2o.mapper.PersonInfoMapper;
import cn.azzhu.o2o.mapper.LocalAuthMapper;
import cn.azzhu.o2o.service.LocalAuthService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @author azzhu
 * @create 2019-09-03 16:32:05
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {
    @Autowired
    private LocalAuthMapper localAuthMapper;
    @Autowired
    private PersonInfoMapper personInfoMapper;

    /**
     * 校验用户名
     * @param userName
     * @return
     */
    @Override
    public LocalAuth checkUserName(String userName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",userName);
        LocalAuth localAuth = localAuthMapper.selectOne(wrapper);
        return localAuth;
    }

    /**
     * 登录1
     * @param userName
     * @param pwd
     * @return
     */
    @Override
    public LocalAuth login(String userName, String pwd) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_name",userName);
    String newPwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        wrapper.eq("password",newPwd);
        return localAuthMapper.selectOne(wrapper);
    }


    /**
     * 插入用户账户信息
     * @param localAuth
     * @return
     */
    @Override
    public int addLocalAuth(LocalAuth localAuth) {
        return localAuthMapper.insert(localAuth);
    }


//    /**
//     * 校验用户名是否存在
//     * @param userName
//     * @return
//     */
//    @Override
//    public LocalAuth getUserById(String userName) {
//        //查询用户表queryWrapper
//        QueryWrapper<LocalAuth> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name",userName);
//        return localAuthMapper.selectOne(queryWrapper);
//    }

//    /**
//     * 注册
//     * @param localAuth
//     */
//    @Override
//    public void regist(LocalAuth localAuth) {
//        //先插入person_info表  PersonInfoMapper
//        PersonInfo personInfo = new PersonInfo();
//        //需要设置4个属性
//        personInfo.setCustomerFlag(PersonInfoFlag.CUSTOMER.getCode());
//        personInfo.setCustomerFlag(PersonInfoFlag.SHOPOEMPLOYEE.getCode());
//        personInfo.setAdminFlag(1);
//        personInfo.setCreateTime(new Date());
//
//        //插入数据
//        personInfoMapper.insert(personInfo);
//        //TODO 难点：如何使用MP拿到刚插入的记录的主键值
//        System.out.println("===================>"+personInfo.getUserId());
//
//        //LocalAuth的相关信息
//        localAuth.setUserId(personInfo.getUserId());
//
//        localAuth.setCreateTime(new Date());
//
//        localAuthMapper.insert(localAuth);
//    }

    /**
     * 登录2
     * @param localAuth
     * @return
     */
    @Override
    public LocalAuth login(LocalAuth localAuth) {
        QueryWrapper<LocalAuth> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",localAuth.getUserName());
        //获取出明文密码
        String password = localAuth.getPassword();
        //对明文进行加密
        String newPwd = DigestUtils.md5DigestAsHex(password.getBytes());
        queryWrapper.eq("password",newPwd);
        return localAuthMapper.selectOne(queryWrapper);
    }
}
