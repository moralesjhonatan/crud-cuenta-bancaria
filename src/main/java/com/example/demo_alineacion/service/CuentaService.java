package com.example.demo_alineacion.service;


import com.example.demo_alineacion.entity.Cuenta;
import com.example.demo_alineacion.response.CuentaResponse;

import java.util.List;

public interface CuentaService {
    List<CuentaResponse> obtenerTodasCuentas();

    List<CuentaResponse> obtenerCuentasPorUsuario(Long identificacion);

    String insertarCuenta(Cuenta cuenta);
    void actualizarCuenta(Cuenta cuenta, Long numeroCuenta);
    void eliminarCuenta(Long cuentaId);

    CuentaResponse obtenerPorNumeroCuenta(Long numeroCuenta);
}
