package com.sofka.DemoRESTful.repositories;

import com.sofka.DemoRESTful.models.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioEmpleado extends MongoRepository<Empleado, String> {
}
