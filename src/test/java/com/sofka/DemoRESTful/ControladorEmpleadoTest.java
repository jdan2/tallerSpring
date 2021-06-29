package com.sofka.DemoRESTful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.DemoRESTful.dto.EmpleadoDTO;
import com.sofka.DemoRESTful.services.ServicioEmpleado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class ControladorEmpleadoTest {

    @MockBean
    private ServicioEmpleado servicioEmpleado;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /empleados success")
    public void findAll() throws Exception {
        //setup mock service
        var dato1 = new EmpleadoDTO();
        dato1.setId("1111");
        dato1.setNombre("Jorge Ramirez");
        dato1.setRol("Gerente");
        var dato2 = new EmpleadoDTO();
        dato2.setId("2222");
        dato2.setNombre("Pedro Contreras");
        dato2.setRol("Vicepresidente");
        var lista = new ArrayList<EmpleadoDTO>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(servicioEmpleado.obtenerTodos()).thenReturn(lista);

        //execute Get request
        mockMvc.perform(get("/empleados"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is("1111")))
                .andExpect((ResultMatcher) jsonPath("$[0].nombre", is("Jorge Ramirez")))
                .andExpect((ResultMatcher) jsonPath("$[0].rol", is("Gerente")))
                .andExpect((ResultMatcher) jsonPath("$[1].id", is("2222")))
                .andExpect((ResultMatcher) jsonPath("$[1].nombre", is("Pedro Contreras")))
                .andExpect((ResultMatcher) jsonPath("$[1].rol", is("Vicepresidente")));
    }

    @Test
    @DisplayName("POST /empleados/crear success")
    public void create() throws Exception {
        // Setup our mocked service
        var datoPost = new EmpleadoDTO();
        datoPost.setNombre("Jorge Ramirez");
        datoPost.setRol("Gerente");

        var datoReturn = new EmpleadoDTO();
        datoReturn.setId("2222");
        datoReturn.setNombre("Jorge Ramirez");
        datoReturn.setRol("Gerente");

        Mockito.when(servicioEmpleado.crear(datoPost)).thenReturn(datoReturn);

        // Execute the POST request
        mockMvc.perform(post("/empleados/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(datoPost)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))

                // Validate the returned fields
                .andExpect((ResultMatcher)jsonPath("$.id", is("2222")))
                .andExpect((ResultMatcher)jsonPath("$.nombre", is("Jorge Ramirez")))
                .andExpect((ResultMatcher)jsonPath("$.rol", is("Gerente")));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
