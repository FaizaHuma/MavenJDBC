package com.jdbc;
import javax.servlet.ServletException;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;

public class JDBCServ extends HttpServlet {

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
    {

        PrintWriter out=response.getWriter();

        String uname=request.getParameter("Uname");
        String pass=request.getParameter("password");
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","Computer_lab","computer_lab");
            Statement stmt=con.createStatement();
            ResultSet rst=stmt.executeQuery("select*from computer_student");

            while(rst.next())

            {    String computer_student=rst.getString(2);

                int student_id=rst.getInt(1);
                int student_age=rst.getInt(3);

                out.println("<h1>"+computer_student+" "+student_id+" "+student_age+"</h1>");
            }
            con.close();


        }
        catch(Exception e)
        {
            out.println("<h3>"+e+"</h3>");
        }

        out.println("<html><body>");
        out.println("<form action="+"index.jsp"+">");
        out.println("<input type="+"submit"+" "+"value="+"Back"+">");
        out.println("</form>");
        out.println("</body></html>");




    }
}
