package cn.azzhu.o2o.service;


import cn.azzhu.o2o.entity.LocalAuth;

/**
 * @author azzhu
 * @create 2019-09-03 16:31:07
 */
public interface LocalAuthService {

//    /**
//     * 校验用户名是否存在
//     * @param userName
//     * @return
//     */
//    LocalAuth getUserById(String userName);
//
//    /**
//     * 注册
//     * @param localAuth
//     */
//    void regist(LocalAuth localAuth);

    /**
     * 登录方法
     * @param localAuth
     * @return
     */
    LocalAuth login(LocalAuth localAuth);

    /**
     * 校验用户名
     * @param userName
     * @return
     */
    LocalAuth checkUserName(String userName);

    /**
     * 登录
     * @param userName
     * @param pwd
     * @return
     */
    LocalAuth login(String userName, String pwd);

    /**
     * 插入用户账户信息
     * @param localAuth
     * @return
     */
    int addLocalAuth(LocalAuth localAuth);
}
