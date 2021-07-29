package com.sda.sapiens.webapp.servlet;

import com.sda.sapiens.webapp.dao.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// simple logger for java
@Slf4j
@WebServlet("/database")
public class IndexServlet extends HttpServlet {
    public IndexServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Ładowanie strony index");

    }
}
