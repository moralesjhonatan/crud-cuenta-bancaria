package com.example.demo_alineacion.service.impl;

import com.example.demo_alineacion.entity.Cliente;
import com.example.demo_alineacion.repository.ClienteRepository;
import com.example.demo_alineacion.response.ClienteResponse;
import com.example.demo_alineacion.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<ClienteResponse> obtenerTodosClientes() {
        List<ClienteResponse> clienteResponses = new ArrayList<>();
        clienteRepository.findAll().forEach(cliente -> {
            ClienteResponse clienteResponse = new ClienteResponse();
            clienteResponse.setNombres(cliente.getNombre());
            clienteResponse.setDireccion(cliente.getDireccion());
            clienteResponse.setTelefono(cliente.getTelefono());
            clienteResponse.setContrasena(cliente.getContrasena());
            clienteResponse.setEstado(cliente.getEstado());
            clienteResponses.add(clienteResponse);
        });
        return clienteResponses;
    }

    @Override
    public String insertarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return "Cliente insertado correctamente: " + cliente.getNombre();
    }

    @Override
    public void actualizarCliente(Cliente cliente, Long identificacion) {
        if(Objects.nonNull(cliente) && (Objects.isNull(cliente.getIdentificacion()) || cliente.getIdentificacion().equals(identificacion))){
            Cliente c = clienteRepository.findById(identificacion).get();
            if(Objects.nonNull(c)){
                cliente.setIdentificacion(identificacion);
                clienteRepository.save(cliente);
            }
        }
    }

    @Override
    public void eliminarCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);

    }
}
