package com.sofka.DemoRESTful;

import com.sofka.DemoRESTful.dto.EmpleadoDTO;
import com.sofka.DemoRESTful.models.Empleado;
import com.sofka.DemoRESTful.repositories.RepositorioEmpleado;
import com.sofka.DemoRESTful.services.ServicioEmpleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioEmpleadoTest {

    @MockBean
    private RepositorioEmpleado repositorioEmpleado;

    @Autowired
    private ServicioEmpleado servicioEmpleado;

    @Test
    @DisplayName("Test findAll Success")
    public void obtenerTodos() {
        var dato1 = new Empleado();
        dato1.setId("1111");
        dato1.setNombre("Jorge Ramirez");
        dato1.setRol("Gerente");
        var dato2 = new Empleado();
        dato1.setId("2222");
        dato1.setNombre("Pedro Contreras");
        dato1.setRol("Vicepresidente");
        var lista = new ArrayList<Empleado>();
        lista.add(dato1);
        lista.add(dato2);
        Mockito.when(repositorioEmpleado.findAll()).thenReturn(lista);

        var resultado = servicioEmpleado.obtenerTodos();

        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals(dato1.getNombre(), resultado.get(0).getNombre());
        Assertions.assertEquals(dato2.getNombre(), resultado.get(1).getNombre());
    }

    @Test
    @DisplayName("Test save Success")
    public void crear() {

        var dato1 = new Empleado();
        dato1.setId("1111");
        dato1.setNombre("Jorge Ramirez");
        dato1.setRol("Gerente");

        var dato2 = new EmpleadoDTO();
        dato2.setNombre("Jorge Ramirez");
        dato2.setRol("Gerente");

        Mockito.when(repositorioEmpleado.save(any())).thenReturn(dato1);

        var resultado = servicioEmpleado.crear(dato2);

        Assertions.assertNotNull(resultado, "el valor guardado no debe ser nulo");

        Assertions.assertEquals(dato1.getNombre(), resultado.getNombre(), "el nombre no corresponde");
        Assertions.assertEquals(dato1.getRol(), resultado.getRol(), "el rol no corresponde");
    }



}
