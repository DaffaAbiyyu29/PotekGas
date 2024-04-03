package com.potekgas.repository;

import com.potekgas.model.Obat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ObatRepository extends JpaRepository<Obat, Integer> {
    @Procedure(procedureName = "getObatActive")
    List<Obat> getObatActive();
    @Procedure(procedureName = "countObat")
    ArrayList countObat();
}
