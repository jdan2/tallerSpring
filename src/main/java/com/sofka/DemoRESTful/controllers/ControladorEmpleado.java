package com.sofka.DemoRESTful.controllers;

import com.sofka.DemoRESTful.dto.EmpleadoDTO;
import com.sofka.DemoRESTful.services.ServicioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class ControladorEmpleado {
    @Autowired
    ServicioEmpleado servicioEmpleado;

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(servicioEmpleado.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmpleadoDTO>> findAll() {
        return new ResponseEntity(servicioEmpleado.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<EmpleadoDTO> create(@RequestBody EmpleadoDTO empleadoDTO) {
        return new ResponseEntity(servicioEmpleado.crear(empleadoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<EmpleadoDTO> update(@RequestBody EmpleadoDTO empleadoDTO) {
        if (empleadoDTO.getId() != null) {
            return new ResponseEntity(servicioEmpleado.modificar(empleadoDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioEmpleado.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}