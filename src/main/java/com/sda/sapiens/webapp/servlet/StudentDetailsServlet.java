package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.model.Grade;
import com.sda.sapiens.webapp.model.Student;
import com.sda.sapiens.webapp.repository.GradeRepository;
import com.sda.sapiens.webapp.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.sda.sapiens.webapp.servlet.ServletURL.STUDENT_DETAILS;

// REST API = JSON
// SOAP     = XML
@Slf4j
@WebServlet(STUDENT_DETAILS)
public class StudentDetailsServlet extends HttpServlet {

    private final StudentRepository studentDao;
    private final GradeRepository gradeRepository;

    @Inject
    public StudentDetailsServlet(StudentRepository studentDao, GradeRepository gradeRepository) {
        this.studentDao = studentDao;
        this.gradeRepository = gradeRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long identifier = Long.parseLong(req.getParameter("studentId"));

        // ładujemy studenta z bazy danych
        Optional<Student> optionalStudent = studentDao.getById(identifier);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            req.setAttribute("student", student);

            List<Grade> ocenyStudenta = gradeRepository.getAll(student);
            req.setAttribute("studentGrades", ocenyStudenta);

            // ładujemy stronę ze szczegółami studenta
            req.getRequestDispatcher("/student-details.jsp").forward(req, resp);
        }else{
            // redirect na listę studentów
            resp.sendRedirect(req.getContextPath() + "/student");
        }
    }
}
