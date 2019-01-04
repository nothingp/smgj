package com.szkingdom.service.impl;

import com.szkingdom.entity.DeptUser;
import com.szkingdom.entity.SysDept;
import com.szkingdom.entity.Users;
import com.szkingdom.entity.vo.IdIdListVo;
import com.szkingdom.mapper.dao.SysDeptMapper;
import com.szkingdom.service.interfaces.SysDeptService;
import com.szkingdom.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-17 15:07
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public void addDept(SysDept dept) {
        dept.setDeptId(CommonUtils.generatePK());
        sysDeptMapper.insert(dept);
    }

    @Override
    public void addDeptUser(IdIdListVo idIdListVo) {
        List<DeptUser> deptUserList = new ArrayList<>();
        for(String id : idIdListVo.getIdArray()){
            Date now = new Date();
            DeptUser du = new DeptUser(CommonUtils.generatePK(), idIdListVo.getId(), Long.parseLong(id), now, now);
            deptUserList.add(du);
        }
        sysDeptMapper.addDeptUserBatch(deptUserList);
    }

    @Override
    public void updateSysDept(SysDept dept) {
        sysDeptMapper.updateByPrimaryKey(dept);
    }

    @Override
    public void deleteSysDept(SysDept dept) {
        sysDeptMapper.deleteByPrimaryKey(dept.getDeptId());
    }

    @Override
    public List<SysDept> listSysDeptAndUser(SysDept dept) {
        List<SysDept> allDept = sysDeptMapper.listSysDept(dept);
        List<Users> allUser = sysDeptMapper.listDeptUser(dept);
        List<SysDept> returnList = new ArrayList<>();
        for(SysDept d : allDept){
            if(d.getParentId() == null){
                returnList.add(d);
                setChild(d, allDept, allUser);
            }
        }
        return returnList;
    }

    @Override
    public List<SysDept> listSysDeptNoUser(SysDept dept) {
        List<SysDept> allDept = sysDeptMapper.listSysDept(dept);
        return deptTree(allDept);
    }

    private List<SysDept> deptTree(List<SysDept> allDept){
        List<SysDept> returnList = new ArrayList<>();
        for(SysDept d : allDept){
            if(d.getParentId() == null){
                returnList.add(d);
                setChild(d, allDept);
            }
        }
        return returnList;
    }

    private void deptAndUserTree(){

    }

    private void setChild(SysDept parent, List<SysDept> allDept){
        List<SysDept> child = new ArrayList<>();
        for(SysDept c : allDept){
            if(parent.getDeptId().equals(c.getParentId())){
                child.add(c);
                setChild(c, allDept);
            }
        }
        parent.setChildDeptList(child);
    }

    private void setChild(SysDept parent, List<SysDept> allDept, List<Users> allUser){
        setDeptUser(parent, allUser);
        List<SysDept> child = new ArrayList<>();
        for(SysDept c : allDept){
            if(parent.getDeptId().equals(c.getParentId())){
                child.add(c);
                setChild(c, allDept, allUser);
            }
        }
        parent.setChildDeptList(child);
    }

    private void setDeptUser(SysDept dept, List<Users> allUser){
        List<Users> deptUser = new ArrayList<>();
        for(Users user : allUser){
            if(user.getDeptId().equals(dept.getDeptId())){
                deptUser.add(user);
            }
        }
        dept.setUserList(deptUser);
        allUser.removeAll(deptUser);
    }
}
