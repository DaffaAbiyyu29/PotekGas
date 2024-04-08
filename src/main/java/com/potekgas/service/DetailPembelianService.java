package com.potekgas.service;

import com.potekgas.response.DtoResponse;
import com.potekgas.vo.DetailPembelianVo;
import com.potekgas.vo.PembelianVo;

public interface DetailPembelianService {
    DtoResponse getAllDetailPembelian();
    DtoResponse findDetailPembelianByTrsId(int id);
    DtoResponse saveDetailPembelian(DetailPembelianVo detailPembelianVo);
}
