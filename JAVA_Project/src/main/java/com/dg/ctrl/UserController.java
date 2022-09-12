package com.dg.ctrl;

import com.dg.common.model.Result;
import com.dg.model.SysPermission;
import com.dg.model.SysUser;
import com.dg.model.request.LoginReq;
import com.dg.model.vo.LoginVo;
import com.dg.model.request.RegisterReq;
import com.dg.service.UserService;
import com.dg.utils.SecurityUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ty
 */
@RestController
@RequestMapping(value = "/system/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public Result<LoginVo> login(@Validated @RequestBody LoginReq req) {
        return Result.success(userService.login(req));
    }

    @PostMapping(value = "/register")
    public Result register(@Validated @RequestBody RegisterReq req) {
        userService.register(req);
        return Result.success();
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public Result getInfo() {
        SysUser user = SecurityUtil.getLoginUser().getSysUser();
        // 权限集合
        List<SysPermission> permissions = SecurityUtil.getLoginUser().getPermissionList();
        Map<String, Object> content = Maps.newHashMap();
        content.put("user", user);
        content.put("per", new HashSet<>(permissions));
        return Result.success(content);
    }

    @GetMapping("/page")
    public Result getUserPage(SysUser user,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                              @RequestParam(required = false, defaultValue = "15") Integer pageSize) {
        return Result.success(userService.queryPage(user, pageNo, pageSize));
    }
}
