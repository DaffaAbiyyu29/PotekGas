package com.potekgas.rest;

import com.potekgas.response.DtoResponse;
import com.potekgas.service.DetailPembelianService;
import com.potekgas.vo.DetailPembelianVo;
import com.potekgas.vo.PembelianVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/detailPembelian")
public class DetailPembelianRest {
    @Autowired
    DetailPembelianService detailPembelianService;

    public DetailPembelianRest(DetailPembelianService detailPembelianService) {
        this.detailPembelianService = detailPembelianService;
    }

    @GetMapping("/getDetailPembelians")
    public DtoResponse getDetailPembelian() {
        return detailPembelianService.getAllDetailPembelian();
    }

    @PostMapping("/saveDetailPembelian")
    public DtoResponse savePembelian(@RequestBody DetailPembelianVo detailPembelianVo){
        return detailPembelianService.saveDetailPembelian(detailPembelianVo);
    }
}
