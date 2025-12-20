package com.example.demo.servlet;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "SimpleStatusServlet", urlPatterns = "/status")
public class SimpleStatusServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain");
    var writer = resp.getWriter();
    writer.write("Cold Chain Temperature Breach Alert System is running");
    writer.flush();
  }
}