package com.szkingdom.service.impl;

import com.szkingdom.entity.SysMenu;
import com.szkingdom.mapper.dao.SysMenuMapper;
import com.szkingdom.service.interfaces.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lange
 * @date 2018-12-21 14:13
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> listMenu() {
        List<SysMenu> menuList = sysMenuMapper.listMenu();
        return MenuTree(menuList);
    }

    private List<SysMenu> MenuTree(List<SysMenu> allList){
        List<SysMenu> returnList = new ArrayList<>();
        for(SysMenu menu : allList){
            if(menu.getParentId() == null){
                returnList.add(menu);
                setChild(menu, allList);
            }
        }
        return returnList;
    }

    private void setChild(SysMenu menu, List<SysMenu> allList) {
        List<SysMenu> child = new ArrayList<>();
        menu.setChildren(child);
        for(SysMenu m : allList){
            if(menu.getMenuId().equals(m.getParentId())){
                child.add(m);
                setChild(menu,allList);
            }
        }
    }
}
