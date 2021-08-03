package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.dao.EntityDao;
import com.sda.sapiens.webapp.model.Grade;
import com.sda.sapiens.webapp.model.GradeSubject;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.sda.sapiens.webapp.servlet.ServletURL.STUDENT_DETAILS;

@Slf4j
@WebServlet("/grade/edit")
public class GradeEditServlet extends HttpServlet {
    private final EntityDao<Grade> gradeEntityDao;

    @Inject
    public GradeEditServlet(EntityDao<Grade> gradeEntityDao) {
        this.gradeEntityDao = gradeEntityDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long gradeIdentifier = Long.parseLong(req.getParameter("gradeId"));
        // przy metodzie get parameter to fragment ścieżki - ponieważ zapytanie GET jest jawne

        Optional<Grade> gradeOptional = gradeEntityDao.getById(Grade.class, gradeIdentifier);
        if (gradeOptional.isPresent()) {
            Grade grade = gradeOptional.get();

            req.setAttribute("grade_ktory_edytujemy", grade);
            req.setAttribute("gradeSubjectsAvailable", GradeSubject.values());

            req.getRequestDispatcher("/grade-form.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/student");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long gradeIdentifier = Long.parseLong(req.getParameter("edited_grade_id"));
        // przy metodzie post wszystkie parametry (treść formularza zostaje wysłana przez parametry niejawnie)
        // metoda post ukrywa parametry (treść formularza)

        Optional<Grade> gradeOptional = gradeEntityDao.getById(Grade.class, gradeIdentifier);
        if (gradeOptional.isPresent()) {
            Grade ocena_w_bazie_danych = gradeOptional.get();
            // Po tym jak użytkownik wysłał formularz odbieramy ID i pierwszym krokiem jest weryfikacja czy dana
            // ocena jest jeszcze w bazie.
            Long studentId = ocena_w_bazie_danych.getStudent().getId();

            // odebranie parametru "wartość oceny"
            String gradeValue = req.getParameter("grade-value");
            Double gradeVal = Double.parseDouble(gradeValue);

            // odebranie parametru "przedmiot oceny"
            String gradeSubject = req.getParameter("grade-subject");
            GradeSubject gradeSubj = GradeSubject.valueOf(gradeSubject);

            // aktualizacja pól obiektu w bazie
            ocena_w_bazie_danych.setGradeValue(gradeVal);
            ocena_w_bazie_danych.setSubject(gradeSubj);

            gradeEntityDao.saveOrUpdate(ocena_w_bazie_danych);

            resp.sendRedirect(req.getContextPath() + STUDENT_DETAILS + "?studentId=" + studentId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/student");
        }
    }
}
