package com.lhm.service.impl;

import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.MD5Utils;
import com.lhm.mapper.UsersMapper;
import com.lhm.pojo.Users;
import com.lhm.pojo.bo.UserBO;
import com.lhm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    private final String DEFAULT_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Override
    public boolean isUsernameExist(String username) {
        Example exp = new Example(Users.class);
        Example.Criteria criteria = exp.createCriteria();
        criteria.andEqualTo("username", username);
        Users users = usersMapper.selectOneByExample(exp);
        return users != null;
    }

    @Override
    public Users insert(UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        Users users = new Users();
        String userId = sid.nextShort();
        users.setId(userId);
        users.setUsername(username);
        try {
            users.setPassword(MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        users.setNickname(username);
        users.setFace(DEFAULT_FACE);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        usersMapper.insert(users);
        return users;
    }

    @Override
    public Users login(String username, String password) throws Exception {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", MD5Utils.getMD5Str(password));
        Users users = usersMapper.selectOneByExample(example);
        return users;
    }
}
