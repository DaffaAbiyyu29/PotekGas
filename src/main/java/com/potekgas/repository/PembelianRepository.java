package com.potekgas.repository;

import com.potekgas.model.Pembelian;
import com.potekgas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembelianRepository extends JpaRepository<Pembelian, Integer> {

}
