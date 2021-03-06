package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.model.Student;
import com.sda.sapiens.webapp.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet("/student/edit")
public class StudentEditServlet extends HttpServlet {

    private final StudentRepository studentDao;

    @Inject
    public StudentEditServlet(StudentRepository studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Edycja studenta.");
        // 0. pobierz studenta z bazy, po uprzednim załadowaniu identyfikatora z parametru
        String studentId = req.getParameter("studentId");
        Long identifier = Long.parseLong(studentId);

        Optional<Student> student = studentDao.getById(identifier);
        if (student.isPresent()) {
            // jeśli go znaleźliśmy
            Student studentFromDatabase = student.get(); // wydobycie obiektu z Optional

            // 1. dodaj studenta jako atrybut do przekazania we frontendzie
            req.setAttribute("dane_studenta_edytowanego", studentFromDatabase);

            // 2. załaduj formularz
            req.getRequestDispatcher("/student-form.jsp").forward(req, resp);
        }else {
            // jeśli nie udało się znaleźć studenta to:
            // przekierujemy na listę studentów

            resp.sendRedirect(req.getContextPath() + "/student");
        }
    }
}
