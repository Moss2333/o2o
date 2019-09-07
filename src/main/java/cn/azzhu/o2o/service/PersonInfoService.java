package cn.azzhu.o2o.service;


import cn.azzhu.o2o.entity.PersonInfo;

/**
 * @author azzhu
 * @create 2019-09-04 09:08:49
 */
public interface PersonInfoService {

    /**
     * 查询单个PersonInfo信息
     * @param personInfo
     * @return
     */
    PersonInfo getPersonInfo(PersonInfo personInfo);
    /**
     * 添加用户
     * @param personInfo
     * @return
     */
    int addPersonInfo(PersonInfo personInfo);

    /**
     * 根据id查询PersonInfo信息
     * @param userId
     * @return
     */
    PersonInfo getPersonInfoById(Long userId);

}
