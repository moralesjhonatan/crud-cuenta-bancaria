package com.example.demo_alineacion.service.impl;

import com.example.demo_alineacion.entity.Movimiento;
import com.example.demo_alineacion.repository.MovimientoRepository;
import com.example.demo_alineacion.response.CuentaResponse;
import com.example.demo_alineacion.service.CuentaService;
import com.example.demo_alineacion.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    static final String DEPOSITO = "DEPOSITO";
    //static final String RETIRO = "RETIRO";
    static final String CUENTA_ACTIVA = "True";

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaService cuentaService;

    @Override
    public String insertarMovimiento(Movimiento movimiento) {
        String msgResponse = "La cuenta no existe.";
        CuentaResponse cuenta = cuentaService.obtenerPorNumeroCuenta(movimiento.getNumeroCuenta());

        if(Objects.nonNull(cuenta)) {
            String depositoRetiro = movimiento.getTipoMovimiento().toLowerCase();
            if(cuenta.getEstado().equals(CUENTA_ACTIVA)) {
                List<Movimiento> movimientos = movimientoRepository.findUltimoMovimiento(movimiento.getNumeroCuenta());
                Double nuevoSaldo = calcularNuevoSaldo(movimiento, movimientos, cuenta);

                if (Objects.nonNull(nuevoSaldo)) {
                    movimiento.setFecha(LocalDateTime.now());
                    movimiento.setSaldo(nuevoSaldo.doubleValue());
                    movimientoRepository.save(movimiento);
                    msgResponse = "El " + depositoRetiro + " por valor de " + movimiento.getValor().doubleValue() + " se realizo correctamente. Nuevo saldo es " + movimiento.getSaldo().doubleValue();
                } else {
                    msgResponse = "No se puede realizar el " + depositoRetiro + " porque el saldo no puede quedar negativo.";
                }
            } else {
                msgResponse = "No se puede realizar el " + depositoRetiro + " porque la cuenta esta inactiva.";
            }
        }
        return msgResponse;
    }

    private Double calcularNuevoSaldo(Movimiento movimientoNuevo, List<Movimiento> movimientos, CuentaResponse cuenta) {
        Double saldoAEvaluar;
        if (movimientos.isEmpty()) {
            saldoAEvaluar = movimientoNuevo.getValor().doubleValue() + cuenta.getSaldoInicial().doubleValue();
        } else {
            saldoAEvaluar = movimientos.get(0).getSaldo().doubleValue() + movimientoNuevo.getValor().doubleValue();
        }
        return saldoAEvaluar.doubleValue() >= 0 ? saldoAEvaluar.doubleValue() : null;
    }
}
