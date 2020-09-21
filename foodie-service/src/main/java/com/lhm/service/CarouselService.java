package com.lhm.service;

import com.lhm.pojo.Carousel;

import java.util.List;

public interface CarouselService {

    List<Carousel> queryAll(Integer isShow);
}
