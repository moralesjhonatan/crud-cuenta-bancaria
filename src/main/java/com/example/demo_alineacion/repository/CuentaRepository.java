package com.example.demo_alineacion.repository;

import com.example.demo_alineacion.entity.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {
//    @Query("select c.numero_cuenta from Cuenta c where c.identificacion = ?1")
//    List<Long> findAllNumeroCuentaByUser(Long identificacion);

    @Query("select c from Cuenta c where c.identificacion = ?1")
    List<Cuenta> findAllByUser(Long identificacion);

    @Query(value = "select * from alineacion.Cuenta c where c.numero_cuenta = ?1", nativeQuery = true)
    Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta);
}
