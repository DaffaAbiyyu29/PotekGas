package com.potekgas.dao.impl;

import com.potekgas.dao.UserDao;
import com.potekgas.model.User;
import com.potekgas.repository.UserRepository;
import com.potekgas.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserVo> getAllUser() {
        Iterable<User> users = userRepository.findAll();
        List<UserVo> userVos = new ArrayList<>();
        for (User item : users) {
            UserVo userVo = new UserVo(item);
            userVos.add(userVo);
        }
        return userVos;
    }
}
