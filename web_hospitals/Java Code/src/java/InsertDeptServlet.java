/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InsertDeptServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();

       String deptname = request.getParameter("text1");

        String className = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.13";
        String user = "root";
        String password = "root";

        PreparedStatement ps;
        ResultSet rs;

        try {
            Class.forName(className);
               Connection con = DriverManager.getConnection
                                ("jdbc:mysql://127.0.0.1:3306/webhospital","root","");
           
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("insert into department(deptname)" + "values(?)");

            ps.setString(1,deptname);
            ps.execute();

        }  catch (ClassNotFoundException cx) {
            out.println(cx);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlInsertServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         RequestDispatcher rd=request.getRequestDispatcher("insertdept_msg.html");
         rd.include(request, response);
        }
    }

