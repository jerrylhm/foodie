package com.lhm.service;

import com.lhm.pojo.Users;
import com.lhm.pojo.bo.UserBO;

import java.util.Map;

public interface UserService {

    public boolean isUsernameExist(String username);

    public Users insert(UserBO userBO);

    public Users login(String username, String password) throws Exception;
}
