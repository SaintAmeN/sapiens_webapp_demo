package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.model.Grade;
import com.sda.sapiens.webapp.model.GradeSubject;
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
import java.util.Optional;

import static com.sda.sapiens.webapp.servlet.ServletURL.STUDENT_DETAILS;

@Slf4j
@WebServlet("/grade/form")
public class GradeFormServlet extends HttpServlet {

    private final StudentRepository studentDao;
    private final GradeRepository gradeDao;

    @Inject
    public GradeFormServlet(StudentRepository studentDao, GradeRepository gradeDao) {
        this.studentDao = studentDao;
        this.gradeDao = gradeDao;
    }

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long studentId = Long.valueOf(req.getParameter("studentId"));

        Optional<Student> optionalStudent = studentDao.getById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            // odebranie parametru "wartość oceny"
            String gradeValue = req.getParameter("grade-value");
            Double gradeVal = Double.parseDouble(gradeValue);

            // odebranie parametru "przedmiot oceny"
            String gradeSubject = req.getParameter("grade-subject");
            GradeSubject gradeSubj = GradeSubject.valueOf(gradeSubject);

            // stworzenie obiektu/rekordu ocena (jeszcze nie jest dodany do bazy, dodanie niżej)
            Grade grade = new Grade(null, gradeVal, gradeSubj, student);
            gradeDao.saveOrUpdate(grade);

            resp.sendRedirect(req.getContextPath() + STUDENT_DETAILS + "?studentId=" + studentId);
        } else {
            // jeśli próbujemy dodać ocenę studentowi który nie istnieje, to przekierujemy na adres listy studentów
            resp.sendRedirect(req.getContextPath() + "/student");
        }
    }
}
