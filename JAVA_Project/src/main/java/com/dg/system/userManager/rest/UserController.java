package com.dg.system.userManager.rest;

import com.dg.common.result.Result;
import com.dg.system.userManager.model.LoginReq;
import com.dg.system.userManager.model.LoginVO;
import com.dg.system.userManager.model.RegisterReq;
import com.dg.system.userManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TheFool
 */
@RestController
@RequestMapping(value = "/v1/public/system/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/login")
    public Result<LoginVO> login(@Validated @RequestBody LoginReq req) {
        return Result.success(userService.login(req));
    }

    @PostMapping(value = "/register")
    public Result register(@Validated @RequestBody RegisterReq req) {
        userService.register(req);
        return Result.success();
    }
}
