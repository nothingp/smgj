package com.szkingdom.entity.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Lange
 * @date 2018-12-19 17:07
 */
public class IdIdListVo {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotNull(message = "id列表不能为空")
    @Pattern(regexp = "^(\\d+[,])*(\\d+)$", message = "id列表必须为逗号隔开的数字")
    private String idList;


    public String[] getIdArray(){
        if(this.idList == null){
            return null;
        }
        return idList.split(",");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }
}
