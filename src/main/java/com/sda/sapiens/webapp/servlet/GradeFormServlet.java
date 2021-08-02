package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.dao.StudentDao;
import com.sda.sapiens.webapp.model.GradeSubject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/grade/form")
public class GradeFormServlet extends HttpServlet {

    private final StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // odebranie parametru z zapytania ?studennt_id=xxx
        String studentIdentifier = req.getParameter("student_id");

        // przekazanie parametru aby wiadomo było któremu studentowi dodajemy ocenę
        req.setAttribute("student_identifier", studentIdentifier);
        req.setAttribute("gradeSubjectsAvailable", GradeSubject.values());

        // wyświetlenie formularza
        req.getRequestDispatcher("/grade-form.jsp").forward(req, resp);
    }
}
