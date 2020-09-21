package com.lhm.service.impl;

import com.imooc.utils.MD5Utils;
import com.lhm.mapper.CarouselMapper;
import com.lhm.mapper.UsersMapper;
import com.lhm.pojo.Carousel;
import com.lhm.pojo.Users;
import com.lhm.pojo.bo.UserBO;
import com.lhm.service.CarouselService;
import com.lhm.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", isShow);
        List<Carousel> carousels = carouselMapper.selectByExample(example);
        return carousels;
    }
}
