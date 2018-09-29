package com.wgh.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/IPServer")
public class IPFinderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置session
        HttpSession session = req.getSession();

        String ip = req.getParameter("ip");
        if(ip==null){
            resp.sendRedirect("index.jsp");
        }
        int ipDecimal = com.wgh.util.IPHandler.ipTrim(ip);
        System.out.println(ipDecimal);

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try{
            new com.mysql.jdbc.Driver();
            String url = "jdbc:mysql:///?user=java&password=java&useUnicode=true&characterEncoding=UTF-8";
            String sql = "select param from javaee.ipfinder where ( ?>startip and ?<endip) or (?>endip and ?<startip)";
            connection = DriverManager.getConnection(url);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ipDecimal);
            preparedStatement.setInt(2, ipDecimal);
            preparedStatement.setInt(3, ipDecimal);
            preparedStatement.setInt(4, ipDecimal);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                String param =rs.getString("param");
                System.out.println(param);
                session.setAttribute("result", ip+" 的位置为"+param);
            }
            session.setAttribute("find",true);
            resp.sendRedirect("index.jsp");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
