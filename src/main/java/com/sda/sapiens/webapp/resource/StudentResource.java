package com.sda.sapiens.webapp.resource;

import com.sda.sapiens.webapp.model.Student;
import com.sda.sapiens.webapp.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Slf4j
@Stateless
@Path("/api/student")
public class StudentResource {

    private final StudentRepository studentRepository;

    @Inject
    public StudentResource(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GET // nie ma body w zapytaniu
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents(){
        log.info("Zwracam listę studentów.");

        return Response.status(Response.Status.OK)
                .entity(studentRepository.getAll())
                .build();
    }

    @GET // nie ma body w zapytaniu
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam(value = "id") Long identifier){
        log.info("Zwracam studenta o id " + identifier);

        Optional<Student> optionalStudent = studentRepository.getById(identifier);
        if(optionalStudent.isPresent()){
            return Response.status(Response.Status.OK)
                    .entity(optionalStudent.get())
                    .build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
