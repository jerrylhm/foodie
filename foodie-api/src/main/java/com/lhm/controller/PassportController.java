package com.lhm.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.MD5Utils;
import com.lhm.pojo.Users;
import com.lhm.pojo.bo.UserBO;
import com.lhm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/passport")
@Api(value = "登录相关", tags = "登录相关接口")
public class PassportController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userBO
     * @return
     */
    @PostMapping(value = "/regist")
    @ApiOperation(value = "用户注册", tags = "用户注册")
    public IMOOCJSONResult registeredUser(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        if (StringUtils.isAnyBlank(username, password, confirmPassword))
            return IMOOCJSONResult.errorMsg("参数不全");
        if (!StringUtils.equals(password, confirmPassword))
            return IMOOCJSONResult.errorMsg("密码和确认密码不一致");
        if (userService.isUsernameExist(username))
            return IMOOCJSONResult.errorMsg("用户名已存在");
        return IMOOCJSONResult.ok(userService.insert(userBO));
    }

    /**
     * 用户名查重
     * @param username 需要查重的用户名
     * @return
     */
    @GetMapping("/usernameIsExist")
    @ApiOperation(value = "用户名查重", tags = "用户名查重")
    public IMOOCJSONResult isUsernameExist(@RequestParam(required = true) String username) {
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        return IMOOCJSONResult.ok(userService.isUsernameExist(username));
    }

    /**
     * 登录
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", tags = "登录")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isBlank(userBO.getUsername()))
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        Users user = userService.login(userBO.getUsername(), userBO.getPassword());
        if (user == null) return IMOOCJSONResult.errorMsg("用户名或密码错误123");
        user.toSalfData();
        CookieUtils.setCookie(request, response, "user", JSONObject.toJSON(user).toString(), true);
        return IMOOCJSONResult.ok(user);
    }

    /**
     * 登录
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    @ApiOperation(value = "注销", tags = "注销")
    public IMOOCJSONResult logout(String userId, HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        CookieUtils.deleteCookie(request, response, "user");
        return IMOOCJSONResult.ok();
    }
}
