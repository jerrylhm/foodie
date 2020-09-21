package com.lhm.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.lhm.pojo.Carousel;
import com.lhm.pojo.Users;
import com.lhm.pojo.bo.UserBO;
import com.lhm.service.CarouselService;
import com.lhm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/index")
@Api(value = "登录相关", tags = "登录相关接口")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    /**
     * 首页轮播
     * @return
     */
    @GetMapping(value = "/carousel")
    @ApiOperation(value = "首页轮播", tags = "首页轮播")
    public IMOOCJSONResult carousel() {
        List<Carousel> carousels = carouselService.queryAll(1);
        return IMOOCJSONResult.ok(carousels);
    }
}
