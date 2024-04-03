package com.potekgas.service;

import com.potekgas.model.Obat;
import com.potekgas.response.DtoResponse;

public interface ObatService {
    DtoResponse getAllObat();
    DtoResponse getObatActive();
    DtoResponse countObat();
    DtoResponse getObatById(int id);
    DtoResponse saveObat(Obat obat);
    DtoResponse updateObat(Obat obat);
    DtoResponse deleteObat(Obat obat);
}
