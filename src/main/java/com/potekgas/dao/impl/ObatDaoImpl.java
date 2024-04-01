package com.potekgas.dao.impl;

import com.potekgas.dao.ObatDao;
import com.potekgas.model.Obat;
import com.potekgas.repository.ObatRepository;
import com.potekgas.vo.ObatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ObatDaoImpl implements ObatDao {
    @Autowired
    private ObatRepository obatRepository;
    @Override
    public List<ObatVo> getAllObat() {
        Iterable<Obat> obats = obatRepository.findAll();
        List<ObatVo> obatVos = new ArrayList<>();
        for (Obat item : obats) {
            ObatVo obatVo = new ObatVo(item);
            obatVos.add(obatVo);
        }
        return obatVos;
    }
}
