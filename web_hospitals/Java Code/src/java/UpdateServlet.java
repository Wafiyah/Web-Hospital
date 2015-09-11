import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/UpdateJdbcQueryServlet")
public class UpdateServlet extends HttpServlet {
 
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int did= Integer.parseInt(request.getParameter("text1"));
        String dname = request.getParameter("text2");
        String desig = request.getParameter("text3");
        String qualification = request.getParameter("text4");
        String time = request.getParameter("text5");

        String className = "com.mysql.jdbc.Driver";
       // String url = "jdbc:mysql://192.168.10.13";
        String user = "root";
        String password = "";

        PreparedStatement ps;
        ResultSet rs;

        try {
            Class.forName(className);
    
            
               Connection con = DriverManager.getConnection
                                ("jdbc:mysql://127.0.0.1:3306/webhospital","root","");
            //        PreparedStatement prepStmt = (PreparedStatement) conn.prepareStatement("Select * from tbl_userinfo");
            ps = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("update doctors set Designation=?,Qualification=?,Time= ? where deptid =? and doctorname=?");
           ps.setString(1, desig);
                ps.setString(2,qualification);
                ps.setString(3,time);
                ps.setInt(4,did);
                  ps.setString(5, dname);
                
            ps.executeUpdate();

        }  catch (ClassNotFoundException cx) {
            out.println(cx);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlInsertServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          RequestDispatcher rd=request.getRequestDispatcher("update_msg.html");
         rd.include(request, response);
    }
}
