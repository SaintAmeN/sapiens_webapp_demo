package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.dao.EntityDao;
import com.sda.sapiens.webapp.dao.StudentDao;
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
import java.util.List;

@Slf4j
@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private final EntityDao<Student> studentDao;

    @Inject
    public StudentServlet(EntityDao<Student> studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentDao.getAll(Student.class);

        req.setAttribute("studentList", studentList);
        req.getRequestDispatcher("/student.jsp").forward(req, resp);
    }
}
