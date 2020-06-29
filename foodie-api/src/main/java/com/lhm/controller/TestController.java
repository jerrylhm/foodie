package com.lhm.controller;

import com.lhm.pojo.Stu;
import com.lhm.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private StuService stuService;

    @GetMapping("")
    public Object test(Integer id) {
        return stuService.getStuInfo(id);
    }

    @PutMapping("update")
    public Object update(Integer id) {
        stuService.updateStu(id);
        return "ok";
    }

    @PostMapping("ts")
    @Transactional(propagation = Propagation.REQUIRED)
    public Object ts() {
        Stu stu = new Stu();
        stu.setName("1");
        stu.setAge(30);
        stuService.saveStu(stu);
        try {
            stuService.saveParent();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int a = 1 / 0;
        return "ok";
    }
}
