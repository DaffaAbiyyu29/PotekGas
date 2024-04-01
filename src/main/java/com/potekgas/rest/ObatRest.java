package com.potekgas.rest;

import com.potekgas.model.Obat;
import com.potekgas.response.DtoResponse;
import com.potekgas.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/obats")
public class ObatRest {
    @Autowired
    private ObatService obatService;

    public ObatRest(ObatService obatService) {
        this.obatService = obatService;
    }

    @GetMapping("/getObats")
    public DtoResponse getObats() {
        return obatService.getAllObat();
    }

    @PostMapping("/saveObat")
    public DtoResponse createObat(@RequestBody Obat obat) { return obatService.saveObat(obat); }

    @PostMapping("/updateObat")
    public DtoResponse updateObat(@RequestBody Obat obat) { return obatService.updateObat(obat); }

    @PostMapping("/deleteObat")
    public DtoResponse deleteObat(@RequestBody Obat obat) { return obatService.deleteObat(obat); }
}
