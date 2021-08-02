package com.sda.sapiens.webapp.servlet;


import com.sda.sapiens.webapp.dao.EntityDao;
import com.sda.sapiens.webapp.dao.StudentDao;
import com.sda.sapiens.webapp.model.Student;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/student/remove")
public class StudentRemoveServlet extends HttpServlet {

    private final EntityDao<Student> studentDao;

    @Inject
    public StudentRemoveServlet(EntityDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. odczytać parametr z zapytania
        String studentId = req.getParameter("studentId");
        log.info("Usuwamy studenta o identyfikatorze: " + studentId);

        // 1. musimy usunąć element z bazy, podajemy identyfikator obiektu usuwanego.
        Long identifier = Long.parseLong(studentId);
        studentDao.delete(Student.class, identifier);

        // 2. przekierować użytkownika na adres "z którego przyszedł"
        // referer to nazwa nagłówka (header) który zawsze otrzymujemy od użytkownika/z requestu
        String urlReferer = req.getHeader("referer");
        resp.sendRedirect(urlReferer);
    }
}
