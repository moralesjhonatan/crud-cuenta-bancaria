package com.example.demo_alineacion.repository;

import com.example.demo_alineacion.entity.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

    @Query(value = "select * from alineacion.Movimientos c where c.numero_cuenta = ?1 order by c.id desc limit 1", nativeQuery = true)
    List<Movimiento> findUltimoMovimiento(Long numeroCuenta);

    /*@Query(value = "select ifnull(max(id) + 1, 1) from alineacion.movimientos", nativeQuery = true)
    int findNuevoId();*/
}
