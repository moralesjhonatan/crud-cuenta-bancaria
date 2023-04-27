package com.example.demo_alineacion.controller;

import com.example.demo_alineacion.entity.Cuenta;
import com.example.demo_alineacion.entity.Movimiento;
import com.example.demo_alineacion.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovimientoController {

    private static final Logger logger = LoggerFactory.getLogger(MovimientoController.class);
    @Autowired
    MovimientoService movimientoService;

    @RequestMapping(value = "/movimientos", method = RequestMethod.POST)
    public ResponseEntity<String> insertarMovimiento(@RequestBody Movimiento movimiento) {
        try {
            logger.info("Inicio insertar movimiento");
            String msgResponse = movimientoService.insertarMovimiento(movimiento);
            return new ResponseEntity<String>(msgResponse, HttpStatus.OK);
        }catch (Exception e) {
            logger.info("Se presento un error al insertar el movimiento");
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
