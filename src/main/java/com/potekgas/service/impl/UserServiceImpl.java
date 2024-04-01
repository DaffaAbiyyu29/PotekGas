package com.potekgas.service.impl;

import com.potekgas.dao.UserDao;
import com.potekgas.model.User;
import com.potekgas.repository.UserRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.potekgas.constant.UserConstant.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DtoResponse getAllUsers() {
        if (userDao.getAllUser() != null) return new DtoResponse(200, userDao.getAllUser());
        return new DtoResponse(200, null, mEmptyData);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DtoResponse saveUser(User user) {
        // validasi user duplikat ketika create
        for (User existingUser : userRepository.findAll()) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                return new DtoResponse(400, null, mDuplicateUser);
            }
        }
        try {
            // Enkripsi kata password sebelum disimpan
            String encryptedPassword = hashPassword(user.getPassword());
            user.setPassword(encryptedPassword);
            userRepository.save(user);
            return new DtoResponse(200, user, mCreateSuccess);
        } catch (Exception e) {
            return new DtoResponse(500, user, mCreateFailed);
        }
    }

    @Override
    public DtoResponse updateUser(User user) {
        // Cari user yang sudah ada dalam database
        User existingUser = userRepository.findById(user.getId_user()).orElse(null);
        if (existingUser == null) {
            return new DtoResponse(404, null, mNotFound);
        }

        // Periksa email atau username ada dalam database
        if (!existingUser.getUsername().equals(user.getUsername())) {
            // Validasi user duplikat ketika update
            for (User checkUser : userRepository.findAll()) {
                if (checkUser.getUsername().equals(user.getUsername())) {
                    return new DtoResponse(400, null, mDuplicateUser);
                }
            }
        }
        try {
            // Enkripsi kata password jika ada perubahan kata sandi
            String existingPassword = userRepository.findById(user.getId_user()).map(User::getPassword).orElse(null);
            if (existingPassword != null && !existingPassword.equals(user.getPassword())) {
                String encryptedPassword = hashPassword(user.getPassword());
                user.setPassword(encryptedPassword);
            }
            User updatedUser = userRepository.save(user);
            if (updatedUser != null)
                return new DtoResponse(200, updatedUser, mUpdateSuccess);
            else
                return new DtoResponse(404, null, mNotFound);
        } catch (Exception e) {
            return new DtoResponse(500, null, mUpdateFailed);
        }
    }

    @Override
    public DtoResponse deleteUser(User user) {
        User userData = userRepository.findById(user.getId_user()).orElseThrow();
        if (userData != null) {
            try {
                userRepository.delete(user);
                return new DtoResponse(200, userData, mDeleteSuccess);
            } catch (Exception e) {
                return new DtoResponse(500, userData, mDeleteFailed);
            }
        }
        return new DtoResponse(404, null, mNotFound);
    }
}
