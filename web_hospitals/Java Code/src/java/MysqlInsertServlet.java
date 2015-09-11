import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MysqlInsertServlet extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws IOException, ServletException
{
response.setContentType("text/html");
PrintWriter out= response.getWriter();

String deptid = request.getParameter("text1");
        String dname = request.getParameter("text2");
        String desig = request.getParameter("text3");
        String qualification = request.getParameter("text4");
        String time = request.getParameter("text5");

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
            //        PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement("Select * from tbl_userinfo");
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("insert into doctors(deptid,doctorname,Designation,Qualification,Time)"
                    + "values(?,?,?,?,?)");

            ps.setString(1,deptid);
            ps.setString(2,dname);
            ps.setString(3,desig);
             ps.setString(4,qualification);
              ps.setString(5,time);
            ps.execute();

        }  catch (ClassNotFoundException cx) {
            out.println(cx);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlInsertServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         RequestDispatcher rd=request.getRequestDispatcher("insertmsg.html");
         rd.include(request, response);
}
}