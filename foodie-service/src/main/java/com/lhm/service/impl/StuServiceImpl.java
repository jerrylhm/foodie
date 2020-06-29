package com.lhm.service.impl;

import com.lhm.mapper.StuMapper;
import com.lhm.pojo.Stu;
import com.lhm.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveStu(Stu stu) {

        stuMapper.insert(stu);
    }

    @Override
    public void updateStu(int id) {
        Stu stu = new Stu();
        stu.setId(id);
        stu.setName("ming");
        stu.setAge(30);
        stuMapper.updateByPrimaryKey(stu);
    }

    @Override
    public void deleteStu(int id) {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveParent() {
        Stu stu = new Stu();
        stu.setName("p");
        stu.setAge(30);
        stuMapper.insertSelective(stu);
        int a = 1 / 0;
    }

    @Override
    public void saveChildren() {

    }
}
