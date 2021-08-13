package com.syq.demo.validation.pojo;

import com.syq.demo.validation.validator.custom.CustomValidator;
import com.syq.demo.validation.validator.group.AddGroup;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User {

    @NotBlank(message = "姓名不能为空",groups = {AddGroup.class})
    @CustomValidator(message = "hahahhaah",groups = {AddGroup.class})
    private String name;

    @NotNull(message = "年龄不能为空",groups = {AddGroup.class})
    @Max(value = 100,message = "年龄太大",groups = {AddGroup.class})
    private Integer age;

    @Valid
    @NotNull(message = "mUser不能为空",groups = {AddGroup.class})
    private MUser mUser;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public MUser getmUser() {
        return mUser;
    }

    public void setmUser(MUser mUser) {
        this.mUser = mUser;
    }
}
