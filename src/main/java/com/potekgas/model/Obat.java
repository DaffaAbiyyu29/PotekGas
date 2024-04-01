package com.potekgas.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ms_obat")
public class Obat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obat")
    private Integer id_obat;
    @Column(name = "nama_obat")
    private String nama_obat;
    @Column(name = "merk_obat")
    private String merk_obat;
    @Column(name = "jenis_obat")
    private Integer jenis_obat;
    @Column(name = "tgl_kadaluarsa")
    private Date tgl_kadaluarsa;
    @Column(name = "harga")
    private Float harga;
    @Column(name = "stok")
    private Integer stok;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "status")
    private Integer status;

    public Obat() {
    }

    public Obat(Integer id_obat, String nama_obat, String merk_obat, Integer jenis_obat, Date tgl_kadaluarsa, Float harga, Integer stok, String keterangan, Integer status) {
        this.id_obat = id_obat;
        this.nama_obat = nama_obat;
        this.merk_obat = merk_obat;
        this.jenis_obat = jenis_obat;
        this.tgl_kadaluarsa = tgl_kadaluarsa;
        this.harga = harga;
        this.stok = stok;
        this.keterangan = keterangan;
        this.status = status;
    }

    public Integer getId_obat() {
        return id_obat;
    }

    public void setId_obat(Integer id_obat) {
        this.id_obat = id_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public String getMerk_obat() {
        return merk_obat;
    }

    public void setMerk_obat(String merk_obat) {
        this.merk_obat = merk_obat;
    }

    public Integer getJenis_obat() {
        return jenis_obat;
    }

    public void setJenis_obat(Integer jenis_obat) {
        this.jenis_obat = jenis_obat;
    }

    public Date getTgl_kadaluarsa() {
        return tgl_kadaluarsa;
    }

    public void setTgl_kadaluarsa(Date tgl_kadaluarsa) {
        this.tgl_kadaluarsa = tgl_kadaluarsa;
    }

    public Float getHarga() {
        return harga;
    }

    public void setHarga(Float harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
