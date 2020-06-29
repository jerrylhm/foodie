package com.lhm.service;

import com.lhm.pojo.Stu;

public interface StuService {
    public Stu getStuInfo(int id);

    public void saveStu(Stu stu);

    public void updateStu(int id);

    public void deleteStu(int id);

    public void saveParent();
    public void saveChildren();
}
