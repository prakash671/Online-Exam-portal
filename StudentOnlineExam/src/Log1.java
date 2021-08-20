
import java.io.*;
import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Log1
 */
@WebServlet("/Log1")
public class Log1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public boolean checkUser(String regno,String pass) 
	
    {
        boolean st =false;
        try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbase","root","root");
            PreparedStatement ps = con.prepareStatement("select * from online_exam where regno=? and password=?");
            ps.setString(1, regno);
            ps.setString(2, pass);
            ResultSet rs =ps.executeQuery();
            st = rs.next();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    }   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	     response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String regno = request.getParameter("regno");
	        String passw = request.getParameter("pass");
	        
	        if(checkUser(regno,passw))
	        {
	            RequestDispatcher rs = request.getRequestDispatcher("Download.html");
	            rs.forward(request, response);
	        }
	        else
	        {
	           out.println("Username or Password incorrect");
	           RequestDispatcher rs = request.getRequestDispatcher("index.html");
	           rs.include(request, response);
	        }
		
		
		
	}

}
