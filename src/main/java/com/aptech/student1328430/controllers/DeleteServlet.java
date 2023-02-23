package com.aptech.student1328430.controllers;

import com.aptech.student1328430.services.ProductBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/","/delete"})
public class DeleteServlet extends HttpServlet {
    @Inject
    ProductBean bean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("productList", bean.findAll());
        req.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("productId");
        boolean isDeletedAll = false;
        for(String val : values){
            if(!bean.deleteProduct(Integer.parseInt(val))){
                isDeletedAll = true;
                break;
            }
        }
        if(!isDeletedAll){
            req.getServletContext().getRequestDispatcher("/WEB-INF/success.jsp").forward(req,resp);
            return;
        }else {
            req.getServletContext().getRequestDispatcher("/WEB-INF/failed.jsp").forward(req,resp);
            return;
        }
    }
}

