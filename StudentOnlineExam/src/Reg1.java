
import java.io.*;
import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reg1
 */
@WebServlet("/Reg1")
public class Reg1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
String r=request.getParameter("regno");  
String e=request.getParameter("email");  
String p=request.getParameter("pass");  

          
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/dbase","root","root");  
  
PreparedStatement ps=con.prepareStatement(  
"insert into online_exam(regno,email,password) values(?,?,?)");  
  
ps.setString(1,r);  
ps.setString(2,e);  
ps.setString(3,p);  
  
          
int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered..."); 
RequestDispatcher rs = request.getRequestDispatcher("Login.html");
rs.include(request, response);
      
          
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
	}

}
