package com.potekgas.dao;

import com.potekgas.vo.UserVo;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    List<UserVo> getAllUser();
    List<UserVo> getUserActive();
    List<UserVo> getUserById(int id);
    ArrayList countAdmin();
    ArrayList countKasir();
}
