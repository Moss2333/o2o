package cn.azzhu.o2o.service.impl;

import cn.azzhu.o2o.entity.PersonInfo;
import cn.azzhu.o2o.mapper.PersonInfoMapper;
import cn.azzhu.o2o.service.PersonInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author azzhu
 * @create 2019-09-04 09:10:43
 */
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
    @Autowired
    private PersonInfoMapper personInfoMapper;
    @Override
    public PersonInfo getPersonInfo(PersonInfo personInfo) {
        QueryWrapper<PersonInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",personInfo.getUserId());
        return personInfoMapper.selectOne(queryWrapper);
    }

    /**
     * 添加用户
     *
     * @param personInfo
     * @return
     */
    @Override
    public int addPersonInfo(PersonInfo personInfo) {
        return 0;
    }

    /**
     * 根据id查询PersonInfo信息
     *
     * @param userId
     * @return
     */
    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return null;
    }
}
