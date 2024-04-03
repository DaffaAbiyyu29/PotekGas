package com.potekgas.dao;

import com.potekgas.vo.ObatVo;

import java.util.List;

public interface ObatDao {
    List<ObatVo> getAllObat();
    List<ObatVo> getObatActive();
    List<ObatVo> getObatById(int id);
}
