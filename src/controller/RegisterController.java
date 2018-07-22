package controller;

import exception.ServiceException;
import service.UserService;
import util.JSONResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "Register", urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        PrintWriter out = resp.getWriter();

        if(username == null || password == null){
            out.print(new JSONResponse(false, "请输入用户名与密码参数"));
            return;
        }

        if(username.isEmpty() || password.isEmpty()){
            out.print(new JSONResponse(false, "用户名和密码不能为空"));
            return;
        }

        UserService userService = UserService.getInstance();
        try {
            userService.register(username, password);
            req.getSession().setAttribute("username", username);
            out.println(new JSONResponse(true));

        } catch (ServiceException e) {
            out.println(new JSONResponse(false, e.getMessage()));
        }

    }
}
