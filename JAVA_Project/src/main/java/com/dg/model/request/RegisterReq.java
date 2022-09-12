package com.dg.model.request;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * @author ty
 */
@Data
public class RegisterReq {
    @Getter
    @NotBlank(message = "登录用户名不可为空")
    private String userName;

    @Getter
    @NotBlank(message = "登录密码不可为空")
    private String password;

    @Getter
    @NotBlank(message = "邮箱不可为空")
    private String email;

    @Getter
    @NotBlank(message = "真实姓名不可为空")
    private String realName;

    @Getter
    @NotBlank(message = "性别不可为空")
    private String sex;

    @Getter
    @NotBlank(message = "联系方式不可为空")
    private String phoneNumber;

    @Getter
    @NotBlank(message = "年龄不可为空")
    private String age;

    @Getter
    @NotBlank(message = "登录头像不可为空")
    private String avatarUrl;

    @Getter
    @NotBlank(message = "登录备注不可为空")
    private String remark;
}
