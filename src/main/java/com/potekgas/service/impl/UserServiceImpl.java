package com.potekgas.service.impl;

import com.potekgas.dao.UserDao;
import com.potekgas.model.Obat;
import com.potekgas.model.User;
import com.potekgas.repository.UserRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.potekgas.constant.UserConstant.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DtoResponse getAllUsers() {
        if (userDao.getAllUser() != null) return new DtoResponse(200, userDao.getAllUser());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse getUserActive() {
        if (userDao.getUserActive() != null) return new DtoResponse(200, userDao.getUserActive());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse getUserById(int id) {
        if (userDao.getUserById(id) != null) {
            return new DtoResponse(200, userDao.getUserById(id));
        }
        return new DtoResponse(200, null, mEmptyData);
    }

    //    public String hashPassword(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] encodedHash = digest.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : encodedHash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public DtoResponse saveUser(User user) {
        // validasi user duplikat ketika create
        if (user == null || user.getUsername() == null || user.getPassword() == null ||
                user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return new DtoResponse(400, null, mNullReq);
        }

        for (User existingUser : userRepository.findAll()) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                return new DtoResponse(400, null, mDuplicateUser);
            }
        }
        try {
            // Enkripsi kata password sebelum disimpan
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            user.setStatus("1");
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

        // Periksa username ada dalam database
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
            /*String existingPassword = userRepository.findById(user.getId_user()).map(User::getPassword).orElse(null);
            if (existingPassword != null && !existingPassword.equals(user.getPassword())) {
                String encryptedPassword = hashPassword(user.getPassword());
                user.setPassword(encryptedPassword);
            }*/
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
        try {
            Optional<User> optionalUser = userRepository.findById(user.getId_user());

            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();

                existingUser.setStatus("0");

                User deleteUser = userRepository.save(existingUser);
                return new DtoResponse(200, deleteUser, mDeleteSuccess);
            } else {
                return new DtoResponse(404, null, mNotFound);
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, mDeleteFailed);
        }
    }

    @Override
    public DtoResponse loginUser(User user) {
        try {
            String msg = "";
            if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
                return new DtoResponse(404, null, mBlank);
            } else {
                User user1 = userRepository.findByUsername(user.getUsername());
                if (user1 != null) {
                    String password = user.getPassword();
                    String encodedPassword = user1.getPassword();
                    boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
                    if (isPwdRight) {
                        List<Map<String, String>> result = new ArrayList<>();

                        Map<String, String> userMap = new HashMap<>();
                        userMap.put("id", user1.getId_user().toString());
                        userMap.put("name", user1.getNama_user());
                        userMap.put("role", user1.getRole());
                        result.add(userMap);
                        return new DtoResponse(200, result, mLoginSuccess);
                    } else {
                        return new DtoResponse(500, null, mPasswordFailed);
                    }
                } else {
                    return new DtoResponse(404, null, mUsernameFailed);
                }
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, mLoginFailed);
        }
    }

    @Override
    public DtoResponse countAdmin() {
        return new DtoResponse(200, userDao.countAdmin());
    }

    @Override
    public DtoResponse countKasir() {
        return new DtoResponse(200, userDao.countKasir());
    }

    @Override
    public DtoResponse findUserById(int id) {
        if (userDao.findUserById(id) != null) return new DtoResponse(200, userDao.findUserById(id));
        return new DtoResponse(200, null, mEmptyData);
    }
}
