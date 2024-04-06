package com.potekgas.dao.impl;

import com.potekgas.dao.PembelianDao;
import com.potekgas.model.Pembelian;
import com.potekgas.model.User;
import com.potekgas.repository.PembelianRepository;
import com.potekgas.repository.UserRepository;
import com.potekgas.vo.PembelianVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PembelianDaoImpl implements PembelianDao {
    @Autowired
    private PembelianRepository pembelianRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PembelianVo> getAllPembelian() {
        Iterable<Pembelian> pembelians = pembelianRepository.findAll();
        List<PembelianVo> pembelianVos = new ArrayList<>();

        for (Pembelian item : pembelians){
            PembelianVo pembelianVo = new PembelianVo(item);

            User user = userRepository.findById(pembelianVo.getIdUser()).orElse(null);
            if (user != null) {
                pembelianVo.setNamaUser(user.getNama_user());
            }

            pembelianVos.add(pembelianVo);
        }
        return pembelianVos;
    }
}
