package com.potekgas.service;

import com.potekgas.model.User;
import com.potekgas.response.DtoResponse;

public interface UserService {
    DtoResponse getAllUsers();
    DtoResponse getUserActive();
    DtoResponse getUserById(int id);
    DtoResponse countAdmin();
    DtoResponse countKasir();
    DtoResponse saveUser(User user);
    DtoResponse updateUser(User user);
    DtoResponse deleteUser(User user);
    DtoResponse loginUser(User user);
}
