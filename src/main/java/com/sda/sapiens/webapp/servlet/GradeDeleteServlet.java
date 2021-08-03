package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.dao.EntityDao;
import com.sda.sapiens.webapp.dao.GradeDao;
import com.sda.sapiens.webapp.model.Grade;
import com.sda.sapiens.webapp.model.Student;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sda.sapiens.webapp.servlet.ServletURL.STUDENT_DETAILS;

@Slf4j
@WebServlet("/grade/delete")
public class GradeDeleteServlet extends HttpServlet {
    private final EntityDao<Grade> gradeDao;

    @Inject
    public GradeDeleteServlet(EntityDao<Grade> gradeDao) {
        this.gradeDao = gradeDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0. odczytać parametr z zapytania
        String studentId = req.getParameter("studentId");
        String gradeId = req.getParameter("gradeId");

        log.info("Usuwamy ocene o identyfikatorze: " + gradeId);

        // 1. musimy usunąć element z bazy, podajemy identyfikator obiektu usuwanego.
        Long identifier = Long.parseLong(gradeId);
        gradeDao.delete(Grade.class, identifier);

        // 2. przekierować użytkownika na adres "z którego przyszedł"
        // referer to nazwa nagłówka (header) który zawsze otrzymujemy od użytkownika/z requestu
//        String urlReferer = req.getHeader("referer");
        resp.sendRedirect(req.getContextPath() + STUDENT_DETAILS + "?studentId=" + studentId);
    }
}
