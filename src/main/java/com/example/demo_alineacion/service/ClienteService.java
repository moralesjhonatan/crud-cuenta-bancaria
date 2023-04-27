package com.example.demo_alineacion.service;

import com.example.demo_alineacion.entity.Cliente;
import com.example.demo_alineacion.response.ClienteResponse;

import java.util.List;

public interface ClienteService {
    List<ClienteResponse> obtenerTodosClientes();
    String insertarCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente, Long clientId);
    void eliminarCliente(Long clienteId);
}
