package com.potekgas.vo;

import com.potekgas.model.Obat;

public class ObatVo {
    private int id;
    private String namaObat;
    private String merk;
    private String jenis;
    private Float harga;
    private String keterangan;

    public ObatVo() {
    }

    public ObatVo(Obat obat) {
        this.id = obat.getId_obat();
        this.namaObat = obat.getNama_obat();
        this.merk = obat.getMerk_obat();
        this.jenis = obat.getJenis_obat();
        this.harga = obat.getHarga();
        this.keterangan = obat.getKeterangan();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public Float getHarga() {
        return harga;
    }

    public void setHarga(Float harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
