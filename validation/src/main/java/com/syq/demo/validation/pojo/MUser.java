package com.syq.demo.validation.pojo;

import com.syq.demo.validation.validator.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MUser {

    @NotBlank(message = "name1不能为空",groups = {AddGroup.class})
    private String name1;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
