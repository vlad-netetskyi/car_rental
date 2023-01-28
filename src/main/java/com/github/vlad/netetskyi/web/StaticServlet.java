package com.github.vlad.netetskyi.web;

import jakarta.servlet.annotation.WebServlet;
import org.apache.catalina.servlets.DefaultServlet;

@WebServlet("/resources/*")
public class StaticServlet extends DefaultServlet {
}