package com.szkingdom.service.interfaces;

import com.szkingdom.entity.SysDept;
import com.szkingdom.entity.vo.IdIdListVo;

import java.util.List;

/**
 * @author Lange
 * @date 2018-12-17 15:05
 */

public interface SysDeptService {
    void addDept(SysDept dept);

    List<SysDept> listSysDeptAndUser(SysDept dept);

    List<SysDept> listSysDeptNoUser(SysDept dept);

    void addDeptUser(IdIdListVo idIdListVo);

    void updateSysDept(SysDept dept);

    void deleteSysDept(SysDept dept);
}
