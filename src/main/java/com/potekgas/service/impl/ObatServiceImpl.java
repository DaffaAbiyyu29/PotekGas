package com.potekgas.service.impl;

import com.potekgas.dao.ObatDao;
import com.potekgas.model.Obat;
import com.potekgas.repository.ObatRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.ObatService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.potekgas.constant.ObatConstant.*;

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
    public DtoResponse saveObat(Obat obat) {
        try {
            obatRepository.save(obat);
            return new DtoResponse(200, obat, mCreateSuccess);
        } catch (Exception e) {
            return new DtoResponse(500, obat, mCreateFailed);
        }
    }

    @Override
    public DtoResponse updateObat(Obat obat) {
        try {
            Optional<Obat> optionalProdi = obatRepository.findById(obat.getId_obat());
            if (optionalProdi.isPresent()) {
                Obat obat1 = optionalProdi.get();

                obat1.setNama_obat((obat.getNama_obat() != null ? obat.getNama_obat() : obat1.getNama_obat()));

                Obat updateObat = obatRepository.save(obat1);
                return new DtoResponse(200, updateObat, mUpdateSuccess);
            } else {
                return new DtoResponse(404, null, mNotFound);
            }
        } catch (Exception e) {
            return new DtoResponse(500, null, mUpdateFailed);
        }    }

    @Override
    public DtoResponse deleteObat(Obat obat) {
        Obat obatData = obatRepository.findById(obat.getId_obat()).orElseThrow();
        if (obatData !=null ) {
            try {
                obatData.setStatus(0);
                obatRepository.save(obatData);
                return new DtoResponse(200, obatData, mDeleteSuccess);
            } catch (Exception e) {
                return new DtoResponse(500, obatData, mDeleteFailed);
            }
        }
        return new DtoResponse(404, null, mNotFound);
    }
}
