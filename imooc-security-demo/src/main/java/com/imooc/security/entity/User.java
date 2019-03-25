package com.imooc.security.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.security.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    public interface UserSimpleView {};

    public interface UserDeatilView extends UserSimpleView{};

    @ApiModelProperty("用户Id")
    private String id;

    @MyConstraint(message = "这是一个测试")
    @ApiModelProperty("用户名")
    private String username;

//    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDeatilView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
