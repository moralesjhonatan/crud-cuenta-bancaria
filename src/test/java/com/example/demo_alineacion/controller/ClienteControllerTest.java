package com.example.demo_alineacion.controller;

import com.example.demo_alineacion.entity.Cliente;
import com.example.demo_alineacion.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    @DisplayName(value = "POST insertar /clientes BadRequest")
    public void testInsertarClienteBadRequest() {
        try {
            mockMvc.perform(post("/clientes")
                            .accept("application/json;charset=UTF-8"))

                    // Validate the response code and content type
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName(value = "POST insertar /clientes Success")
    public void testInsertarClienteSuccess() {
        Cliente cliente = new Cliente().toBuilder()
                .identificacion(10L)
                .contrasena("pass")
                .estado("Activo")
                .nombre("pepito")
                .genero("masculino")
                .direccion("calle falsa")
                .edad("33")
                .telefono("3111111")
                .build();

        doReturn("Cliente insertado correctamente: " + cliente.getNombre()).when(clienteService).insertarCliente(cliente);

        try {
            mockMvc.perform(post("/clientes")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(cliente)))

                    // Validate the response code and content type
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.TEXT_PLAIN+";charset=UTF-8"))

                    // Validate the returned fields
                    .andExpect(content().string(containsString("Cliente insertado correctamente")));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
