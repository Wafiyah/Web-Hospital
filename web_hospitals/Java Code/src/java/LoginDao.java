import java.sql.*;  
  
public class LoginDao {  
public static boolean validate(String Username ,String user_password){  
boolean status=false;  
try{  
    Class.forName("com.mysql.jdbc.Driver");  
    Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/webhospital","root","");  
      
    PreparedStatement ps=con.prepareStatement("select * from users where Username=? and user_password=?");  
ps.setString(1,Username);  
ps.setString(2,user_password);  
      
ResultSet rs=ps.executeQuery();  
status=rs.next();  
          
}catch(Exception e){System.out.println(e);}  
return status;  
}  
} 
