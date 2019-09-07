package cn.azzhu.o2o.service.impl;

import cn.azzhu.o2o.entity.LogInfo;
import cn.azzhu.o2o.mapper.LogInfoMapper;
import cn.azzhu.o2o.service.LogInfoService;
import cn.azzhu.o2o.utils.DataGridView;
import cn.azzhu.o2o.vo.LogInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author azzhu
 * @create 2019-09-04 09:14:49
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;


    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        QueryWrapper<LogInfo> queryWrapper = new QueryWrapper<>();
        List<LogInfo> logInfos = logInfoMapper.selectList(queryWrapper);
        PageInfo<LogInfo> pageInfo = new PageInfo<>();
        DataGridView<LogInfo> dataGridView = new DataGridView<>();
        dataGridView.setCount(pageInfo.getTotal());
        dataGridView.setData(logInfos);
        return dataGridView;
    }

    @Override
    public void addLogInfo(LogInfo logInfo) {
        logInfoMapper.insert(logInfo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoid) {
        logInfoMapper.deleteById(logInfoid);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        logInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
