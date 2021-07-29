package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.dao.StudentDao;
import com.sda.sapiens.webapp.model.Student;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@WebServlet("/student/form")
public class StudentFormServlet extends HttpServlet {

    private final StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/student-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentFirstName = req.getParameter("student-first-name");
        String studentLastName = req.getParameter("student-last-name");
        String studentIndex = req.getParameter("student-indeks");
        String studentBirthDate = req.getParameter("student-birth-date");

        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(studentBirthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (IllegalArgumentException iae) {
            resp.sendRedirect(req.getContextPath() + "/student/form?error=" + iae.getMessage());
            return;
        }

        // korzystam z adnotacji @Builder w klasie Student (patrz pod @Data)
        Student student = Student.builder()
                .firstName(studentFirstName)
                .lastName(studentLastName)
                .indeks(studentIndex)
                .birthDate(birthDate)
                .build();

        studentDao.saveOrUpdate(student);

        resp.sendRedirect(req.getContextPath() + "/student");
    }
}
