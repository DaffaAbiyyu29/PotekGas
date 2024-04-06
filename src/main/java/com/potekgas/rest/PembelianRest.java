package com.potekgas.rest;

import com.potekgas.model.Pembelian;
import com.potekgas.model.User;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.PembelianService;
import com.potekgas.vo.PembelianVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/pembelian")
public class PembelianRest {
    @Autowired
    PembelianService pembelianService;

    public PembelianRest(PembelianService pembelianService) {
        this.pembelianService = pembelianService;
    }

    @GetMapping("/getPembelians")
    public DtoResponse getPembelian() {
        return pembelianService.getAllPembelian();
    }

    @PostMapping("/saveUser")
    public DtoResponse savePembelian(@RequestBody PembelianVo pembelianVo){
        return pembelianService.savePembelian(pembelianVo);
    }
}
