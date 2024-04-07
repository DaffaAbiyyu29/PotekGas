package com.potekgas.repository;

import com.potekgas.model.DetailPembelian;
import com.potekgas.model.Pembelian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailPembelianRepository extends JpaRepository<DetailPembelian, Integer> {

}
