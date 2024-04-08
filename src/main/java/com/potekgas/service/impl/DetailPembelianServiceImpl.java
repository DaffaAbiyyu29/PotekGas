package com.potekgas.service.impl;

import com.potekgas.dao.DetailPembelianDao;
import com.potekgas.model.DetailPembelian;
import com.potekgas.model.DetailPembelianPK;
import com.potekgas.model.Obat;
import com.potekgas.repository.DetailPembelianRepository;
import com.potekgas.repository.ObatRepository;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.DetailPembelianService;
import com.potekgas.vo.DetailPembelianVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.potekgas.constant.DetailPembelianConstant.*;

@Service
@Transactional
public class DetailPembelianServiceImpl implements DetailPembelianService {
    @Autowired
    DetailPembelianDao detailPembelianDao;
    @Autowired
    DetailPembelianRepository detailPembelianRepository;
    @Autowired
    ObatRepository obatRepository;

    @Override
    public DtoResponse getAllDetailPembelian() {
        if (detailPembelianDao.getAllDetailPembelian() != null) return new DtoResponse(200, detailPembelianDao.getAllDetailPembelian());
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse findDetailPembelianByTrsId(int id) {
        if (detailPembelianDao.findDetailPembelianByTrsId(id) != null) return new DtoResponse(200, detailPembelianDao.findDetailPembelianByTrsId(id));
        return new DtoResponse(200, null, mEmptyData);
    }

    @Override
    public DtoResponse saveDetailPembelian(DetailPembelianVo detailPembelianVo) {
        try {
            DetailPembelianPK detailPembelianPK = new DetailPembelianPK();
            detailPembelianPK.setId_detail(detailPembelianVo.getIdDetail());
            detailPembelianPK.setId_transaksi(detailPembelianVo.getIdTransaksi());
            detailPembelianPK.setId_obat(detailPembelianVo.getIdObat());

            List<DetailPembelianVo> listData = detailPembelianDao.getAllDetailPembelian();
            if (listData.isEmpty()){
                detailPembelianPK.setId_transaksi(1);
            } else {
                DetailPembelianVo lastData = listData.get(listData.size() - 1);
                detailPembelianPK.setId_detail(lastData.getIdDetail() + 1);
            }

            Obat existingObat = obatRepository.findById(detailPembelianVo.getIdObat()).orElse(null);
            if (existingObat == null) {
                return new DtoResponse(404, null, mEmptyObat);
            }

            DetailPembelian detailPembelian = new DetailPembelian();
            detailPembelian.setDetailPembelianPK(detailPembelianPK);
            detailPembelian.setJumlah(detailPembelianVo.getJumlah());

            detailPembelianRepository.save(detailPembelian);
            return new DtoResponse(200, detailPembelian, mCreateSuccess);
        } catch (Exception e){
            return new DtoResponse(500, detailPembelianVo, mCreateFailed);
        }
    }
}
