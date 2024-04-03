package com.potekgas.service.impl;

import com.potekgas.dao.ObatDao;
import com.potekgas.model.Obat;
import com.potekgas.model.User;
import com.potekgas.repository.ObatRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.ObatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.potekgas.constant.ObatConstant.*;
import static com.potekgas.constant.UserConstant.mEmptyData;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
    @Autowired
    private ObatDao obatDao;

    @Autowired
    private ObatRepository obatRepository;

    @Override
    public DtoResponse getAllObat() {
        if (obatDao.getAllObat() != null) return new DtoResponse(200, obatDao.getAllObat());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse getObatActive() {
        if (obatDao.getObatActive() != null) return new DtoResponse(200, obatDao.getObatActive());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse countObat() {
        return new DtoResponse(200, obatDao.countObat());
    }

    @Override
    public DtoResponse getObatById(int id) {
        if(obatDao.getObatById(id) != null){
            return new DtoResponse(200, obatDao.getObatById(id));
        }
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse saveObat(Obat obat) {
        // validasi obat duplikat ketika create
        for (Obat existingObat : obatRepository.findAll()) {
            if (existingObat.getNama_obat().equals(obat.getNama_obat())) {
                return new DtoResponse(400, null, mDuplicateObat);
            }
        }
        try {
            obat.setStatus(1);
            obatRepository.save(obat);
            return new DtoResponse(200, obat, mCreateSuccess);
        } catch (Exception e) {
            return new DtoResponse(500, obat, mCreateFailed);
        }
    }

    @Override
    public DtoResponse updateObat(Obat obat) {
        // Cari obat yang sudah ada dalam database
        Obat existingObat = obatRepository.findById(obat.getId_obat()).orElse(null);
        if (existingObat == null) {
            return new DtoResponse(404, null, mNotFound);
        }

        // Periksa nama obat ada dalam database
        if (!existingObat.getNama_obat().equals(obat.getNama_obat())) {
            for (Obat checkObat : obatRepository.findAll()) {
                if (checkObat.getNama_obat().equals(obat.getNama_obat())) {
                    return new DtoResponse(400, null, mDuplicateObat);
                }
            }
        }
        try {
            Obat updatedObat = obatRepository.save(obat);
            if (updatedObat != null)
                return new DtoResponse(200, updatedObat, mUpdateSuccess);
            else
                return new DtoResponse(404, null, mNotFound);
        } catch (Exception e) {
            return new DtoResponse(500, null, mUpdateFailed);
        }
    }


    @Override
    public DtoResponse deleteObat(Obat obat) {
        try {
            Optional<Obat> optionalObat = obatRepository.findById(obat.getId_obat());

            if (optionalObat.isPresent()) {
                Obat existingObat = optionalObat.get();

                existingObat.setStatus(0);

                Obat deleteObat = obatRepository.save(existingObat);
                return new DtoResponse(200, deleteObat, mDeleteSuccess);
            } else {
                return new DtoResponse(404, null, mNotFound);
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, mDeleteFailed);
        }
    }
}
