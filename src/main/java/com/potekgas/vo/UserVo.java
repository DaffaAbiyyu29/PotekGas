package com.potekgas.vo;

import com.potekgas.model.User;

public class UserVo {
    private int id;
    private String nama;
    private String no_telp;
    private String role;
    private String username;
    private String password;
    private String status;

    public UserVo(){
    }

    public UserVo(User user) {
        this.id = user.getId_user();
        this.nama = user.getNama_user();
        this.no_telp = user.getNo_telp();
        this.role = user.getRole();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.status = user.getStatus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
