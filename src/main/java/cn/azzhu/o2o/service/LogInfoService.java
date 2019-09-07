package cn.azzhu.o2o.service;


import cn.azzhu.o2o.entity.LogInfo;
import cn.azzhu.o2o.utils.DataGridView;
import cn.azzhu.o2o.vo.LogInfoVo;

/**
 * @author azzhu
 * @create 2019-09-04 09:13:35
 */
public interface LogInfoService {

//    /**
//     * 插入一条登录日志
//     * @param logInfo
//     * @return
//     */
//    int addLogInfo(LogInfo logInfo);
    /**
     * 查询所有日志
     * @param logInfoVo
     * @return
     */
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo);
    /**
     * 添加日志
     * @param logInfo
     */
    public void addLogInfo(LogInfo logInfo);
    /**
     * 根据id删除日志
     * @param logInfoid
     */
    public void deleteLogInfo(Integer logInfoid);
    /**
     * 批量删除日志
     * @param ids
     */
    public void deleteBatchLogInfo(Integer[] ids);
}
