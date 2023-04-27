package com.example.demo_alineacion.controller;

import com.example.demo_alineacion.entity.Cuenta;
import com.example.demo_alineacion.response.CuentaResponse;
import com.example.demo_alineacion.service.CuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CuentaController {

    private static final Logger logger = LoggerFactory.getLogger(CuentaController.class);
    @Autowired
    CuentaService cuentaService;

    @RequestMapping(value = "/cuentas", method = RequestMethod.GET)
    public ResponseEntity<List<CuentaResponse>> getCuentas() {
        try {
            logger.info("Inicio obtener cuentas");
            return new ResponseEntity<List<CuentaResponse>>(cuentaService.obtenerTodasCuentas(), HttpStatus.OK);
        }catch (Exception e) {
            List<CuentaResponse> response = new ArrayList<>();
            logger.info("Se presento un error al obtener las cuentas");
            return new ResponseEntity<List<CuentaResponse>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/cuentas", method = RequestMethod.POST)
    public ResponseEntity<String> insertarCuenta(@RequestBody Cuenta cuenta) {
        try {
            logger.info("Inicio insertar cuenta");
            cuentaService.insertarCuenta(cuenta);
            logger.info("cuenta insertada con éxito");
            return new ResponseEntity<String>("Cuenta insertada correctamente: " + cuenta.getNumeroCuenta(), HttpStatus.OK);
        }catch (Exception e) {
            logger.info("Se presento un error al insertar la cuenta");
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @RequestMapping(value = "/cuentas/{numero_cuenta}", method = RequestMethod.PUT)
    public ResponseEntity<String> actualizarCuenta(@RequestBody Cuenta cuenta, @PathVariable("numero_cuenta") Long numero_cuenta) {
        try {
            logger.info("Inicio actualizar cuenta");
            cuentaService.actualizarCuenta(cuenta, numero_cuenta);
            logger.info("cuenta actualizada con éxito");
            return new ResponseEntity<String>("Cuenta actualizada correctamente: " + cuenta.getNumeroCuenta(), HttpStatus.OK);
        }catch (Exception e) {
            logger.info("Se presento un error al actualizar la cuenta");
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @RequestMapping(value = "/cuentas/{numero_cuenta}", method = RequestMethod.DELETE)
    public ResponseEntity<String> eliminarCuenta(@PathVariable("numero_cuenta") Long numero_cuenta) {
        try {
            logger.info("Inicio eliminar cuenta");
            cuentaService.eliminarCuenta(numero_cuenta);
            logger.info("cuenta eliminada con éxito");
            return new ResponseEntity<String>("Cuenta eliminada correctamente: " + numero_cuenta, HttpStatus.OK);
        }catch (Exception e) {
            logger.info("Se presento un error al actualizar la cuenta");
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }


}
