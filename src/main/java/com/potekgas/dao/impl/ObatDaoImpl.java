package com.potekgas.dao.impl;

import com.potekgas.dao.ObatDao;
import com.potekgas.model.Obat;
import com.potekgas.repository.ObatRepository;
import com.potekgas.vo.ObatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            if("0".equals(obatVo.getJenis())) {
                obatVo.setJenis("Tablet");
            } else if("1".equals(obatVo.getJenis())) {
                obatVo.setJenis("Kapsul");
            }

            if("0".equals(obatVo.getStatus())) {
                obatVo.setStatus("Tidak Aktif");
            } else if("1".equals(obatVo.getStatus())) {
                obatVo.setStatus("Aktif");
            }
            obatVos.add(obatVo);
        }
        return obatVos;
    }

    @Override
    public List<ObatVo> getObatActive() {
        Iterable<Obat> obats = obatRepository.getObatActive();
        List<ObatVo> obatVos = new ArrayList<>();
        for (Obat item : obats) {
            ObatVo obatVo = new ObatVo(item);
            if("0".equals(obatVo.getJenis())) {
                obatVo.setJenis("Tablet");
            } else if("1".equals(obatVo.getJenis())) {
                obatVo.setJenis("Kapsul");
            }

            if("0".equals(obatVo.getStatus())) {
                obatVo.setStatus("Tidak Aktif");
            } else if("1".equals(obatVo.getStatus())) {
                obatVo.setStatus("Aktif");
            }
            obatVos.add(obatVo);
        }
        return obatVos;
    }

    @Override
    public List<ObatVo> getObatById(int id) {
        Optional<Obat> userOptional = obatRepository.findById(id);
        List<ObatVo> obatVos = new ArrayList<>();
        userOptional.ifPresent(user -> {
            ObatVo obatVo = new ObatVo(user);
            obatVos.add(obatVo);
        });
        return obatVos;
    }
}
