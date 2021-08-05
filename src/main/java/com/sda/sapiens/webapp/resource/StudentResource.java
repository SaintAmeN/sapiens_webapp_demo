package com.sda.sapiens.webapp.resource;

import com.sda.sapiens.webapp.model.Student;
import com.sda.sapiens.webapp.services.StudentService;
import com.sda.sapiens.webapp.model.dto.StudentEditRequest;
import com.sda.sapiens.webapp.repository.StudentRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Api
@Slf4j
@Stateless
@Path("/api/student")
public class StudentResource {

    // Servlety/Resource    <->     Service         <->      Repository
    // Przyjęcie/Odpowiedź  <->     Przetworzenie   <->      Metody na bazie danych
    //      na request          Logika Biz.                  Operacje na modelach
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Inject
    public StudentResource(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @GET // nie ma body w zapytaniu
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        log.info("Zwracam listę studentów.");

        return Response.status(Response.Status.OK)
                .entity(studentService.getAll())
                .build();
    }

    @GET // nie ma body w zapytaniu
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam(value = "id") Long identifier) {
        log.info("Zwracam studenta o id " + identifier);

        return Response.status(Response.Status.OK)
                .entity(studentService.getById(identifier))
                .build();
    }

    @POST // EDYCJA
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public Response editStudent(@PathParam(value = "id") Long identifier,
                                StudentEditRequest editRequest) {
        // może wysłać zapytanie i podać:
        // - id                     -- identyfikator obiektu w bazie który edytujemy
        // - obiekt edytujący       -- to będą parametry którymi zastępujemy orginalne w bazie
        log.info("Edytuje studenta o id " + identifier + " danymi " + editRequest);

        Student student = studentService.updateStudent(identifier, editRequest);

        return Response.status(Response.Status.OK)
                .entity(student)
                .build();
    }

    @PUT // dodawanie
    @Path("")
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public Response addStudent(StudentEditRequest editRequest) {
        // może wysłać zapytanie i podać:
        // - id                     -- identyfikator obiektu w bazie który edytujemy
        // - obiekt edytujący       -- to będą parametry którymi zastępujemy orginalne w bazie
        log.info("Dodaje studenta o id danymi " + editRequest);

        Student student = studentService.addStudent(editRequest);

        return Response.status(Response.Status.OK)
                .entity(student)
                .build();
    }
}
