package com.example.demo_alineacion.controller;

import com.example.demo_alineacion.entity.Cliente;
import com.example.demo_alineacion.response.ClienteResponse;
import com.example.demo_alineacion.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteService clienteService;

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public ResponseEntity<List<ClienteResponse>> getClientes() {
        try {
            logger.info("Inicio obtener clientes");
            return new ResponseEntity<List<ClienteResponse>>(clienteService.obtenerTodosClientes(), HttpStatus.OK);
        }catch (Exception e) {
            List<ClienteResponse> response = new ArrayList<>();
            logger.info("Se presento un error al obtener los clientes");
            return new ResponseEntity<List<ClienteResponse>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }

    }

    @RequestMapping(value = "/clientes", method = RequestMethod.POST)
    public ResponseEntity<String> insertarCliente(@RequestBody Cliente cliente) {
        try {
            logger.info("Inicio insertar cliente");
            clienteService.insertarCliente(cliente);
            logger.info("Cliente insertado con éxito");
            return new ResponseEntity<String>("Cliente insertado correctamente: " + cliente.getNombre(), HttpStatus.OK);
        }catch (Exception e) {
            logger.info("Se presento un error al insertar el cliente");
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        }


    }

    @RequestMapping(value = "/clientes/{identificacion}", method = RequestMethod.PUT)
    public ResponseEntity<String> actualizarCliente(@RequestBody Cliente cliente, @PathVariable("identificacion") Long identificacion) {
        try {
            logger.info("Inicio actualizar cliente");
            clienteService.actualizarCliente(cliente, identificacion);
            logger.info("Cliente actualizado con éxito");
            return new ResponseEntity<String>("Cliente actualizado correctamente: " + cliente.getNombre(), HttpStatus.OK);
        }catch (Exception e) {
            logger.info("Se presento un error al actualizar el cliente");
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/clientes/{identificacion}", method = RequestMethod.DELETE)
    public ResponseEntity<String> eliminarCliente(@PathVariable("identificacion") Long identificacion) {
        try {
            logger.info("Inicio eliminar cliente");
            clienteService.eliminarCliente(identificacion);
            logger.info("Cliente eliminado con éxito");
            return new ResponseEntity<String>("Cliente eliminado correctamente: " + identificacion, HttpStatus.OK);
        }catch (Exception e) {
            logger.info("Se presento un error al eliminar el cliente");
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }


}
