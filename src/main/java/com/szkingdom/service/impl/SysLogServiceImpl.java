package com.szkingdom.service.impl;

import com.szkingdom.entity.SysLog;
import com.szkingdom.mapper.dao.SysLogMapper;
import com.szkingdom.service.interfaces.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lange
 * @date 2018-12-15 21:48
 */
@Service
public class SysLogServiceImpl implements SysLogService{

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void addLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }
}
